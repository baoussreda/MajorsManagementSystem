package com.example.affectation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe permettant la connexion avec ma base de données(university)
public class DBConnect {

    public static Connection conn;
    public static String url = "jdbc:mysql://localhost/university";
    public static String user = "root";
    public static String pass = "";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }

        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn != null && !conn.isClosed()) return conn;
        connect();
        return conn;

    }
}