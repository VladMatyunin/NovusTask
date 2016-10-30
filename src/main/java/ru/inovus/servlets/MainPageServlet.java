package ru.inovus.servlets;

import ru.inovus.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Created by Vlad.M on 27.10.2016.
 */

public class MainPageServlet extends HttpServlet {
    private final LocalTime MORNING_TIME = LocalTime.of(6,0);
    private final LocalTime AFTERNOON_TIME = LocalTime.of(10,0);
    private final LocalTime NIGHT_TIME = LocalTime.of(18,0);
    private final LocalTime MIDNIGHT_TIME = LocalTime.of(22,0);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user_auth");
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/sign-in"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user_auth") != null){
            ZoneId zone1 = ZoneId.of("Europe/Moscow");
            LocalTime nowTime = LocalTime.now();
            System.out.println(nowTime);
            User curr_user = (User) session.getAttribute("user_auth");
            String welcometext = "Здравствуйте," + curr_user.getName();
            if (nowTime.isBefore(MORNING_TIME) && nowTime.isAfter(MIDNIGHT_TIME)) welcometext = "Доброй ночи," + curr_user.getName() +"!";
            if (nowTime.isBefore(AFTERNOON_TIME) && nowTime.isAfter(MORNING_TIME)) welcometext = "Доброе утро," + curr_user.getName() +"!";
            if (nowTime.isBefore(NIGHT_TIME) && nowTime.isAfter(AFTERNOON_TIME)) welcometext = "Добрый день," + curr_user.getName() +"!";
            if (nowTime.isBefore(MIDNIGHT_TIME) && nowTime.isAfter(NIGHT_TIME)) welcometext = "Добрый вечер," + curr_user.getName() +"!";
            req.setAttribute("welcome", welcometext);
            req.setAttribute("now", nowTime.toString());
            req.getServletContext().getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/sign-in"));
        }
    }
}
