package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users ( id INT AUTO_INCREMENT, name VARCHAR(15), lastName VARCHAR(15), age TINYINT, PRIMARY KEY (id) )";
        try {

//            Вот так не получилось сделать, как не пытался.
//            Как я понял, так можно передавать только значения и в создании таблицы так сделать нельзя. Я прав?
//            Буду благодарен если ответить в слаке на этот вопрос, или в комментарии по задаче.
//            PreparedStatement preparedStatement = connection.prepareStatement(create);
//            preparedStatement.setString(1, "users");
//            preparedStatement.setString(1, "id");
//            preparedStatement.setString(2, "name");
//            preparedStatement.setString(3, "lastName");
//            preparedStatement.setString(4, "age");
//            preparedStatement.setString(5, "id");
//            preparedStatement.execute();

            connection.prepareStatement(create).execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try {
            connection.prepareStatement(drop).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String save = "INSERT users (name,lastName,age) values (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        String delete = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String select = "SELECT * FROM users";
        try {
            ResultSet resultSet = connection.prepareStatement(select).executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println("User с именем " + resultSet.getString("name") + " добавлен в базу данных");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String delete = "DELETE FROM users";
        try {
            connection.prepareStatement(delete).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
