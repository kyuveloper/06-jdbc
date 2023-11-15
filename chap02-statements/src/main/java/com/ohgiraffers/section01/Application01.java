package com.ohgiraffers.section01;

import static com.ohgiraffers.common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        // statement : 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스
        Statement stmt = null;

        // ResultSet : select의 결과 집합을 받아 올 용도의 인터페이스
        ResultSet rset = null;
        try {
            stmt = con.createStatement();

            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE"); // 데이터베이스 서버에 이 명령어를 날림
            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }
    }
}
