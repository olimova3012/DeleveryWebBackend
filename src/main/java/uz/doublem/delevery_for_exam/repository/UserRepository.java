package uz.doublem.delevery_for_exam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import uz.doublem.delevery_for_exam.entity.Product;
import uz.doublem.delevery_for_exam.entity.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository {

    EntityManagerFactory entityManagerFactory = Configurations.getEntityManagerFactory();



    public void save(Users user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(user);
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public List<Users> getUsers() {
        return null;
    }

    public void saveUser(Users user) {
    }

    public Optional<Users> getUserByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // JPQL yordamida qidiruv amalga oshirilmoqda
            Users user = entityManager.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class)
                    .setParameter("email", email)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }


    public void delete(String id) {

    }


    private static UserRepository userRepository;

    public static UserRepository getInstance() {
        if (userRepository == null)
            synchronized (UserRepository.class) {
                if (userRepository == null) {
                    userRepository = new UserRepository();
                }
            }
        return userRepository;
    }


    public Optional<Users> getUserById(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Users user = entityManager.find(Users.class, id);
            return Optional.of(user);
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            return Optional.empty();
        }
    }

    public void update(Users user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.merge(user);
        } finally {
            entityManager.getTransaction().commit();
        }
    }


    public boolean updateUserActive(String userId, boolean b) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                Users users = entityManager.find(Users.class, userId);
                users.setActive(b);
                entityManager.merge(users);
                return true;
            }catch (Exception e){
                return false;
            }
            finally {
                entityManager.getTransaction().commit();
                entityManager.close();
            }
    }

}
