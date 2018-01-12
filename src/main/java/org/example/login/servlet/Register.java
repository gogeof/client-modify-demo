package org.example.login.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.login.bean.User;
import org.example.login.dao.UserDao;

public class Register extends HttpServlet {
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
        resp.setContentType("text/html;charset=utf-8");
        String name=req.getParameter("name");
        String pwd=req.getParameter("password");
        UserDao uDao=new UserDao();
        User user = null;
        try {
            user = uDao.findByName(name);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(user.getName());
        System.out.println("判断user是否存在");
        if(name.equals(user.getName())){
            String msg=name+"该用户名已经使用！请重新注册！";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }else{
            System.out.println("检验数据库没有该用户，存入用户信息！");
            User user1=new User();
            user1.setName(name);
            user1.setPassword(pwd);
            try {
                uDao.saveUser(user1);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String msg=name+"注册成功，请重新登陆！";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
        doGet(req, resp);
    }
}
