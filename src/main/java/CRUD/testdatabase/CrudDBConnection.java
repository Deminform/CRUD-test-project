package CRUD.testdatabase;

import java.sql.*;

public class CrudDBConnection {
    private static String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
    private static String NAME = "root";
    private static String PASSWORD = "root";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public CrudDBConnection() {
        try {
            this.connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}