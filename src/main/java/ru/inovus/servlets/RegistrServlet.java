package ru.inovus.servlets;

import ru.inovus.entities.User;
import ru.inovus.repository.UserRepository;
import ru.inovus.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Vlad.M on 27.10.2016.
 */

public class RegistrServlet extends HttpServlet{
    UserRepository userRepository;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user_auth") != null){
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/welcome"));
        }
        else {
            req.getServletContext().getRequestDispatcher("/WEB-INF/view/reg.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String confirmPass = req.getParameter("confirmpass");
        System.out.println(name+password+confirmPass);
        if (confirmPass.equals(password) && name != null && password != null && name != "" && password != ""){
            User user = new User(name,password);
            try {
                if (userRepository.contains(user)) {
                    req.setAttribute("message", "This user is already registered");
                    req.getServletContext().getRequestDispatcher("/WEB-INF/view/reg.jsp").forward(req, resp);
                }
                else {
                    userRepository.addUser(user);
                    session.setAttribute("user_auth",user);
                    resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/welcome"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (!password.equals(confirmPass)){
            req.setAttribute("message", "check Password");
            req.getServletContext().getRequestDispatcher("/WEB-INF/view/reg.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("message", "Fill all fields");
            req.getServletContext().getRequestDispatcher("/WEB-INF/view/reg.jsp").forward(req, resp);
        }
    }

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepositoryImpl();
    }
}
