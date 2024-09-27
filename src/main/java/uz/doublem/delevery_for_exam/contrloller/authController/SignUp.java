package uz.doublem.delevery_for_exam.contrloller.authController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.service.AuthService;

import java.io.IOException;

@WebServlet("/auth/sign-up")
public class SignUp extends HttpServlet {
    AuthService authService = AuthService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sign",true);
        req.getRequestDispatcher("views/auth.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (authService.signUp(req,resp)) {
            req.getRequestDispatcher("/views/authPage.jsp").forward(req, resp);
        }else {
            resp.addCookie(new Cookie("identity",null));
            req.getRequestDispatcher("/views/authPage.jsp").forward(req, resp);
        }
//        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);

    }

}
