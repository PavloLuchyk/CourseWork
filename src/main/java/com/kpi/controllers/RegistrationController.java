package com.kpi.controllers;

import com.kpi.dao.UserDao;
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
                                                request.getParameter("phoneNumber"));
        UserValidation userValidation = new UserValidation();
        if (userValidation.validate(userWrapper).isResult()){
            User user = UserService.getUser(userWrapper);
            UserDao userDao = new UserDao();
            userDao.add(user);
            response.setContentType("text/html");
            request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").include(request, response);
            response.getWriter().println("<script src = \"" + request.getContextPath() +
                    "/static/js/successfulRegistrationScript.js\">;</script>");
        } else {
            response.setContentType("text/html");
            request.setAttribute("message", userValidation.validate(userWrapper).getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/registrationPage.jsp").include(request,response);
            response.getWriter().println("<script src = \"" + request.getContextPath() +
                    "/static/js/failedRegistrationScript.js\">;</script>");
        }
    }
}
