package com.ohgiraffers.opez.controller;

import com.ohgiraffers.opez.model.dto.UserDTO;
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
    
    public String registUser(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            return "유저 정보가 입력되지 않았습니다. 다시 입력 해주세요.";
        }
        if (userDTO.getUserName() == null || userDTO.getUserName().equals("")) {
            return "유저 정보가 입력되지 않았습니다. 다시 입력 해주세요.";
        }

        if (!userDTO.getUserTier().equals("아이언") && !userDTO.getUserTier().equals("브론즈") && !userDTO.getUserTier().equals("실버")&&!userDTO.getUserTier().equals("골드")&&!userDTO.getUserTier().equals("플레티넘")&&!userDTO.getUserTier().equals("에메랄드")&&!userDTO.getUserTier().equals("다이아몬드")&&!userDTO.getUserTier().equals("마스터")&&!userDTO.getUserTier().equals("그랜드 마스터")&&!userDTO.getUserTier().equals("챌린저")) {
            return "티어 이름이 정확하지 않습니다. 다시 입력 해주세요";
        }

        int result = userService.registUser(userDTO);

        if (result <= 0) {
            return "등록 중 오류가 발생됨";
        } else {
            return "등록 완료";
        }
    }

    public String editUser(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            return "유저 정보를 잘못 입력했습니다.";
        }
        if (userDTO.getUserName() == null || userDTO.getUserName().equals("")) {
            return "유저 정보가 입력되지 않았습니다. 다시 입력 해주세요.";
        }

        int result = userService.registUser(userDTO);

        if (result <= 0) {
            return "등록 중 오류가 발생됨";
        } else  {
            return "등록 완료";
        }
    }

    /*public String deleteUser(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            return "유저 정보가 입력되지 않았습니다. 다시 입력 해주세요.";
        }
        if (userDTO.getUserName() == null || userDTO.getUserName().equals("")) {
            return "유저 정보가 존재하지 않습니다.";
        }
        int result = userService.deleteUser(userDTO);

        if (result <= 0) {
            return "삭제 중 오류가 발생됨";
        } else  {
            return "삭제 완료";
        }
    }*/
}
