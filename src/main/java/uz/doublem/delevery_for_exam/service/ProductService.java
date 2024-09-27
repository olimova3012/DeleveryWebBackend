package uz.doublem.delevery_for_exam.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.entity.Combo;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.entity.SuperProduct;
import uz.doublem.delevery_for_exam.repository.CategoryRepository;
import uz.doublem.delevery_for_exam.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;

public class ProductService {
    private static ProductRepository productRepository = ProductRepository.getInstance();
    private CategoryRepository categoryRepository = CategoryRepository.getInstance();



    private static ProductService productService;

    public static ProductService getInstance() {
        if (productService == null)
            synchronized (ProductService.class) {
                if (productService == null) {
                    productService = new ProductService();
                }
            }
        return productService;
    }
    @SneakyThrows
    public void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String productDescription = req.getParameter("description");
        String productPrice = req.getParameter("price");
        String productCategoryId = req.getParameter("category");
        Category category = categoryRepository.get(productCategoryId);
        if (name != null && !name.trim().isEmpty()) {
            Product product = productRepository.get(id);
            product.setName(name);
            product.setDescription(productDescription);
            product.setPrice(Double.parseDouble(productPrice));
            product.setCategory(category);
            productRepository.edit(product);
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }
    }
    @SneakyThrows
    public void addProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String productDescription = req.getParameter("description");
        String productPrice = req.getParameter("price");
        String productCategoryId = req.getParameter("category");
        Category category = categoryRepository.get(productCategoryId);
        if (name != null && !name.trim().isEmpty()) {
            Product product = new Product();
            product.setName(name);
            product.setDescription(productDescription);
            product.setPrice(Double.parseDouble(productPrice));
            product.setCategory(category);
            productRepository.add(product);
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("error");
        }
    }



    public HashMap<Category, List<Product>> getProductsByCategory() {
        HashMap<Category, List<Product>> productsByCategory = new HashMap<>();
        List<Category> categories = categoryRepository.getAll();
        for (Category category : categories) {
            List<Product> products = category.getProducts();
            if (products != null && !products.isEmpty()) {
                productsByCategory.put(category, products);
            }
        }
        return productsByCategory;
    }
    @SneakyThrows
    public void addCombo(HttpServletRequest req, HttpServletResponse resp) {
        // Request'dan olingan ma'lumotlar
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String productCategoryId = req.getParameter("category");
        Category category = categoryRepository.get(productCategoryId); // Assuming categoryRepository.get returns a Category object
        String selectedProducts = req.getParameter("selectedProducts");
        String[] productIds = selectedProducts.split(",");


        Combo combo = Combo.builder()
                .name(name)
                .description(description)
                .price(Double.parseDouble(price))
                .category(category)
                .build();

        boolean b = productRepository.addCombo(combo, productIds);
        if (b){
            resp.getWriter().write("success");
        }else {
            resp.getWriter().write("error");
        }


    }
    public static String getProductsInCategory(String comboId){
        StringBuilder products = new StringBuilder();
        Integer comboId1 = Integer.valueOf(comboId);
        List<SuperProduct> superProductByComboId = productRepository.getSuperProductByComboId(comboId1);
        superProductByComboId.forEach(superProduct -> {
            products.append(superProduct.getProduct().getId());
            products.append(",");
        });
        products.deleteCharAt(products.length() - 1);
        return products.toString();
    }



    @SneakyThrows
    public void editCombo(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("id").isBlank()) {
            resp.getWriter().write("success");
            resp.sendRedirect("views/adminProduct.jsp");
        } else {
            resp.getWriter().write("error");
            resp.sendRedirect("views/adminProduct.jsp");
        }
    }

    public String getGetProductsInCategory(String  comboId) {
        return getProductsInCategory(comboId);
    }
}
