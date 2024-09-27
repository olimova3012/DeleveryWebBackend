package uz.doublem.delevery_for_exam.contrloller.utilConfig;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import uz.doublem.delevery_for_exam.entity.Role;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.repository.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@WebFilter("/*")
public class Config implements Filter {
    UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        Cookie[] cookies = req.getCookies();
        req.setAttribute("exists", false);

        if (cookies == null || Arrays.stream(cookies).noneMatch(cookie -> cookie.getName().equals("identity") && !cookie.getValue().isBlank())) {
            // If cookies are null or no "identity" cookie is found
            if (requestURI.contains("/user/") && !requestURI.contains("/auth/")) {
                req.setAttribute("exists", false);
                req.setAttribute("message", "Iltimos avval ro'yxatdan o'ting.");
                req.getRequestDispatcher("/views/authPage.jsp").forward(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            return;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("identity")) {
                String value = new String(Base64.getDecoder().decode(cookie.getValue().getBytes()));
                Optional<Users> optionalUser = userRepository.getUserById(value);

                if (optionalUser.isPresent() && optionalUser.get().isActive()) {
                    Users user = optionalUser.get();
                    Context.setUser(user);

                    if (requestURI.contains("/admin") && !user.getRole().equals(Role.ADMIN)) {
                        req.setAttribute("message", "Not found");
                        req.getRequestDispatcher("/views/error-page.jsp").forward(servletRequest, servletResponse);
                        return;
                    }

                    req.setAttribute("exists", true);
                    req.setAttribute("user", user);
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                } else {
                    req.setAttribute("exists", false);
                    req.setAttribute("message", "Please confirm your account.");
                    req.getRequestDispatcher("/views/authPage.jsp").forward(servletRequest, servletResponse);
                    return;
                }
            }
        }

        // Fallback error message
        req.setAttribute("message", "Something went wrong with credentials.");
        req.getRequestDispatcher("/views/error-page.jsp").forward(servletRequest, servletResponse);
    }

}
