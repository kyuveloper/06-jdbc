package com.ohgiraffers.opez.model.dao;

import com.ohgiraffers.opez.model.vo.UserVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.opez.common.JDBCTemplate.*;

public class UserDAO { // Data Access Object
    Properties prop = new Properties();

    public UserDAO(String url) {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<UserVO> checkUser(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        List<UserVO> resultList = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(prop.getProperty("checkUser"));

            while (rset.next()) {
                resultList.add(new UserVO(rset.getInt(1),
                                          rset.getString(2),
                                          rset.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(stmt);
        }
        return resultList;
    }
}
