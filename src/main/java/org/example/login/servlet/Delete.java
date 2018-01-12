package org.example.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.login.dao.UserDao;

public class Delete extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        HttpSession hs=req.getSession();
        String name=(String) hs.getAttribute("1");
        UserDao userDao=new UserDao();
        try {
            userDao.deleteUser(name);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String msg="用户删除成功！";
        req.setAttribute("msg", msg);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);

    }
}
