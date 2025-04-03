package com.berru.app.atmjfx.database;

import com.berru.app.atmjfx.utils.SpecialColor;
import org.h2.tools.Server;

import java.sql.*;

public class SingletonDBConnection {

    // Field
    // Database  Information Data
    private static final String URL = "jdbc:h2:~/h2db/user_management";
    //private static final String URL = "jdbc:h2:./h2db/user_management?" + "AUTO_SERVER=TRUE";
    //private static final String URL = "jdbc:h2:~/h2db/user_management";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    // Singleton Design pattern
    private static SingletonDBConnection instance;
    private  Connection connection;

    // Parameterless Constructor (made private to prevent external access)
    private SingletonDBConnection() {
        try {
            // Load JDBC driver
            Class.forName("org.h2.Driver");

            // Establish the database connection
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println(SpecialColor.GREEN + "Database connection successful" + SpecialColor.RESET);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(SpecialColor.RED + "Database connection failed" + SpecialColor.RESET);
            throw new RuntimeException("Database connection failed");
        }
    }

    public static synchronized SingletonDBConnection getInstance(){
        if(instance==null){
            instance= new SingletonDBConnection();
        }
        return instance;
    }

    // Calling the connection object
    public Connection getConnection() {
        return connection;
    }

    // Database Kapatmak
    public static void closeConnection(){
        if(instance!=null && instance.connection!=null){
            try {
                instance.connection.close();
                System.out.println(SpecialColor.RED+ "Veritabanı bağlantısı kapatıldı");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

    public static void dataSet() throws SQLException {
        SingletonDBConnection dbInstance = SingletonDBConnection.getInstance();
        Connection conn = dbInstance.getConnection();

        Statement stmt = conn.createStatement();

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INT PRIMARY KEY AUTO_INCREMENT , " // Doğru
                + "name VARCHAR(255), "
                + "email VARCHAR(255))";

        stmt.execute(createTableSQL);
        System.out.println("Users table created!");

        String insertDataSQL = "INSERT INTO Users (name, email) VALUES "
                + "('Ali Veli', 'ali@example.com'), "
                + "('Ayşe Fatma', 'ayse@example.com')";

        stmt.executeUpdate(insertDataSQL);
        System.out.println("Veriler eklendi!");

        String selectSQL = "SELECT * FROM Users";
        ResultSet rs = stmt.executeQuery(selectSQL);

        System.out.println("\nUsers Tablosu İçeriği:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("name") +
                    ", Email: " + rs.getString("email"));
        }
        // Close the database connection
        SingletonDBConnection.closeConnection();
    }

    public static void main(String[] args) throws SQLException {
        dataSet();
    }
} // end class