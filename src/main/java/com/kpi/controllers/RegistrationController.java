package com.kpi.controllers;

import com.kpi.dao.UserDao;
import com.kpi.dao.mysql.MySQLDaoFactory;
import com.kpi.dao.mysql.UserMySQLDao;
import com.kpi.models.User;
import com.kpi.sevices.UserService;
import com.kpi.validation.UserValidation;
import com.kpi.wrapper.UserWrapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationController", value = "/RegistrationController")
public class RegistrationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/registrationPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
