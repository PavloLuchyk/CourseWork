package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.dao.mysql.MenuElementMySQLDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "UpdateImageController", value = "/UpdateImageController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UpdateImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int elementId = Integer.parseInt(request.getParameter("elementId"));
        MenuElementDao menuElementDao = new MenuElementMySQLDao();
        try(InputStream inputStream = request.getPart("file").getInputStream()) {
            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);
            menuElementDao.updateImage(elementId, buf);
        }
        request.setAttribute("menuElement", menuElementDao.get(Integer.parseInt(request.getParameter("elementId"))));
        request.getRequestDispatcher("WEB-INF/jsp/elementUpdatePage.jsp").forward(request, response);
    }
}
