package com.exam.BAM.app;

import java.util.Scanner;

import com.exam.BAM.controller.ArticleController;
import com.exam.BAM.controller.Controller;
import com.exam.BAM.controller.MemberController;

public class App {
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		memberController.makeTestData();
		articleController.makeTestData();

		while (true) {

			System.out.print("명령어 ) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.isEmpty()) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if (cmdBits.length < 2) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			switch (methodName) {
			case "write":
			case "modify":
			case "delete":
			case "logout":
				if (Controller.loginedMember == null) {
					System.out.println("로그인이 필요한 기능입니다.");
					continue;
				}
				break;
			case "join":
			case "login":
				if (Controller.loginedMember != null) {
					System.out.println("로그아웃을 해주세요.");
					continue;
				}
				break;
			}
			
			Controller controller = null;
			
			if (controllerName.equals("member")) {
				controller = memberController;
			}
			
			else if (controllerName.equals("article")) {
				controller = articleController;
			}
			
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			controller.doAction(cmd, methodName);
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}