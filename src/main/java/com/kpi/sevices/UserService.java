package com.kpi.sevices;

import com.kpi.dao.UserDao;
import com.kpi.dao.mysql.MySQLDaoFactory;
import com.kpi.models.User;
import com.kpi.validation.UserValidation;
import com.kpi.wrapper.UserWrapper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;

public class UserService {
    public static byte[] getPasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128 );
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        return hash;
    }

    public static byte[] getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static User getUser(UserWrapper userWrapper){
        try {
            byte[] salt = getSalt();
            byte[] passwordHash = getPasswordHash(userWrapper.getPassword(), salt);
            return new User(userWrapper.getUsername(),
                            userWrapper.getEmail(),
                            passwordHash,
                            salt,
                            userWrapper.getPhoneNumber(),
                            userWrapper.getAddress());
        } catch (NoSuchAlgorithmException|InvalidKeySpecException e){
            System.out.println(e.getMessage());
            return new User("", "", new byte[1], new byte[1], "", "");
        }
    }

    public static void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserWrapper userWrapper = new UserWrapper(request.getParameter("username"),
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("phoneNumber"),
                request.getParameter("address"));
        UserValidation userValidation = new UserValidation();
        if (userValidation.validate(userWrapper).isResult()){
            User user = UserService.getUser(userWrapper);
            UserDao userDao = new MySQLDaoFactory().getUserDao();
            userDao.add(user);
            request.setAttribute("message", "You have successfully registered. Now you can log in");
            request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").forward(request, response);
        } else {
            request.setAttribute("message", userValidation.validate(userWrapper).getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/registrationPage.jsp").forward(request,response);
        }
    }

    public static void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username =  request.getParameter("username");
        String password = request.getParameter("password");
        ArrayList<User> users = new MySQLDaoFactory().getUserDao().getAll();
        for (User user: users){
            if (user.getUsername().equals(username)){
                try {
                    byte[] passwordHash = UserService.getPasswordHash(password, user.getPasswordSalt());
                    if (Arrays.equals(passwordHash, user.getPasswordHash())) {
                        request.getSession().setAttribute("userId", user.getUserId());
                        request.getSession().setAttribute("username", user.getUsername());
                        request.getSession().setAttribute("admin", user.isAdmin());
                        request.getRequestDispatcher("IndexController").forward(request, response);
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    System.out.println("Exception! " + e.getMessage());
                }
            }
        }
        request.setAttribute("errorMessage", "Wrong username or password");
        request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").forward(request, response);
    }
}
