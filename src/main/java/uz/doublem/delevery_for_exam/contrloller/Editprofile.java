package uz.doublem.delevery_for_exam.contrloller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.repository.UserRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;
@WebServlet("/editProfile")
public class Editprofile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("date_of_birth");

            UserRepository userRepository =  UserRepository.getInstance();
            Optional<Users> optionalUsers = userRepository.getUserById(id);
            if (optionalUsers.isEmpty()) {
                return;
            }
            Users user = optionalUsers.get();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone_number(phoneNumber);
            user.setPassword(password);
            if (dateOfBirth != null&&!dateOfBirth.isBlank()) {
                if (!dateOfBirth.contains(" ")) {
                    // Append the time part if it's missing
                    dateOfBirth = dateOfBirth + " 00:00:00";
                }
                user.setDateOfBirth(Timestamp.valueOf(dateOfBirth));
            }else{
                user.setDateOfBirth(Timestamp.valueOf(LocalDateTime.now()));
            }
            userRepository.update(user);
            response.sendRedirect("/profile?id="+id);

        }

    }

