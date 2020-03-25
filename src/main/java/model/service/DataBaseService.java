package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseService {

    private static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    private Connection connection;
    private static DataBaseService instance;

    private DataBaseService() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseService getInstance() {
        if (instance == null)
            instance = new DataBaseService();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
