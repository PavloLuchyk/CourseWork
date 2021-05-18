package com.kpi.controllers;

import com.kpi.dao.mysql.MenuElementMySQLDao;
import com.kpi.models.MenuElement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "GetController", value = "/GetController")
public class GetController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuElementMySQLDao menuElementDao = new MenuElementMySQLDao();
        MenuElement menuElement = menuElementDao.get(1);
        System.out.println(request.getParameter("id"));
        /*File file = new File( request.getContextPath() + "\\images\\image.jpg");
        file.getParentFile().mkdirs(); // correct!
        if (!file.exists()) {
            file.createNewFile();
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile( request.getContextPath() + "\\images\\image.jpg", "rw");
        FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(file);
        BufferedImage bufferedImage = ImageIO.read(menuElement.getImageStream());
        ImageIO.write(bufferedImage, "jpg",fileImageOutputStream);*/
        try(ServletOutputStream fos = response.getOutputStream()) {
            byte[] b = menuElement.getImage();
            fos.write(b);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
