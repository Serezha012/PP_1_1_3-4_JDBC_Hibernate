package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


}
