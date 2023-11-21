package com.ohgiraffers.restaurant.controller;

import com.ohgiraffers.restaurant.model.dto.MenuDTO;
import com.ohgiraffers.restaurant.model.vo.MenuVO;
import com.ohgiraffers.restaurant.service.MenuSerivce;

import java.util.List;
import java.util.Objects;


/*
* 사용자의 요청을 받아 유효성 체크를 하고 요청을 service로 전달하고,
* 이후 service의 반환값을 페이지로 반환하는 역할을 하는 클래스
* */
public class MenuCtr { // 사용자가 입력한 기능을 받는 메서드

    private MenuSerivce menuSerivce;

    public MenuCtr(MenuSerivce menuSerivce) {
        this.menuSerivce = menuSerivce;
    }

    public List<MenuVO> findAllMenu() {
        // 값을 확인한다.
        List<MenuVO> list = menuSerivce.findMenu();

        // 반환값 확인 후 응답 값을 지정
        if (Objects.isNull(list)) {
            return null;
        }

        return list;
    }

    public int modifyMenu(int code) {
        System.out.println("modify code : " + code);
        return 0;
    }

    public String registMenu(MenuDTO menuDTO) {
        if (Objects.isNull(menuDTO)) {
            return "메뉴 정보가 존재하지 않습니다. 다시 입력 해주세요.";
        }
        if (menuDTO.getMenuName() == null || menuDTO.getMenuName().equals("")) {
            return "메뉴 이름을 등록해주세요.";
        }
        if (menuDTO.getPrice() <= 0) {
            return "메뉴 가격은 음수일 수 없습니다.";
        }
        if (menuDTO.getCategory() == null || menuDTO.getCategory().equals("")){
            return "카테고리 코드는 필수 입력 사항입니다.";
        }
        if (menuDTO.getStatus() == null || menuDTO.getStatus().equals("")) {
            return "판매 여부를 입력해주세요.";
        }
        // 유효성 검사가 끝난 것들을 아래로 보내기
        int result = menuSerivce.registMenu(menuDTO);

        if (result <= 0) {
            return "등록 중 오류가 발생됨";
        } else {
            return "등록 완료";
        }
    }

    public int deleteMenu(int code) {
        System.out.println("code : " + code);
        return 0;
    }
}
