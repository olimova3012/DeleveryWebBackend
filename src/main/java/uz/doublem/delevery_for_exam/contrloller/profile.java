package uz.doublem.delevery_for_exam.contrloller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/profile")
public class profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        Optional<Users> optionalUsers = UserRepository.getInstance().getUserById(userId);
        if(optionalUsers.isPresent()){
            req.setAttribute("user", optionalUsers.get());
            req.getRequestDispatcher("/views/attachments/profile.jsp").forward(req, resp);
        }}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}




