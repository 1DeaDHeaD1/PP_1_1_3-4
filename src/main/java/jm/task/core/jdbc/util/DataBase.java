package jm.task.core.jdbc.util;

public class DataBase {

    private static final String URL = "jdbc:mysql://localhost";
    private static final int PORT = 3306;
    private static final String DB_NAME = "new_schema";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String HIBERNATE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HIBERNATE_SQL_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final String HIBERNATE_HBM2DDL_AUTO = "none";
    private static final boolean HIBERNATE_SHOW_SQL = false;
    private static final boolean HIBERNATE_FORMAT_SQL = true;

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

    public static String getHibernateDriver() {
        return HIBERNATE_DRIVER;
    }

    public static String getHibernateHbm2ddlAuto() {
        return HIBERNATE_HBM2DDL_AUTO;
    }

    public static String getHibernateSqlDialect() {
        return HIBERNATE_SQL_DIALECT;
    }

    public static boolean isHibernateShowSql() {
        return HIBERNATE_SHOW_SQL;
    }

    public static boolean isHibernateFormatSql() {
        return HIBERNATE_FORMAT_SQL;
    }

}
