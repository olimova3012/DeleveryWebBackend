package uz.doublem.delevery_for_exam.contrloller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.repository.CategoryRepository;
import uz.doublem.delevery_for_exam.repository.ProductRepository;
import uz.doublem.delevery_for_exam.service.ProductService;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/admin/saveProduct")
public class SaveProductServlet  extends HttpServlet {
    ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null&&!req.getParameter("id").isBlank()) {
            productService.editProduct(req,resp);
        }else {
            productService.addProduct(req,resp);
        }
    }
}
