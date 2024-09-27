package uz.doublem.delevery_for_exam.contrloller.controllerImage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.doublem.delevery_for_exam.entity.ProductImages;
import uz.doublem.delevery_for_exam.repository.ImageRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class UploadController extends HttpServlet {

    public static final String DEFAULT_PATH = "D:/downloads/attachments";
    ImageRepository imageRepository = ImageRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductImages productImage = new ProductImages();
        Part file = req.getPart("file");
        System.out.println(file.getSubmittedFileName());
        InputStream inputStream = file.getInputStream();
        String fileName = file.getSubmittedFileName();
        String substring = fileName.substring(0, fileName.lastIndexOf("."));
        ProductImages productImages = imageRepository.get(substring);
        if (productImages!=null&&productImages.getId()!=null){
            String productId = req.getParameter("productId");
            imageRepository.reSetProductImage(productId,productImages);
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        productImage.setAttachmentName(fileName.substring(0, fileName.lastIndexOf(".")));
        productImage.setPrefix(fileName.substring(fileName.lastIndexOf(".")));
        productImage.setAttachmentSize(String.valueOf(file.getSize()));
        // Check if directory exists, create if not
        Path directory = Paths.get(DEFAULT_PATH);
        if (Files.notExists(directory)) {
            Files.createDirectories(directory);
        }
        // Set the path dynamically
        Path dest = directory.resolve(productImage.getId() + productImage.getPrefix());
        Files.copy(inputStream, dest, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();

        if (productImage.getAttachmentName() != null) {
            String productId = req.getParameter("productId");
            imageRepository.add(productImage);
            imageRepository.reSetProductImage(productId,productImage);
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
