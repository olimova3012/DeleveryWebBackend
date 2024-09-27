package uz.doublem.delevery_for_exam.contrloller.controllerImage;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.doublem.delevery_for_exam.entity.ProductImages;
import uz.doublem.delevery_for_exam.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


@WebServlet("/download")
public class DownloadController extends HttpServlet {
    private ImageRepository imageRepository = ImageRepository.getInstance();
    String path = UploadController.DEFAULT_PATH;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        resp.setContentType("application/octet-stream");
        ProductImages productImages = imageRepository.get(id);
        resp.setHeader("Content-Disposition","attachment;fileName=\""+productImages.getAttachmentName()+productImages.getPrefix()+"\"");
        String way = path + "/" + id + productImages.getPrefix();
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(Paths.get(way),outputStream);
    }
}
