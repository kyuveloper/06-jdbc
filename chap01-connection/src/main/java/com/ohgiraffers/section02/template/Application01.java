package com.ohgiraffers.section02.template;

import java.sql.Connection;
import com.ohgiraffers.section02.template.JDBCTemplate; // 1
import static com.ohgiraffers.section02.template.JDBCTemplate.getConnection; //2
import static com.ohgiraffers.section02.template.JDBCTemplate.close;

public class Application01 {

    public static void main(String[] args) { // 만들어진 메서드로 간단하게 연결하고 닫음
        Connection con = getConnection();
        System.out.println(con);
        close(con);
    }
}
