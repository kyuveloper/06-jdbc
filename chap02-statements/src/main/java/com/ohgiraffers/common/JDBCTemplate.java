package com.ohgiraffers.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate { // 공통되게 사용한다는 특징 때문에

    public static Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url"); // url value 값 담음

            con = DriverManager.getConnection(url, prop);

        } catch (FileNotFoundException e) { // 첫번째줄 에러
            e.printStackTrace();
        } catch (IOException e) { //첫~세번째 줄 에러 // 하나만 두면 에러 발생시에 어떤 에러인지 정확하게 파악하기 위해서 위에 FileNotFoundException도 작성한다.
            e.printStackTrace();
        } catch (SQLException e) { // getConnection 에러
            e.printStackTrace();
        }

        return con;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        try {
            rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
