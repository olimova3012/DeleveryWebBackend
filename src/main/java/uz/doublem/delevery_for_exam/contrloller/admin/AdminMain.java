package uz.doublem.delevery_for_exam.contrloller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.entity.Combo;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.entity.Users;
import uz.doublem.delevery_for_exam.repository.CategoryRepository;
import uz.doublem.delevery_for_exam.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/adminMainController")
public class AdminMain extends HttpServlet {
    CategoryRepository categoryRepository = CategoryRepository.getInstance();
    ProductRepository productRepository = ProductRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        if (value.equals("category")) {
            List<Category> all = categoryRepository.getAll();
            req.setAttribute("allCategory", all);
            req.getRequestDispatcher("/views/adminCategory.jsp").forward(req, resp);
        } else if (value.equals("product")) {
            List<Product> all = productRepository.getAll(Product.class);
            req.setAttribute("product", all);
            List<Combo> allCombo = productRepository.getAll(Combo.class);
            req.setAttribute("combos",allCombo);
            req.getRequestDispatcher("/views/adminProduct.jsp").forward(req, resp);
        }else if (value.equals("users")) {
        List<Users> all = productRepository.getAll(Users.class);
        req.setAttribute("users", all);
        req.getRequestDispatcher("/views/adminUsers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
