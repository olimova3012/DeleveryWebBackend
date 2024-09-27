package uz.doublem.delevery_for_exam.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.repository.CategoryRepository;
import uz.doublem.delevery_for_exam.repository.ProductRepository;

public class CategoryService {
    private ProductRepository productRepository = ProductRepository.getInstance();
    private CategoryRepository categoryRepository = CategoryRepository.getInstance();








    private static CategoryService categoryService;

    public static CategoryService getInstance() {
        if (categoryService == null)
            synchronized (CategoryService.class) {
                if (categoryService == null) {
                    categoryService = new CategoryService();
                }
            }
        return categoryService;
    }
    @SneakyThrows
    public void editCategory(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (name != null && !name.trim().isEmpty()) {
            Category category = categoryRepository.get(id);
            category.setName(name);

            categoryRepository.edit(category);

            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }
    }
    @SneakyThrows
    public void addCategory(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            Category category = new Category();
            category.setName(name);
            categoryRepository.add(category);

            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }
    }
}
