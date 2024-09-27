package uz.doublem.delevery_for_exam.contrloller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Role;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.repository.UserRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import static uz.doublem.delevery_for_exam.contrloller.utilConfig.Utils.getCookie;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    UserRepository userRepository = UserRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        soxta cookiye for admin
//shedan
//        Cookie[] cookies = req.getCookies();
//        req.setAttribute("exists", false);
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("identity")) {
//                    String value = cookie.getValue();
//                    value = new String(Base64.getDecoder().decode(value.getBytes()));
//                    Optional<Users> optionalUser = userRepository.getUserById(value);
//                    if (optionalUser.isPresent() && optionalUser.get().isActive()) {
//                        Users user = optionalUser.get();
//                        if (user.getRole().equals(Role.ADMIN)) {
//                            req.setAttribute("users", userRepository.getUsers());
//                            req.setAttribute("exists", true);
//                            req.setAttribute("user", user);
//                            req.getRequestDispatcher("/views/admin_main.jsp").forward(req, resp);
//                            return;
//                        } else {
//                            req.getRequestDispatcher("/views/usersPage/public/index.jsp").forward(req, resp);
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//            Users user = Users.builder()
//                    .firstName("admin")
//                    .lastName("admins")
//                    .isActive(true)
//                    .phone_number("123")
//                    .isDeleted(false)
//                    .role(Role.ADMIN)
//                    .email("admin@gmail.com")
//                    .password("root123")
//                    .code("12345")
//                    .build();
//            userRepository.save(user);
//            Cookie cookie = new Cookie("identity", new String(Base64.getEncoder().encode(user.getId().getBytes())));
//            resp.addCookie(cookie);
//            req.setAttribute("users", userRepository.getUsers());
//            req.setAttribute("exists", true);
//            req.setAttribute("user", user);
//
//        req.getRequestDispatcher("/views/admin_main.jsp").forward(req, resp);
// shegacha
        req.getRequestDispatcher("/views/admin_main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
