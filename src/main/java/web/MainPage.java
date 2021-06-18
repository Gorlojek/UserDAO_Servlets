package web;

import user.User;
import user.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user/list")
public class MainPage extends HttpServlet {
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User[] users = userDao.findAll();
        req.setAttribute("users",users);


        getServletContext().getRequestDispatcher("/jsp/userdao.jsp").forward(req,resp);
    }


}
