package web;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/user/editUser","/user/deleteUser/*","/user/showUser"})
public class SessionAdd extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        if(session.getAttribute("username") == null){
            session.setAttribute("username",username);
        }
        if(session.getAttribute("email") == null){
            session.setAttribute("email",email);
        }
        if(session.getAttribute("id") == null){
            session.setAttribute("id",id);
        }
        session.setAttribute("username",username);
        session.setAttribute("email",email);
        session.setAttribute("id",id);
        chain.doFilter(req,res);
    }

}
