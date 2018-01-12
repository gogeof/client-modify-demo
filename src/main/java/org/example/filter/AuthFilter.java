package org.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.login.bean.User;
/**
 * 全站判断用户是否登录过滤器
 *
 */
public class AuthFilter implements Filter{

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        String user = (String)session.getAttribute("user");
        String uri = req.getRequestURI();
        //简单判断缓存中是否有用户
        if(user==null || user.equals("")){//没有用户
            //判断用户是否是选择跳到登录界面
            if(uri.endsWith("login.jsp")||uri.endsWith("login.do")){
                chain.doFilter(request, response);
            }else{
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
            }
        }else{//有用户
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
