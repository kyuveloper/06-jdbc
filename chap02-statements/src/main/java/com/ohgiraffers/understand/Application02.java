package com.ohgiraffers.understand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {
    public static void main(String[] args) {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        Scanner sc = new Scanner(System.in);

        try {
            stmt = con.createStatement();
            String sunInfo = "SELECT " +
                                "e.EMP_ID, " +
                                "e.EMP_NAME, " +
                                "e.PHONE, " +
                                "j.JOB_NAME " +
                            "FROM EMPLOYEE e " +
                            "JOIN JOB j ON e.JOB_CODE = j.JOB_CODE " +
                            "WHERE EMP_NAME = '선동일'";
            rset = stmt.executeQuery(sunInfo);
            while (rset.next()) {
                System.out.println(rset.getString("e.EMP_ID") + " " + rset.getString("e.EMP_NAME") + " " +
                        rset.getString("e.PHONE") + " " + rset.getString("j.JOB_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(stmt);
            close(rset);
            sc.close();
        }
    }
}
