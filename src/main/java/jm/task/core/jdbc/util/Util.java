package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public Connection getConnection() {
        String PASSWORD = "root";
        String USERNAME = "root";
        Connection connection = null;

        try {
            // реализуйте настройку соеденения с БД
            String URL = "jdbc:mysql://localhost:3306/user";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return connection;

    }


}
