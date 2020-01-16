package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLController {
    private final String POSTGRES_USERNAME = "vldata_backend";
    private final String POSTGRES_PASSWORD = "vlbackend";
    private final String POSTGRES_URL = "jdbc:postgresql://192.168.56.101:5432/vldata";

    private Connection conn;

    private static PostgreSQLController instance;

    private PostgreSQLController() {
        try {
            conn = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USERNAME, POSTGRES_PASSWORD);
            System.out.println("Connected to PostgreSQL Database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public static PostgreSQLController getInstance() {
         if(PostgreSQLController.instance == null) {
             PostgreSQLController.instance = new PostgreSQLController();
         }
         return instance;
    }
}
