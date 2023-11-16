package com.ohgiraffers.section01.insert;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {
    public static void main(String[] args) {

        /*
         * 사용자가 메뉴를 등록 할 수 있도록 만들어주세요!
         * 등록이 완료되면 메뉴 등록 성공, 실패하면 다시 등록을 요청해주세요..
         * */

        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        // class는 heap 영역,,,, 그 안에 main...
        // 어떤 값을 넣을지 모르니, 초기화하지 않으면 실행이 되지 않는다..
        // try 안에 넣으면, 그 안에서 에러가 발생 시에, close를 해줄 수 없기 때문에 열린 객체가 되어 버린다. 그래서 밖으로 빼주는 것이다.
        PreparedStatement pstmt = null;

        int result = 0;
        String menuName = "";
        int price = 0;
        int code = 0;
        char orderable = ' ';

        Properties props = new Properties();

        try {
            props.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            System.out.println("등록할 메뉴의 이름을 입력해주세요 : ");
            menuName = sc.nextLine();
            System.out.println("등록할 메뉴의 가격을 입력해주세요 : ");
            price = sc.nextInt();
            System.out.println("등록할 메뉴의 카테고리 코드를 입력해주세요 : ");
            code = sc.nextInt(); // sc.nextInt(); 이후에는 태생적 문제로 nextLine()이 씹힙.. 그래서 이후에 빈 nextLine()값을 넣거나 이 다음처럼 charAt을 사용한다.
            System.out.println("등록할 메뉴의 판매 가능 여부를 입력해주세요 (Y / N) : ");
            orderable = sc.next().charAt(0);

            pstmt = con.prepareStatement(props.getProperty("insertMenu"));

            pstmt.setString(1, menuName);
            pstmt.setInt(2, price);
            pstmt.setInt(3, code);
            pstmt.setString(4, String.valueOf(orderable));

            result = pstmt.executeUpdate();

            try {
                throw new RuntimeException("의도적인 에러 발생");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }



            if (result == 1) {
                System.out.println("등록 완료");
            } else {
                System.out.println("다시 등록");
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
