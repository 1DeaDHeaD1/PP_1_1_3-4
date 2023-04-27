package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_AGE = "age";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s int NOT NULL AUTO_INCREMENT, %s varchar(50), %s varchar(50), %s tinyint,PRIMARY KEY(%s))",
                TABLE_NAME, COLUMN_ID, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_AGE, COLUMN_ID);
        try {
            transaction.begin();
            entityManager.createNativeQuery(sql, User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void dropUsersTable() {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        try {
            transaction.begin();
            entityManager.createNativeQuery(sql, User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String hql = "SELECT u FROM User u WHERE u.id=:id";
        try {
            transaction.begin();
            entityManager.remove(entityManager.createQuery(hql, User.class).setParameter("id", id).setMaxResults(1).getResultList().stream().findFirst().orElse(null));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = Util.getEntityManager();
        String hql = "SELECT u FROM User u";
        try {
            return entityManager.createQuery(hql, User.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        EntityManager entityManager = Util.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String hql = "DELETE FROM User";
        try {
            transaction.begin();
            entityManager.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
