package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.models.MenuElement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "UploadController", value = "/UploadController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuElementDao menuElementDao = new MenuElementDao();
        try(InputStream inputStream = request.getPart("file").getInputStream()) {
            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);
            menuElementDao.add(new MenuElement(9, request.getParameter("name"),
                    request.getParameter("description"),
                    request.getParameter("ingredients"),
                    Double.parseDouble(request.getParameter("price")),
                    buf));
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
