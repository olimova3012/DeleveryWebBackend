package uz.doublem.delevery_for_exam.contrloller.controllerImage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.repository.ImageRepository;

import java.io.IOException;

@WebServlet("/images")
public class ImageController extends HttpServlet {
    ImageRepository imageRepository = ImageRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("attachments",imageRepository.getAll());

        req.getRequestDispatcher("/images.jsp").forward(req, resp);
    }
}
