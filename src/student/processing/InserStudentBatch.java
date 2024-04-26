package student.processing;

import java.util.ArrayList;
import java.util.Scanner;

import student.dto.Student;

public class InserStudentBatch {
	static Scanner scan=new Scanner(System.in);
	
	public static Student data() {
		System.out.print("Enetr Id: ");
		int id=scan.nextInt();
		scan.nextLine();
		System.out.print("Enetr Name: ");
		String name=scan.nextLine();
		System.out.print("Enetr Email: ");
		String email=scan.nextLine();
		System.out.print("Enetr Phone: ");
		long phone=scan.nextLong();
		System.out.print("Enetr Age: ");
		int age=scan.nextInt();
		scan.nextLine();
		System.out.print("Enetr Address: ");
		String address=scan.nextLine();
		System.out.print("Enetr Marks: ");
		int marks=scan.nextInt();
		scan.nextLine();
		System.out.print("Enetr Password: ");
		String password=scan.nextLine();
		Student student=new Student(id, name, email, phone, age, address, marks, password);
		return student;
	}
	public static void main(String[] args) {
		ArrayList<Student> al=new ArrayList<Student>();
		String ch=null;
		do {		
			al.add(data());
			System.out.println("Do you want to insert data more(yes/no)? ");
			ch=scan.next().toLowerCase();
		} while (ch.equals("yes"));
		
		System.out.println(al);
	}
}
