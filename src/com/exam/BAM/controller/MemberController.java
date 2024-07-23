package com.exam.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exam.BAM.dto.Member;
import com.exam.BAM.util.Util;

public class MemberController {
	private Scanner sc;
	private List<Member> members;
	private int lastMemberId;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = new ArrayList<>();
		this.lastMemberId = 0;
	}

	public void doJoin() {
		String loginId = null;
		String loginPw = null;
		String name = null;

		while (true) {
			System.out.print("아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.isEmpty()) {
				System.out.println("필수 입력 정보입니다.");
				continue;
			}

			boolean isLoginIdDup = false;

			for (Member member : members) {
				if (loginId.equals(member.getLoginId())) {
					isLoginIdDup = true;
					break;
				}
			}
			if (isLoginIdDup) {
				System.out.printf("[ %s ]는 이미 존재하는 아이디입니다 !!\n", loginId);
				continue;
			}

			System.out.printf("[ %s ]는 사용가능한 아이디입니다 !!\n", loginId);
			break;
		}

		while (true) {
			System.out.print("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.isEmpty()) {
				System.out.println("필수 입력 정보입니다.");
				continue;
			}

			System.out.print("비밀번호 확인 : ");
			String chkLoginPw = sc.nextLine().trim();

			if (chkLoginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}

		while (true) {
			System.out.print("이름 : ");
			name = sc.nextLine().trim();

			if (name.isEmpty()) {
				System.out.println("필수 입력 정보입니다.");
				continue;
			}
			break;
		}

		members.add(new Member(++lastMemberId, Util.getDateStr(), loginId, loginPw, name));
		System.out.printf("[ %s ]님 회원가입을 축하합니다 !!\n", name);
	}

	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개를 생성했습니다!");
		for (int i = 1; i <= 3; i++) {
			members.add(new Member(++lastMemberId, Util.getDateStr(), ("user" + i), ("user" + i), ("유저" + i)));
		}
	}
}