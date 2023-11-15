package com.ohgiraffers.section02.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate { // 필요할 때마다 데이터베이스의 객체를 불러오기 위해 사용할 클래스 작성

    public static Connection getConnection() { // 객체를 연결해주는 메서드
        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load (new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            con = DriverManager.getConnection(url, prop);
            // DriverManager 안에 Connection con = aDriver.driver.connect(url, info);로 인해 몸체가 생김
            // con = new Connection(); con.close(); 몸체가 없음.. 커넥션 맺은 것이 없으니 단독으로 사용 불가.. 그래서 try&catch 문으로 묶음

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con) { // 연결 닫아주는 메서드
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
