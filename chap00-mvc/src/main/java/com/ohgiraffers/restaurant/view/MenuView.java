package com.ohgiraffers.restaurant.view;

import com.ohgiraffers.restaurant.controller.MenuCtr;
import com.ohgiraffers.restaurant.model.dto.MenuDTO;
import com.ohgiraffers.restaurant.model.vo.MenuVO;
import com.ohgiraffers.restaurant.service.MenuSerivce;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuView { // 사용자가 보는 화면 (html)

    /*
    * view 계층을 예시로 만든다.
    * 이후 해당 페이지는 html로 변경된다.
    * */
    public static void runApplication() {
        Scanner sc = new Scanner(System.in);
        MenuCtr menuCtr = new MenuCtr(new MenuSerivce("src/main/resources/mapper/menu-query.xml"));

        프로그램: while (true) {
            System.out.println("메뉴 관리 프로그램 입니다.");
            System.out.println("1. 조회\n2. 수정\n3. 등록\n4. 삭제");
            System.out.println("원하는 기능을 입력해주세요 : ");

            // 1. 사용자가 원하는 기능을 선택함
            int step = sc.nextInt();

            // 2. 기능에 따라 동작됨
            switch (step) {

                // 2-1 : 메뉴 조회
                case 1 : viewMenu(menuCtr.findAllMenu());  break;

                // 2-2 : 메뉴 수정
                case 2 : break;

                // 2-3 : 메뉴 등록
                case 3 :
                    System.out.println(menuCtr.registMenu(registMenu())); break;

                // 2-4 : 메뉴 삭제
                case 4 : break;

                // default : 프로그램 종료
                default : break 프로그램;

            }

        }
    }

    public static void viewMenu(List<MenuVO> menuList) {
        if (Objects.isNull(menuList)) {
            System.out.println("조회 중 오류가 발생됨");
            System.out.println();
        } else if (menuList.size() < 0) {
            System.out.println("등록된 메뉴가 없음");
            System.out.println();
        } else {
            for (MenuVO menu : menuList) {
                System.out.println(menu);
            }
        }
    }

    public static MenuDTO registMenu() {
        MenuDTO newMenu = new MenuDTO();
        Scanner sc = new Scanner(System.in);
        System.out.println("=============================");
        System.out.println();
        System.out.print("등록할 메뉴 이름을 입력해주세요 : ");
        newMenu.setMenuName(sc.nextLine());
        System.out.print("메뉴 가격을 입력해주세요 : ");
        newMenu.setPrice(sc.nextInt());
        sc.nextLine();
        System.out.print("메뉴 코드를 입력해주세요 : ");
        newMenu.setCategory(sc.nextLine());
        System.out.print("메뉴 판매 여부를 입력해주세요 : ");
        newMenu.setStatus(sc.nextLine());


        // 일반적으로 front에서 js를 이용하여 1차 유효성 검사를 진행한다.
        return newMenu;
    }
}
