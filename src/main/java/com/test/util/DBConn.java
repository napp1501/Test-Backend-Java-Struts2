package com.test.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
    private static final String URL =
            "jdbc:mysql://localhost:3306/bananapp_store";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
