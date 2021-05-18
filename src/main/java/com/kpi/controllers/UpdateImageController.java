package com.kpi.controllers;

import com.kpi.dao.MenuElementDao;
import com.kpi.models.MenuElement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
        MenuElementDao menuElementDao = new MenuElementDao();
        try(InputStream inputStream = request.getPart("file").getInputStream()) {
            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);
            menuElementDao.updateImage(elementId, buf);
            System.out.println(Arrays.equals(buf, menuElementDao.get(elementId).getImage()));
        }
        request.setAttribute("menuElement", menuElementDao.get(Integer.parseInt(request.getParameter("elementId"))));
        request.getRequestDispatcher("WEB-INF/jsp/elementUpdatePage.jsp").forward(request, response);
    }
}
