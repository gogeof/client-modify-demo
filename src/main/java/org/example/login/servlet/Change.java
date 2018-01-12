package org.example.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.login.bean.User;
import org.example.login.dao.UserDao;

public class Change extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        HttpSession hs=req.getSession();
        String name=(String) hs.getAttribute("1");
        System.out.println("当前登陆用户名"+name);
        String password=(String) hs.getAttribute("2");
        String password1=req.getParameter("password1");//新密码
        String password2=req.getParameter("password2");//重新输入一次密码
        if(password.equals(password1)){
            String msg1="新输入的密码与旧密码一样，请重新输入";
            req.setAttribute("msg1", msg1);
            req.getRequestDispatcher("change.jsp").forward(req,resp);
        }else if(!password2.equals(password1)){
            String msg2="密码不一致！请重新输入！";
            req.setAttribute("msg2", msg2);
            req.getRequestDispatcher("change.jsp").forward(req,resp);
        }else  if(password2.equals(password1)){
            System.out.println(",,,,,,,,,,,,,,,,,,,");
            UserDao userDao=new UserDao();
            User user=new User();
            user.setName(name);
            user.setPassword(password1);
            try {
                userDao.changUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String msg="密码修改成功，请重新登陆！";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }


    }
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);

    }
}
