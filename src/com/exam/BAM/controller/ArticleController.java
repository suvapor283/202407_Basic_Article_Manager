package com.exam.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exam.BAM.dto.Article;
import com.exam.BAM.util.Util;

public class ArticleController {
	private Scanner sc;
	private List<Article> articles;
	private int lastArticleId;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
	}

	public void doWrite() {
		System.out.print("제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("내용 : ");
		String body = sc.nextLine().trim();

		articles.add(new Article(++lastArticleId, Util.getDateStr(), title, body, 0));

		System.out.println(lastArticleId + "번 글이 생성되었습니다.");
	}

	public void showList(String cmd) {
		if (articles.isEmpty()) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
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
				return;
			}
		}
		
		System.out.println("번호	|	제목	|		작성일		|	조회수");

		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			System.out.printf("%d	|	%s	|	%s	|	%d\n", article.getId(), article.getTitle(), article.getRegDate(), article.getVeiwCnt());
		}
	}

	public void showDetail(String cmd) {
		int id = getIdByCmd(cmd);
		
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		foundArticle.increaseViewCnt();

		System.out.println("번호 : " + foundArticle.getId());
		System.out.println("작성일 : " + foundArticle.getRegDate());
		System.out.println("제목 : " + foundArticle.getTitle());
		System.out.println("내용 : " + foundArticle.getBody());
		System.out.println("조회수 : " + foundArticle.getVeiwCnt());
	}

	public void doModify(String cmd) {
		int id = getIdByCmd(cmd);
		
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.print("수정할 제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("수정할 내용 : ");
		String body = sc.nextLine().trim();

		foundArticle.setTitle(title);
		foundArticle.setBody(body);
		System.out.println(id + "번 게시물이 수정되었습니다.");	
	}

	public void doDelete(String cmd) {
		int id = getIdByCmd(cmd);
		
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		articles.remove(foundArticle);
		System.out.println(foundArticle.getId() + "번 게시물이 삭제되었습니다.");
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
	
	public void makeTestData() {
		System.out.println("테스트용 게시물 데이터 3개를 생성했습니다!");
		for (int i = 1; i <= 3; i++) {
			articles.add(new Article(++lastArticleId, Util.getDateStr(), (i + "번"), (i + "번 게시물 내용"), (i * 10)));
		}
	}
}