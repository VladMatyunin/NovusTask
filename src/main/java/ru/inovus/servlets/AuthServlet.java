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

public class AuthServlet extends HttpServlet{
    UserRepository userRepository;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user_auth") == null){
            req.getServletContext().getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, resp);
            return;
        }
        else {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/welcome"));
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name =  req.getParameter("name");
        String password =  req.getParameter("password");
        if (name != null && password != null){
            try {
                User user;
                if (userRepository.getUserByName(name) != null){
                    user = userRepository.getUserByName(name);
                    if (password.equals(user.getPassword())){
                        session.setAttribute("user_auth", user);
                        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/welcome"));
                        return;
                    }
                    else {
                        req.setAttribute("message", "Wrong password");
                        req.getServletContext().getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, resp);
                        return;
                    }
                }
                else {
                    req.setAttribute("message", "No such user");
                    req.getServletContext().getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            req.setAttribute("message","Fill all fields");
        }
    }

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepositoryImpl();
    }
}
