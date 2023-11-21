package com.ohgiraffers.opez.model.dao;

import com.ohgiraffers.opez.model.dto.UserDTO;
import com.ohgiraffers.opez.model.vo.UserVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.opez.common.JDBCTemplate.close;

public class UserDAO {

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

    public List<String> findAllUserName(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        List<String> resultList = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(prop.getProperty("findAllUserName"));

            while (rset.next()) {
                resultList.add(rset.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(stmt);
        }
        return resultList;
    }

    /*public List<String> findUserTier(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        List<String> resultList = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(prop.getProperty("findUserTier"));

            while (rset.next()) {
                resultList.add(rset.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(stmt);
        }
        return resultList;
    }*/

    public int registUser(Connection con, UserDTO userDTO) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            stmt = con.prepareStatement(prop.getProperty("insertUser"));
            stmt.setString(1, userDTO.getUserName());
            stmt.setString(2, userDTO.getUserTier());

            result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }
        return result;
    }

    public int editUser(Connection con, UserDTO userDTO) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            pstmt = con.prepareStatement(prop.getProperty("editUser"));
            pstmt.setString(1, userDTO.getUserName());
            pstmt.setString(2, userDTO.getUserName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    /*public String deleteUser(Connection con, UserDTO userDTO) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            pstmt = con.prepareStatement(prop.getProperty("deleteUser"));
            pstmt.setString(1, userDTO.getUserName());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }*/
}
