package com.ohgiraffers.section02;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class update {
    public static void main(String[] args) {
        /*
         * 사용자가 메뉴를 업데이트 할 수 있도록
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

            System.out.println("수정할 메뉴의 이름을 입력해주세요 : ");
            menuName = sc.nextLine();
            System.out.println("수정된 메뉴의 이름을 입력해주세요 : ");
            changedName = sc.nextLine();
            System.out.println("수정할 메뉴의 가격을 입력해주세요 : ");
            price = sc.nextInt();
            System.out.println("수정할 메뉴의 카테고리 코드를 입력해주세요 : ");
            code = sc.nextInt();
            System.out.println("수정할 메뉴의 판매 가능 여부 입력해주세요 (Y/N) : ");
            orderable = sc.next().charAt(0);

            pstmt = con.prepareStatement(props.getProperty("updateMenu"));

            pstmt.setString(5, menuName);
            pstmt.setString(1, changedName);
            pstmt.setInt(2, price);
            pstmt.setInt(3, code);
            pstmt.setString(4, String.valueOf(orderable));

            result = pstmt.executeUpdate();
            System.out.println(result);

            if (result>0) {
                System.out.println("수정 완료");
            } else {
                System.out.println("수정 실패");
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
