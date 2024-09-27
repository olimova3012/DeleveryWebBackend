package uz.doublem.delevery_for_exam.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.repository.ProductRepository;

import java.util.List;

public class UserService {
    ProductRepository productRepository = ProductRepository.getInstance();



    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null)
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        return userService;
    }

    public void getSearchFood(String search,HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = productRepository.getSearchProduct(search);
        if (products == null){
            req.setAttribute("message","there is not food for search");
        }{
            req.setAttribute("products", products);
        }
        req.getRequestDispatcher("views/usersPage/public/searchFood.jsp");
    }
}
