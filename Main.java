package 고병지_30202;

import java.io.*;
import java.util.*;

public class Main {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		
		Engine e = new Engine();

		System.out.println("<도서 관리 프로그램 입니다>");
		System.out.println();
		

		int choice = -1;

		while (choice != 0) {
			
			e.Print_Menu();
			choice = -1;
			
			System.out.print("메뉴를 선택하세요 : ");
			choice = scan.nextInt();
			scan.nextLine();
			System.out.println();
			
			System.out.print("선택한 메뉴 : ");
			
			switch (choice) {
			case 1:
				e.Print_Book();
				System.out.println();
				break;
			case 2:
				e.Search_Book();
				System.out.println();
				break;
			case 3:
				e.Insert_Book();
				System.out.println();
				break;
			case 4:
				e.Delete_Book();
				System.out.println();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다");
				System.out.println();
				System.exit(0);
				break;
			default:
				System.out.println("다시 입력하세요");
			}
		}
	}
}
