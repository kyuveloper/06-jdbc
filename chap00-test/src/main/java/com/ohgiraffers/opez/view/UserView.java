package com.ohgiraffers.opez.view;

import com.ohgiraffers.opez.controller.UserCtr;
import com.ohgiraffers.opez.model.vo.UserVO;
import com.ohgiraffers.opez.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserView {
    /*
    * 사용자가 보는 화면을 작성한다.
    * 이 코드는 이후에 html로 대체된다.
    * */
    public static void runApplication() {
        Scanner sc = new Scanner(System.in);
        UserCtr userCtr = new UserCtr(new UserService("src/main/resources/mapper/master-query.xml"));

        loop: while (true) {
            System.out.println("리그오브레전드 데이터 관리 시스템입니다.");
            System.out.println("1. 조회\n2. 수정\n3. 등록\n4. 삭제");
            System.out.println("원하는 기능을 입력해주세요 : ");

            // 1. 사용자가 원하는 기능을 선택함
            int choice = sc.nextInt();
            // 2. 기능에 따라 동작됨
            switch (choice) {
                case 1 : viewUser(userCtr.checkUser()); break;
                default : break loop;
            }
        }
    }

    public static void viewUser(List<UserVO> menuList) {
        if (Objects.isNull(menuList)) {
            System.out.println("조회 중 오류가 발생됨");
        } else if (menuList.size() == 0) {
            System.out.println("등록된 유저가 없음");
        } else {
            for (UserVO menu : menuList) {
                System.out.println(menu);
            }
        }
    }
}
