package uz.doublem.delevery_for_exam.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Configurations {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HIBERNATE-UNIT");
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
