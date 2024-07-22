package com.exam.BAM.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exam.BAM.dto.Article;
import com.exam.BAM.dto.Member;
import com.exam.BAM.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;
	private int lastArticleId;
	private int lastMemberId;

	public App() {
		this.articles = new ArrayList<>();
		this.members = new ArrayList<>();
		this.lastArticleId = 0;
		this.lastMemberId = 0;
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

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
			
			if (cmd.equals("member join")) {
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

			else if (cmd.equals("article write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine().trim();
				System.out.print("내용 : ");
				String body = sc.nextLine().trim();

				lastArticleId++;
				articles.add(new Article(lastArticleId, Util.getDateStr(), title, body, 0));

				System.out.println(lastArticleId + "번 글이 생성되었습니다.");
			}

			else if (cmd.startsWith("article list")) {
				if (articles.isEmpty()) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				
				List<Article> printArticles = articles;

				String searchKeyword = cmd.substring("article list".length()).trim();

				if (searchKeyword.length() > 0) {
					printArticles = new ArrayList<>();
					
					for (Article article : articles) {
						if (article.getTitle().contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
					
					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다.");
						continue;
					}
				}
				
				System.out.println("번호	|	제목	|		작성일		|	조회수");

				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	|	%s	|	%s	|	%d\n", article.getId(), article.getTitle(), article.getRegDate(), article.getVeiwCnt());
				}
			}

			else if (cmd.startsWith("article detail ")) {
				int id = getIdByCmd(cmd);
				
				if (id == 0) {
					System.out.println("명령어가 올바르지 않습니다.");
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				foundArticle.increaseViewCnt();

				System.out.println("번호 : " + foundArticle.getId());
				System.out.println("작성일 : " + foundArticle.getRegDate());
				System.out.println("제목 : " + foundArticle.getTitle());
				System.out.println("내용 : " + foundArticle.getBody());
				System.out.println("조회수 : " + foundArticle.getVeiwCnt());
			}

			else if (cmd.startsWith("article modify ")) {
				int id = getIdByCmd(cmd);
				
				if (id == 0) {
					System.out.println("명령어가 올바르지 않습니다.");
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				System.out.print("수정할 제목 : ");
				String title = sc.nextLine().trim();
				System.out.print("수정할 내용 : ");
				String body = sc.nextLine().trim();

				foundArticle.setTitle(title);
				foundArticle.setBody(body);
				System.out.println(id + "번 게시물이 수정되었습니다.");
			}

			else if (cmd.startsWith("article delete ")) {
				int id = getIdByCmd(cmd);
				
				if (id == 0) {
					System.out.println("명령어가 올바르지 않습니다.");
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundArticle);
				System.out.println(foundArticle.getId() + "번 게시물이 삭제되었습니다.");
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
	
	private int getIdByCmd(String cmd) {
		String[] cmdBits = cmd.split(" ");

		try {
			int id = Integer.parseInt(cmdBits[2]);
			return id;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (id == article.getId()) {
				return article;
			}
		}
		return null;
	}
	
	private void makeTestData() {
		System.out.println("테스트 게시물 데이터와 회원 데이터를 생성했습니다!");
		for (int i = 1; i < 4; i++) {
			articles.add(new Article(++lastArticleId, Util.getDateStr(), (i + "번"), (i + "번 게시물 내용"), (i * 10)));
			members.add(new Member(++lastMemberId, Util.getDateStr(), ("user" + i), ("user" + i), ("유저" + i)));
		}
	}
}