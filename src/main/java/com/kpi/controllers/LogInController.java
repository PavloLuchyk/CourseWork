package com.kpi.controllers;

import com.kpi.dao.mysql.MySQLDaoFactory;
import com.kpi.dao.mysql.UserMySQLDao;
import com.kpi.models.User;
import com.kpi.sevices.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "LogInController", value = "/LogInController")
public class LogInController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
