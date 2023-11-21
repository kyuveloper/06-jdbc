package com.ohgiraffers.opez.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {


    // 1. 데이터베이스랑 연결 확인하기
    public static Connection getConnection() {
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(new FileReader("src/main/resources/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            con = DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement statement) {
        try {
            statement.close();
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
