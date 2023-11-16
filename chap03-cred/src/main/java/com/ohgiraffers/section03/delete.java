package com.ohgiraffers.section03;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class delete {
    public static void main(String[] args) {
        /*
         * 사용자가 메뉴를 제거하도록
         * */

        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        int result = 0;
        String menuName = "";
        String changedName = "";
        int price = 0;
        int code = 0;
        char orderable = ' ';

        Properties props = new Properties();

        try {
            props.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            System.out.println("삭제할 메뉴의 이름을 입력해주세요 : ");
            menuName = sc.nextLine();

            pstmt = con.prepareStatement(props.getProperty("deleteMenu"));

            pstmt.setString(1, menuName);

            result = pstmt.executeUpdate();
            System.out.println(result);

            if (result>0) {
                System.out.println("삭제 완료");
            } else {
                System.out.println("삭제 실패");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
            sc.close();
        }
    }
}
