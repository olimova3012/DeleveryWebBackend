package uz.doublem.delevery_for_exam.contrloller.admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.repository.UserRepository;

import java.io.IOException;

@WebServlet("/UserToggleConfirmedServlet")
public class UserToggleConfirmedServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        UserRepository userRepository = UserRepository.getInstance();

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String userId = request.getParameter("id");
            String newStatus = request.getParameter("newStatus");

            boolean updated = userRepository.updateUserActive(userId, Boolean.parseBoolean(newStatus));

            if (updated) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
}

