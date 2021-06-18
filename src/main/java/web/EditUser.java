package web;

import user.User;
import user.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/editUser")
public class EditUser extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");
        Object id = session.getAttribute("id");

        getServletContext().getRequestDispatcher("/jsp/edituser.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        int userID = Integer.parseInt((String) session.getAttribute("id"));
        //wiadomość do konsoli
        System.out.println("Edytowany użytkownik " + userID);

        User user = userDao.read(userID);
        user.setUserName(username);
        user.setEmail(email);
        userDao.updateUserInfo(user, userID);
        resp.sendRedirect("/user/list");
    }
}