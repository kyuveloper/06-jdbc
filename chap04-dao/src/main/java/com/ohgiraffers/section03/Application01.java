package com.ohgiraffers.section03;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {
        /* Transaction */
        Connection con = getConnection();

        try {
            con.setAutoCommit(false);
            /*
            * if(result < 0) {
            *   con.rollback();
            * }
            * */
            con.commit();
            System.out.println("autoCommit의 현재값 : ");
        } catch (SQLException e) {
            try {
                con.rollback(); // 에러 발생 시에 try에서 실행 된 것들 롤백
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
