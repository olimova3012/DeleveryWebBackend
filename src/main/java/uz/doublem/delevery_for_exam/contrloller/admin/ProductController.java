package uz.doublem.delevery_for_exam.contrloller.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductController extends HttpServlet {

    ProductRepository repository = ProductRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = repository.getAll(Product.class);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/views/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product =

                Product
                        .builder()
                        .name(name)
                        .description(description)
                        .price(price)
                        .build();
        repository.add(product);
        resp.sendRedirect("/product");
    }
}
