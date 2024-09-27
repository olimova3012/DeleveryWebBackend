package uz.doublem.delevery_for_exam.contrloller.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.service.ProductService;

import java.io.IOException;

@WebServlet("/admin/saveCombo")
public class SaveComboServalet extends HttpServlet {
    ProductService productService = ProductService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id") != null&&!req.getParameter("id").isBlank()) {
            productService.editCombo(req,resp);
        }else {
            productService.addCombo(req,resp);
        }

    }
}
