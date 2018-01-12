package org.example.login.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.login.bean.User;
import org.example.login.dao.UserDao;

public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String name=req.getParameter("name");
        HttpSession hs=req.getSession();
        hs.setAttribute("user",null);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }
}
