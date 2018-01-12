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

public class Login extends HttpServlet {
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String name=req.getParameter("name");
        String pwd=req.getParameter("password");
        HttpSession hs=req.getSession();
        UserDao userDao =new UserDao();
        User user = null;
        try {
            user = userDao.findByName(name);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(name!=null&&pwd!=null&&name.equals(user.getName())&&pwd.equals(user.getPassword())){
            System.out.println("登录成功");
            hs.setAttribute("user",name);
            resp.sendRedirect("success.jsp");
        }else{
            String msg="你所输入的用户名不存在或者密码不正确，请重新输入！";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);

    }
}
