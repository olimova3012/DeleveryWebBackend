package uz.doublem.delevery_for_exam.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.repository.UserRepository;

import java.util.Base64;
import java.util.Optional;
import java.util.Random;

public class AuthService {
    UserRepository userRepository = UserRepository.getInstance();
    EmailService emailService = EmailService.getInstance();


    @SneakyThrows
    public boolean signIn(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("exists", true);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<Users> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(password)) {
            request.setAttribute("exists", false);
            response.addCookie(new Cookie("identity",null));
            request.setAttribute("message", "sorry you should sign up");
            return false;
        }
        Users user = optionalUser.get();
        if (!user.isActive()) {
            response.addCookie(new Cookie("identity",null));
            request.setAttribute("message", "you should confirm your account!");
            return false;
        }
        request.setAttribute("user", user);
        Cookie cookie = new Cookie("identity", new String(Base64.getEncoder().encode(user.getId().getBytes())));
        response.addCookie(cookie);
        return true;
    }

    public boolean signUp(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        if (name == null || name.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            request.setAttribute("message","name email or password null");
            request.setAttribute("exists", false);
            return false;
        }
        Optional<Users> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            request.setAttribute("message","this email already exists");
            request.setAttribute("exists", false);

            return false;
        }
        Users user = Users.builder()
                .firstName(name)
                .email(email)
                .password(password)
                .phone_number(phoneNumber)
                .code(generateCode())
                .build();
        userRepository.save(user);
        String text = user.getEmail() + ":" + user.getCode();
        final String message = new String(Base64.getEncoder().encode(text.getBytes()));
        new Thread(() -> emailService.sendSmsToUser(user.getEmail(), message)).start();
        request.setAttribute("message","Confirm Your email");
        request.setAttribute("exists", true);
        return true;
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


    @SneakyThrows
    public boolean confirmEmail(HttpServletRequest req, HttpServletResponse resp) {

        String confirmation = req.getParameter("confirmation");
        confirmation = new String(Base64.getDecoder().decode(confirmation.getBytes()));
        String[] split = confirmation.split(":");
        Optional<Users> optionalUser = userRepository.getUserByEmail(split[0]);
        req.setAttribute("exists", false);
        if (optionalUser.isEmpty() || !optionalUser.get().getCode().equals(split[1])) {
            req.setAttribute("message", "you should confirm your email");
            return false;
        }
        Users user = optionalUser.get();
        user.setActive(true);
        userRepository.update(user);
        req.setAttribute("exists", true);
        req.setAttribute("user",user);
        return true;
    }


    private static AuthService authService;

    public static AuthService getInstance() {
        if (authService == null)
            synchronized (AuthService.class) {
                if (authService == null) {
                    authService = new AuthService();
                }
            }
        return authService;
    }


    public boolean authorization(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        req.setAttribute("exists", false);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("identity")) {
                    String value = cookie.getValue();
                    value = new String(Base64.getDecoder().decode(value.getBytes()));
                    Optional<Users> optionalUser = userRepository.getUserById(value);
                    if (optionalUser.isPresent() && optionalUser.get().isActive()) {
                        Users user = optionalUser.get();
//                        req.setAttribute("users", userRepository.getUsers());
                        req.setAttribute("exists", true);
                        req.setAttribute("user", user);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
