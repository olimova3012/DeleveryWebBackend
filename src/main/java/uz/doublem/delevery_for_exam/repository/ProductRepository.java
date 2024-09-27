package uz.doublem.delevery_for_exam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.entity.Combo;
import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.entity.SuperProduct;
import uz.doublem.delevery_for_exam.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    EntityManagerFactory entityManagerFactory = Configurations.getEntityManagerFactory();


    public void add(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(product);
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    public List<Product> getProductByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList;
        try {
            entityManager.getTransaction().begin();
            resultList = entityManager.createNativeQuery("select * from product where name = '';", Product.class).getResultList();
        } finally {
            entityManager.getTransaction().commit();
        }
        return resultList;
    }

    public void edit(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.merge(product);
        } finally {
            entityManager.getTransaction().commit();
        }
    }


    public <T> List getAll(Class<T> entityType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = new ArrayList<T>();
        try {

            entityManager.getTransaction().begin();
            String query = String.format("SELECT * FROM %s", entityType.getSimpleName());
            resultList = entityManager.createNativeQuery(query, entityType).getResultList();
            return resultList;
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }

    }



    private static ProductRepository productRepository;

    public static ProductRepository getInstance() {
        if (productRepository == null)
            synchronized (ProductRepository.class) {
                if (productRepository == null) {
                    productRepository = new ProductRepository();
                }
            }
        return productRepository;
    }
    public Product get(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Integer id1 = Integer.valueOf(id);
            entityManager.getTransaction().begin();
            return entityManager.find(Product.class, id1);
        }finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public void delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
        } finally {

            entityManager.getTransaction().commit();
        }
    }

    public boolean updateProductActive(String productId, boolean b) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, productId);
            product.setActive(b);
            entityManager.merge(product);
            return true;
        }catch (Exception e){
            return false;
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public void addCombo2(Combo combo){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(combo);
        }finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public boolean addCombo(Combo combo, String[] productIds) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            // Combo'ni saqlash
            addCombo2(combo);
            // Har bir product uchun SuperProduct yaratish va Combo bilan bog'lash
            for (String productId : productIds) {
                Product product = get(productId);
                if (product != null) {
                    SuperProduct superProduct = new SuperProduct();
                    superProduct.setProduct(product);
                    superProduct.setCombo(List.of(combo));
                    superProduct.setActive(true);
                    superProduct.setOptional(false); // Agar optional bo'lsa o'zgartirishingiz mumkin
                    // SuperProduct'ni saqlash
                    entityManager.persist(superProduct);
                }
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                return false;
            }
            e.printStackTrace(); // Xatolikni log qilishingiz mumkin
        } finally {
            entityManager.close();
        }
        return false;
    }

    public List<SuperProduct> getSuperProductByComboId(Integer comboId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<SuperProduct> resultList = null;
        try {
            entityManager.getTransaction().begin();

            // Query to fetch SuperProducts by Combo ID
            String jpql = "SELECT sp FROM SuperProduct sp JOIN sp.combo c WHERE c.id = :comboId";
            TypedQuery<SuperProduct> query = entityManager.createQuery(jpql, SuperProduct.class);
            query.setParameter("comboId", comboId);
            resultList = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return resultList;
    }


    public List<Product> getSearchProduct(String search) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Product> resultList = null;
        try {
            entityManager.getTransaction().begin();
            String jpql = "SELECT pr FROM Product pr  WHERE pr.name ilike % :search %";
            TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
            query.setParameter("search", search);
            resultList = query.getResultList();
            return resultList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
}