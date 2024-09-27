package uz.doublem.delevery_for_exam.contrloller.user;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/PageMain")
public class UserPageMain extends HttpServlet {
    UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        String login = req.getParameter("login");
        if (search != null && !search.isEmpty() || !login.equals("login")) {
            userService.getSearchFood(search,req,resp);
        }else {
            resp.addCookie(new Cookie("identity",null));
            req.getRequestDispatcher("/views/authPage.jsp").forward(req, resp);
        }
    }
}
