package uz.doublem.delevery_for_exam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.entity.ProductImages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImageRepository {
    EntityManagerFactory entityManagerFactory = Configurations.getEntityManagerFactory();
ProductRepository productRepository = new ProductRepository();

    public void add(ProductImages productImages) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(productImages);
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
    public void edit(ProductImages productImages) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.merge(productImages);
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
    public ProductImages get(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            return entityManager.find(ProductImages.class, id);
        }finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
    public List<ProductImages> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = new ArrayList();
        try {

            entityManager.getTransaction().begin();
            resultList = entityManager.createNativeQuery("select * from productimages", ProductImages.class).getResultList();
        } finally {
            entityManager.getTransaction().commit();
        }
        return resultList;

    }




    private static ImageRepository imageRepository;

    public static ImageRepository getInstance() {
        if (imageRepository == null)
            synchronized (ImageRepository.class) {
                if (imageRepository == null) {
                    imageRepository = new ImageRepository();
                }
            }
        return imageRepository;
    }

    public void reSetProductImage(String productId, ProductImages productImage) {
        Product product=productRepository.get(productId);
        product.setProductImages(productImage);
        productRepository.edit(product);
    }

}
