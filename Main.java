package ����_30202;

import java.io.*;
import java.util.*;

public class Main {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		
		Engine e = new Engine();

		System.out.println("<���� ���� ���α׷� �Դϴ�>");
		System.out.println();
		

		int choice = -1;

		while (choice != 0) {
			
			e.Print_Menu();
			choice = -1;
			
			System.out.print("�޴��� �����ϼ��� : ");
			choice = scan.nextInt();
			scan.nextLine();
			System.out.println();
			
			System.out.print("������ �޴� : ");
			
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
				System.out.println("���α׷��� �����մϴ�");
				System.out.println();
				System.exit(0);
				break;
			default:
				System.out.println("�ٽ� �Է��ϼ���");
			}
		}
	}
}
