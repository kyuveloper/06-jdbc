package com.ohgiraffers.opez.controller;


import com.ohgiraffers.opez.model.vo.UserVO;
import com.ohgiraffers.opez.service.UserService;

import java.util.List;
import java.util.Objects;

/*
* 사용자가 입력한 요청을 받아 유효성 체크 완료 후에 Service로 전달하고,
* 이후 Service의 반환값을 페이지로 반환하는 역할을 한다.
* */
public class UserCtr {

    private UserService userService;

    public UserCtr(UserService userService) {
        this.userService = userService;
    }

    public List<UserVO> checkUser() {
        List<UserVO> list = userService.findUser();

        if (Objects.isNull(list)) {
            return null;
        }

        return list;
    }
}
