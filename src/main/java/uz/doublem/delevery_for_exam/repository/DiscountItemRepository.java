package uz.doublem.delevery_for_exam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import uz.doublem.delevery_for_exam.entity.Category;
import uz.doublem.delevery_for_exam.entity.DiscountItem;

import java.util.ArrayList;
import java.util.List;

public class DiscountItemRepository {
    EntityManagerFactory entityManagerFactory = Configurations.getEntityManagerFactory();
    public List<DiscountItem> getAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = new ArrayList();
        try {

            entityManager.getTransaction().begin();
            resultList = entityManager.createNativeQuery("select * from discount_items", DiscountItem.class).getResultList();
        } finally {
            entityManager.getTransaction().commit();
        }
        return resultList;

    }

    private static DiscountItemRepository discountRep;

    public static DiscountItemRepository getInstance() {
        if (discountRep == null)
            synchronized (DiscountItemRepository.class) {
                if (discountRep == null) {
                    discountRep = new DiscountItemRepository();
                }
            }
        return discountRep;
    }
}
