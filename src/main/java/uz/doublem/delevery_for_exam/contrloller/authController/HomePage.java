package uz.doublem.delevery_for_exam.contrloller.authController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Role;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.service.AuthService;

import java.io.IOException;

@WebServlet("/home")
public class HomePage extends HttpServlet {
    AuthService authService = AuthService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        enterWithHome(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        enterWithHome(req, resp);
    }

    private void enterWithHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (authService.authorization(req, resp)) {
            if (((Users) req.getAttribute("user")).getRole().equals(Role.ADMIN)) {
                req.getRequestDispatcher("/admin").forward(req, resp);
            }else {
                req.getRequestDispatcher("/views/usersPage/public/index.jsp").forward(req, resp);
            }
            return;
        }
        req.setAttribute("message","something went wrong please sign in again");
        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
    }
}
