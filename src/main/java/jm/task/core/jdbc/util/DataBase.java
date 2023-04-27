package jm.task.core.jdbc.util;

public class DataBase {

    private static final String URL = "jdbc:mysql://localhost";
    private static final int PORT = 3306;
    private static final String DB_NAME = "new_schema";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static String getURL() {
        return URL;
    }
    public static String getPort() {
        return String.valueOf(PORT);
    }
    public static String getDbName() {
        return DB_NAME;
    }
    public static String getLogin() {
        return LOGIN;
    }

    public static String getPassword() {
        return PASSWORD;
    }

}
