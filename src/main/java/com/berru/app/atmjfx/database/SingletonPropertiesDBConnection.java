package com.berru.app.atmjfx.database;

import org.h2.tools.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SingletonPropertiesDBConnection {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static SingletonPropertiesDBConnection instance;
    private Connection connection;

    private SingletonPropertiesDBConnection() {
        try {
            loadDatabaseConfig(); // config.properties dosyasını oku
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Database connection successful.");


            H2DbStarting();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Database connection failed!");
        }
    }

    private void H2DbStarting() {
        try {
            Server server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Web Console is running at: http://localhost:8082");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadDatabaseConfig() {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);

            URL = properties.getProperty("db.url", "jdbc:h2:./h2db/user_management");
            USERNAME = properties.getProperty("db.username", "sa");
            PASSWORD = properties.getProperty("db.password", "");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load database configuration!");
        }
    }

    public static synchronized SingletonPropertiesDBConnection getInstance() {
        if (instance == null) {
            instance = new SingletonPropertiesDBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                System.out.println("🔒 Database connection closed.");
            } catch (SQLException e) {
                throw new RuntimeException("❌ Error occurred while closing the connection!", e);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
    }
}
