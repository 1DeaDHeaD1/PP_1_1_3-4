package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static EntityManagerFactory entityManagerFactory;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DataBase.getURL() + ':' + DataBase.getPort() + '/' + DataBase.getDbName(),
                DataBase.getLogin(),
                DataBase.getPassword());
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory != null) {
            return entityManagerFactory.createEntityManager();
        }
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DataBase.getHibernateDriver());
        properties.put(Environment.DIALECT, DataBase.getHibernateSqlDialect());
        properties.put(Environment.URL, DataBase.getURL() + ':' + DataBase.getPort() + '/' + DataBase.getDbName());
        properties.put(Environment.USER, DataBase.getLogin());
        properties.put(Environment.PASS, DataBase.getPassword());
        properties.put(Environment.SHOW_SQL, DataBase.isHibernateShowSql());
        properties.put(Environment.HBM2DDL_AUTO, DataBase.getHibernateHbm2ddlAuto());

        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        entityManagerFactory = configuration.buildSessionFactory(registry);
        return entityManagerFactory.createEntityManager();

    }

}
