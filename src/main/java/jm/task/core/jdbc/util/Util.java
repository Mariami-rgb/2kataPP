package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection connection;
    private static SessionFactory sessionFactory;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/dbtesting";
    private static final String user = "root";
    private static final String password = "aShabtamaev2001";

    public static Connection getConnection() {
        ;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(connectionURL, user, password);
            System.out.println("Connected to database" + connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
        // реализуйте настройку соеденения с БД
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, driver);
                properties.put(Environment.URL, connectionURL);
                properties.put(Environment.USER, user);
                properties.put(Environment.PASS, password);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory =configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
