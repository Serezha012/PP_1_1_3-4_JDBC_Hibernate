package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String USERNAME = "Serezha012";
    private static final String PASSWORD = "Serega12";
    private static final String URL = "jdbc:mysql://localhost:3306/user";

    public static Connection getConnection() {

        Connection connection;

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return connection;

    }

    private static final Properties properties = new Properties();


    static {
        properties.setProperty("hibernate.driver_class", "com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/user");
        properties.setProperty("hibernate.connection.username", "Serezha012");
        properties.setProperty("hibernate.connection.password", "Serega12");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.current_session_context_class", "thread");
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().addProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return sessionFactory;
    }


}
