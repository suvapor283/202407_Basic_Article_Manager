import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
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

				System.out.println(++lastArticleId + "번 글이 생성되었습니다.");
			}
			
			else if (cmd.equals("article list")) {
				System.out.println("게시물이 존재하지 않습니다.");
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}
