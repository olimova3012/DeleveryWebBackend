package uz.doublem.delevery_for_exam.contrloller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.repository.CategoryRepository;
import uz.doublem.delevery_for_exam.repository.ProductRepository;

import java.io.IOException;

@WebServlet("/admin/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
    private CategoryRepository categoryRepository = CategoryRepository.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null && !id.trim().isEmpty()) {
            categoryRepository.delete(Integer.parseInt(id));
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }
    }
}
