package com.ohgiraffers.opez.service;

import com.ohgiraffers.opez.model.dao.UserDAO;
import com.ohgiraffers.opez.model.vo.UserVO;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.opez.common.JDBCTemplate.*;

public class UserService {

    private UserDAO userDAO;

    public UserService(String url) {
            this.userDAO = new UserDAO(url);
    }

    public List<UserVO> findUser() {
        Connection con = getConnection();

        List<UserVO> list = userDAO.checkUser(con);

        close(con);
        return list;
    }
}
