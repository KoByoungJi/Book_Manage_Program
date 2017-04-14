package 고병지_30202;

import java.io.*;
import java.util.*;

public class Engine {
	String FilePath = "C:\\test\\30202_고병지.txt";
	Scanner scan = new Scanner(System.in);
	Book b = new Book();

	void Print_Menu() {
		System.out.println("1. 전체 도서 목록");
		System.out.println("2. 도서 검색");
		System.out.println("3. 신규 도서 추가");
		System.out.println("4. 노후 도서 폐기");
		System.out.println("0. 프로그램 종료");
	}

	void Print_Book() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String str = "";
		int count = 1;

		try {
			while ((str = br.readLine()) != null) {
				System.out.println("(" + count + ")\t" + str);
				count++;
			}
		} catch (IOException e) {
			System.out.println("파일을 읽어올 수 없습니다");
			System.out.println("파일을 찾을 수 없습니다");
			e.printStackTrace();
		}
		count--;
		System.out.println("전체 도서 권수 : " + count + "권");
	}

	void Search_Book() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String keyword = "";
		String str = "";
		String tmp = "";
		int count = 0;

		System.out.println("검색 방법");
		System.out.println("1. 통합 검색");
		System.out.println("2. 상세 검색");
		System.out.print("검색할 방법을 선택하세요 : ");
		int choice = scan.nextInt();
		scan.nextLine();

		System.out.print("검색 방법 : ");

		switch (choice) {
		case 1:
			System.out.println("통합 검색");
			System.out.println();

			System.out.print("검색할 키워드를 입력하세요 : ");
			keyword = scan.nextLine();

			try {
				while ((str = br.readLine()) != null) {
					if (str.contains(keyword)) {
						System.out.println(str);
						count++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("검색 도서 권수 : " + count + "권");
			break;
		case 2:
			System.out.println("상세 검색");
			System.out.println();

			System.out.println("검색 조건");
			System.out.println("1. 책이름");
			System.out.println("2. 저자");
			System.out.println("3. 출판사");
			System.out.println("0. 취소");
			System.out.print("검색할 조건을 입력하세요 : ");
			choice = scan.nextInt();
			scan.nextLine();

			System.out.print("검색할 키워드를 입력하세요 : ");
			keyword = scan.nextLine();

			try {
				while ((str = br.readLine()) != null) {
					StringTokenizer tokens = new StringTokenizer(str, "\t");
					for (int i = 0; i < choice; i++) {
						tmp = tokens.nextToken();
					}
					if (tmp.contains(keyword)) {
						System.out.println(str);
						count++;
					}
				}
				if (count == 0)
					System.out.println("검색 결과 없음");
				else
					System.out.println("검색 도서 권수 : " + count + "권");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void Input_Data() throws IOException {
		try {
			System.out.print("책 이름 : ");
			b.setName(scan.nextLine());
			System.out.print("저 자 : ");
			b.setAuthor(scan.nextLine());
			System.out.print("출판사 : ");
			b.setPublisher(scan.nextLine());
			System.out.print("가 격 : ");
			b.setCost(scan.nextInt());
			System.out.println();
		} catch (Exception e) {
			System.out.println("가격엔 숫자만 입력해 주세요");
			System.out.println();
			scan.nextLine();
			Input_Data();
		}
		
	}

	void Insert_Book() throws IOException {
		System.out.println("추가할 도서의 정보를 입력하세요");
		
		Input_Data();

		System.out.println("입력한 정보를 확인하세요");
		System.out.println("책 이름 : " + b.getName());
		System.out.println("저 자 : " + b.getAuthor());
		System.out.println("출판사 : " + b.getPublisher());
		System.out.println("가 격 : " + b.getCost());
		System.out.println();

		System.out.print("입력한 정보가 맞습니까?[y/n] : ");
		String check = scan.next();
		scan.nextLine();

		switch (check) {
		case "y":
			BufferedWriter bw = new BufferedWriter(new FileWriter(FilePath, true));
			bw.write(b.getName() + "\t" + b.getAuthor() + "\t" + b.getPublisher() + "\t" + b.getCost());
			bw.newLine();
			bw.close();
			break;
		case "n":
			System.out.println();
			System.out.println("다시 입력해 주세요");
			Insert_Book();
			break;
		}
	}

	void Delete_Book() throws IOException {
		String tmpFilePath = FilePath + ".tmp";

		Print_Book();

		System.out.print("폐기할 도서의 라인 번호를 입력하세요 : ");
		int DeleteLineBook = scan.nextInt();

		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));
		String str = "";
		int count = 1;

		while ((str = br.readLine()) != null) {
			if (count != DeleteLineBook) {
				bw.write(str);
				bw.write("\r\n");
			}
			count++;
		}
		bw.close();
		br.close();

		FileInputStream fis = new FileInputStream(tmpFilePath);
		FileOutputStream fos = new FileOutputStream(FilePath);
		int data = 0;

		while ((data = fis.read()) != -1) {
			fos.write(data);
		}
		fis.close();
		fos.close();

		File f = new File(tmpFilePath);
		f.delete();
	}
}
