package com.ohgiraffers.common;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class JDBCTemplate {

    private static Connection con;
    public static Connection getConnection() {
        Properties props = new Properties();
        try {
            if (con == null || con.isClosed()) {
                try {
                    props.load(new FileReader("src/main/resources/config/connection-info.properties"));
                    String driver = props.getProperty("driver");
                    String url = props.getProperty("url");
                    Class.forName(driver);
                    con = DriverManager.getConnection(url, props);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static Connection getConnection2() {
        Connection con2 = null;
        Properties props = new Properties();
        try {
                props.load(new FileReader("src/main/resources/config/connection-info.properties"));
                String driver = props.getProperty("driver");
                String url = props.getProperty("url");
                Class.forName(driver);
                con2 = DriverManager.getConnection(url, props);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        return con2;
    }


    // close 작동 구문?
    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset) {
        try {
            rset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
