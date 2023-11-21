package com.ohgiraffers.opez.service;

import com.ohgiraffers.opez.model.dao.UserDAO;
import com.ohgiraffers.opez.model.dto.UserDTO;
import com.ohgiraffers.opez.model.vo.UserVO;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.opez.common.JDBCTemplate.close;
import static com.ohgiraffers.opez.common.JDBCTemplate.getConnection;

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

    public int registUser(UserDTO userDTO) {
        Connection con = getConnection();
        List<String> users = userDAO.findAllUserName(con);

        if (users.contains(userDTO.getUserName())) {
            return 0;
        }

        int result = userDAO.registUser(con, userDTO);

        close(con);

        return result;
    }

    public int editUser(UserDTO userDTO) {
        Connection con = getConnection();
        List<String> user = userDAO.findAllUserName(con);

        if (user.contains(userDTO.getUserName())) {
            return 0;
        }

        int result = userDAO.editUser(con, userDTO);

        close(con);

        return result;
    }

    /*public int deleteUser(UserDTO userDTO) {
        Connection con = getConnection();
        String userName = userDAO.deleteUser(con, userDTO);

        if (!userName.contains(userDTO.getUserName())) {
            return 0;
        }

        int result = userDAO.deleteUser(con, userDTO);

        close(con);

        return result;
    }*/
}
