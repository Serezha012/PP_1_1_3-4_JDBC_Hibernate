package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    Util util = new Util();
    private final Connection connection = util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "   id INT AUTO_INCREMENT,\n" +
                    "   name VARCHAR(15),\n" +
                    "   lastName VARCHAR(15),\n" +
                    "   age TINYINT,\n" +
                    "   PRIMARY KEY (id))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.createStatement().execute("INSERT users (name,lastName,age) values (" + "'" + name + "'" + "," + "'" + lastName + "'" + "," + "'" + age + "'" + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            connection.createStatement().execute("DELETE FROM users WHERE id=" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println("User с именем" + resultSet.getString("name") + "добавлен в базу данных");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection.createStatement().execute("DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
