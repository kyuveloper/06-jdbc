package com.ohgiraffers.restaurant.service;

import com.ohgiraffers.restaurant.model.dao.MenuDAO;
import com.ohgiraffers.restaurant.model.dto.MenuDTO;
import com.ohgiraffers.restaurant.model.vo.MenuVO;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import static com.ohgiraffers.restaurant.common.JDBCTemplate.*;

public class MenuSerivce {

    private MenuDAO menuDAO;

    public MenuSerivce(String url) {
        this.menuDAO = new MenuDAO(url);
    }

    public List<MenuVO> findMenu() {
        // DB 연결
        Connection con = getConnection();

        // business 로직   EX: price > 0
        List<MenuVO> list = menuDAO.findAllMenu(con);

        close(con);
        return list;


    }

    public int registMenu(MenuDTO menuDTO) {
        Connection con = getConnection();
        List<String> category = menuDAO.findAllCategoryCode(con);

        if (!category.contains(menuDTO.getCategory())){
            return 0;
        }
        if (menuDTO.getPrice() <= 1000) {
            return 0;
        }

        int result = menuDAO.registMenu(con, menuDTO);

        close(con);

        return result;
    }
}
