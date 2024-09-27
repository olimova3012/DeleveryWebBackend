package uz.doublem.delevery_for_exam.contrloller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.repository.CategoryRepository;
import uz.doublem.delevery_for_exam.service.CategoryService;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/admin/saveCategory")
public class SaveCategoryServlet  extends HttpServlet {
    CategoryService categoryService = CategoryService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null&&!req.getParameter("id").isBlank()) {
           categoryService.editCategory(req,resp);
        }else {
            categoryService.addCategory(req,resp);
        }
    }
}
