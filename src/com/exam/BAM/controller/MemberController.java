package com.exam.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exam.BAM.dto.Member;
import com.exam.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Member loginedMember;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = new ArrayList<>();
		this.lastId = 0;
		this.loginedMember = null;
	}

	@Override
	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			dologin();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
		}
	}

	private void doJoin() {
		String loginId = null;
		String loginPw = null;
		String name = null;

		while (true) {
			System.out.print("아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.isEmpty()) {
				System.out.println("아이디는 필수 입력 정보입니다.");
				continue;
			}

			Member member = getMemberByLoginId(loginId);
			
			if (member != null) {
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
				System.out.println("비밀번호는 필수 입력 정보입니다.");
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

		members.add(new Member(++lastId, Util.getDateStr(), loginId, loginPw, name));
		System.out.printf("[ %s ]님 회원가입을 축하합니다 !!\n", name);
	}

	private void dologin() {
		if (loginedMember != null) {
			System.out.println("이미 로그인 상태입니다.");
			return;
		}

		System.out.print("아이디 : ");
		String loginId = sc.nextLine().trim();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine().trim();

		if (loginId.isEmpty()) {
			System.out.println("아이디를 입력해주세요.");
			return;
		}

		if (loginPw.isEmpty()) {
			System.out.println("비밀번호를 입력해주세요.");
			return;
		}

		Member foundMember = getMemberByLoginId(loginId);

		if (foundMember == null) {
			System.out.printf("[ %s ]은(는) 존재하지 않는 아이디입니다.\n", loginId);
			return;
		}
		
		if (foundMember.getLoginPw().equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}

		this.loginedMember = foundMember;
		System.out.printf("[ %s ]님 로그인 되었습니다.\n", loginedMember.getLoginId());
	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	@Override
	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개를 생성했습니다!");
		for (int i = 1; i <= 3; i++) {
			members.add(new Member(++lastId, Util.getDateStr(), ("user" + i), ("user" + i), ("유저" + i)));
		}
	}
}