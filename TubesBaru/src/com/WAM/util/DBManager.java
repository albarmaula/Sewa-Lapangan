package com.WAM.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    static final String DB_URL = "jdbc:mysql://36.95.3.227:3306/SE13";
    static final String USER = "SE13";
    static final String PASS = "basisdatarpl";

    static Connection connection;

    static void connect() throws SQLException {
        //Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connect();
        }

        return connection;
    }
}
