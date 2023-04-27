package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_AGE = "age";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s int NOT NULL AUTO_INCREMENT, %s varchar(50), %s varchar(50), %s tinyint,PRIMARY KEY(%s))",
                    TABLE_NAME, COLUMN_ID, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_AGE, COLUMN_ID);
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            String sql = String.format("INSERT INTO %s (%s,%s, %s) VALUES('%s', '%s', '%d')",
                    TABLE_NAME, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_AGE, name, lastName, age);
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            String sql = String.format("DELETE FROM %s WHERE %s=%d", TABLE_NAME, COLUMN_ID, id);
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            String sql = String.format("SELECT * FROM %s", TABLE_NAME);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                result.add(new User(resultSet.getString(COLUMN_NAME), resultSet.getString(COLUMN_LASTNAME), resultSet.getByte(COLUMN_AGE)));
                result.get(result.size()-1).setId(resultSet.getLong(COLUMN_ID));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String sql = String.format("DELETE FROM %s", TABLE_NAME);
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
