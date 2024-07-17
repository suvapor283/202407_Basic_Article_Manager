import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList<>();

		int lastArticleId = 0;

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

			if (cmd.equals("article write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine().trim();
				System.out.print("내용 : ");
				String body = sc.nextLine().trim();

				lastArticleId++;
				articles.add(new Article(lastArticleId, Util.getDateStr(), title, body));

				System.out.println(lastArticleId + "번 글이 생성되었습니다.");
			}
			
			else if (cmd.equals("article list")) {
				if (articles.isEmpty()) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호	|	제목	|	작성일");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.regDate);
				}
			}
			
			else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);
					
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				} catch (Exception e) {
					System.out.println("error : " + e);
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (id == article.id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("작성일 : " + foundArticle.regDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
			}
			
			else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);
					
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				} catch (Exception e) {
					System.out.println("error : " + e);
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (id == article.id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				
				System.out.print("수정할 제목 : ");
				String title = sc.nextLine().trim();
				System.out.print("수정할 내용 : ");
				String body = sc.nextLine().trim();
				
				foundArticle.title = title;
				foundArticle.body = body;
				System.out.println(id + "번 게시물이 수정되었습니다.");
			}
			
			else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);
					
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				} catch (Exception e) {
					System.out.println("error : " + e);
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (id == article.id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				
				articles.remove(foundArticle);
				System.out.println(id + "번 게시물이 삭제되었습니다.");
			}
			
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}