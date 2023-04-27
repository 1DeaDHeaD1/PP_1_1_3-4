package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DataBase.getURL() + ':' + DataBase.getPort() + '/' + DataBase.getDbName(),
                DataBase.getLogin(),
                DataBase.getPassword());
    }
}
