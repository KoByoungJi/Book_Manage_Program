package ����_30202;

import java.io.*;
import java.util.*;

public class Engine {
	String FilePath = "C:\\test\\30202_����.txt";
	Scanner scan = new Scanner(System.in);

	void Print_Menu() {

		System.out.println("1. ��ü ���� ���");
		System.out.println("2. ���� �˻�");
		System.out.println("3. �ű� ���� �߰�");
		System.out.println("4. ���� ���� ���");
		System.out.println("0. ���α׷� ����");
	}

	void Print_Book() throws FileNotFoundException {

		System.out.println("��ü ���� ���");

		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String str = "";
		int count = 1;

		try {
			while ((str = br.readLine()) != null) {
				System.out.println("(" + count + ")\t" + str);
				count++;
			}
		} catch (IOException e) {
			System.out.println("������ �о�� �� �����ϴ�");
			System.out.println("������ ã�� �� �����ϴ�");
			e.printStackTrace();
		}
		count--;
		System.out.println("��ü ���� �Ǽ� : " + count + "��");
	}

	void Search_Book() throws FileNotFoundException {
		
		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String keyword = "";
		String str = "";
		String tmp = "";
		int count = 0;

		System.out.println("���� �˻�");
		System.out.println();

		System.out.println("�˻� ���");
		System.out.println("1. ���� �˻�");
		System.out.println("2. �� �˻�");
		System.out.println("0. ���");
		System.out.print("�˻��� ����� �����ϼ��� : ");
		int choice = scan.nextInt();
		scan.nextLine();
		
		System.out.print("�˻� ��� : ");

		switch (choice) {
		case 1:
			System.out.println("���� �˻�");
			System.out.println();
			
			System.out.print("�˻��� Ű���带 �Է��ϼ��� : ");
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
			System.out.println("�˻� ���� �Ǽ� : " + count + "��");
			break;
		case 2:
			System.out.println("�� �˻�");
			System.out.println();
			
			System.out.println("�˻� ����");
			System.out.println("1. å�̸�");
			System.out.println("2. ����");
			System.out.println("3. ���ǻ�");
			System.out.print("�˻��� ������ �Է��ϼ��� : ");
			choice = scan.nextInt();
			scan.nextLine();

			System.out.print("�˻��� Ű���带 �Է��ϼ��� : ");
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
					System.out.println("�˻� ��� ����");
				else
					System.out.println("�˻� ���� �Ǽ� : " + count + "��");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 0:
			System.out.println("����մϴ�");
			break;
		}
	}

	public void Input_Data() throws IOException {

		Book b = new Book();

		System.out.print("å �̸� : ");
		b.setName(scan.nextLine());
		System.out.print("�� �� : ");
		b.setAuthor(scan.nextLine());
		System.out.print("���ǻ� : ");
		b.setPublisher(scan.nextLine());
		System.out.print("�� �� : ");
		b.setCost(scan.nextLine());
		System.out.println();

		System.out.println("�Է��� ������ Ȯ���ϼ���");
		System.out.println("å �̸� : " + b.getName());
		System.out.println("�� �� : " + b.getAuthor());
		System.out.println("���ǻ� : " + b.getPublisher());
		System.out.println("�� �� : " + b.getCost());
		System.out.println();

		System.out.print("�Է��� ������ �½��ϱ�?[y/n] : ");
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
			System.out.println("�ٽ� �Է��� �ּ���");
			Input_Data();
			break;
		}
	}

	void Insert_Book() throws IOException {

		System.out.println("�ű� ���� �߰�");

		System.out.println("�߰��� ������ ������ �Է��ϼ���");

		Input_Data();
	}

	void Delete_Book() throws IOException {

		System.out.println("���� ���� ���");
		System.out.println();

		String tmpFilePath = FilePath + ".tmp";

		Print_Book();

		System.out.print("����� ������ ���� ��ȣ�� �Է��ϼ��� : ");
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
