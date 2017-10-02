package com.capgemini.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

// Database helper class
public class Database {
    private final String DB_URL;
    private final String DB_NAME;

    //  Database credentials
    private final String USERNAME;
    private final String PASSWORD;

    private Connection conn = null;
    private Statement stmt = null;

    public Database(String db_url, String username, String password, String database_name) {

        DB_URL = db_url;
        DB_NAME = database_name;
        USERNAME = username;
        PASSWORD = password;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/" + DB_NAME + "?useSSL=false", USERNAME,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet query(String sql) {

        System.out.println("Creating statement...");

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}