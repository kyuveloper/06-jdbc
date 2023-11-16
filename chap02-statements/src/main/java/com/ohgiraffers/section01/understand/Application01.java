package com.ohgiraffers.section01.understand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        Scanner sc = new Scanner(System.in);

        try {
            stmt = con.createStatement();
            String getMoney = "SELECT " +
                                "EMP_ID, " +
                                "EMP_NAME, " +
                                "PHONE " +
                            "FROM EMPLOYEE " +
                            "LIMIT 1 ";
            rset = stmt.executeQuery(getMoney);
            while (rset.next()) {
                System.out.println("가장 많은 급여를 받는 사원 : " + rset.getString("EMP_ID") + " / " + rset.getString("EMP_NAME") + " / " + rset.getString("PHONE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            close(con);
            close(rset);
            sc.close();
        }
    }
}
