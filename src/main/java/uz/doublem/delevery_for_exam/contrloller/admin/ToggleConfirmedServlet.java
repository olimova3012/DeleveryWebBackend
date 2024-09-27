package uz.doublem.delevery_for_exam.contrloller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.repository.ProductRepository;
import uz.doublem.delevery_for_exam.repository.UserRepository;

@WebServlet("/ToggleConfirmedServlet")
public class ToggleConfirmedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductRepository productRepository = ProductRepository.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        String newStatus = request.getParameter("newStatus");

        // Call the repository method to update the status

        boolean updated = productRepository.updateProductActive(productId, Boolean.parseBoolean(newStatus));

        if (updated) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
