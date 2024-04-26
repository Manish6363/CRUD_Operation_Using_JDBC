package student.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import student.dao.StudentCRUD;
import student.dto.Student;

public class StudentMain {

	static Scanner scan = new Scanner(System.in);
	static StudentCRUD crud = new StudentCRUD();

	public static int save() {
		ArrayList<Student> al=new ArrayList<Student>();
		int data = 0;
		System.out.println(
				"\t................................................\n\t=====Welcome to Student REGISTRATION Portal=====\n\t````````````````````````````````````````````````");
		String ch=null;
		do {
			System.out.print("\tEnter ID: ");
			int id = scan.nextInt();
			scan.nextLine();
			System.out.print("\tEnter Name: ");
			String name = scan.nextLine();
			System.out.print("\tEnter Email: ");
			String email = scan.nextLine();
			System.out.print("\tEnter Phone: ");
			long phone = scan.nextLong();
			System.out.print("\tEnter AGE: ");
			int age = scan.nextInt();
			scan.nextLine();
			System.out.print("\tEnter Address: ");
			String address = scan.nextLine();
			System.out.print("\tEnter Total Marks: ");
			double marks = scan.nextDouble();
			System.out.print("\tEnter Password: ");
			String pwd = scan.next();
			Student stu = new Student(id, name, email, phone, age, address, marks, pwd);
			al.add(stu);
			System.out.print("\nDo you want to insert more student data (yes/no)?");
			ch=scan.next().toLowerCase();
		} while (ch.equals("yes"));
		try {
			data = crud.insertStudentData(al);
			System.out.println("\n\tThank You for Registration..!");
		} catch (SQLException e) {
			System.out.println("\n\tSORRY! Data is not Saved, Try again...!\n");
			data = save();
		}
		return data;
	}

	public static Student userLogin() {
		System.out.println(
				"\t.........................................\n\t=====Welcome to Student LOGIN Portal=====\n\t`````````````````````````````````````````");
		System.out.print("\tEnter your ID: ");
		int id = scan.nextInt();
		System.out.print("\tEnter your password: ");
		String pwd = scan.next();
		Student student = null;
		try {
			if (crud.login(id) != student) {
				student = crud.login(id);
				if (student.getPassword().equals(pwd)) {
					return student;
				} else {
					System.out.println("\n\tStudent Password is Incorrect. Try Again...!\n");
					return userLogin();
				}
			} else {
				System.out.println("\n\tStudent Id is Incorrect. Try Again...!\n");
				return userLogin();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	public static void update(Student student) {
		System.out.println(
				"\tEnter your choice to update: \n\t\t1.NAME\t2.EMAIL\t3.PHONE\t4.AGE\t5.ADDRESS\t6.MARKS\t7.PASSWORD");
		int ch = scan.nextInt();
		int n = 0;
		try {
			if (ch == 1) {
				scan.nextLine();
				System.out.print("Enter NEW NAME: ");
				String name = scan.nextLine();
				n = crud.updateStudentData(student, ch, name);
				System.out.println("\n\t" + n + "-row Updated...!\n");

			} else if (ch == 2) {
				scan.nextLine();
				System.out.print("Enter NEW EMAIL: ");
				String email = scan.nextLine();
				n = crud.updateStudentData(student, ch, email);
				System.out.println("\n\t" + n + "-row Updated...!\n");

			} else if (ch == 3) {
				System.out.print("Enter NEW PHONE No.: ");
				long ph = scan.nextLong();
				n = crud.updateStudentData(student, ch, ph);
				System.out.println("\n\t" + n + "-row Updated...!\n");

			} else if (ch == 4) {
				System.out.print("Enter NEW AGE: ");
				int age = scan.nextInt();
				n = crud.updateStudentData(student, ch, age);
				System.out.println("\n\t" + n + "-row Updated...!\n");

			} else if (ch == 5) {
				scan.nextLine();
				System.out.print("Enter NEW ADDRESS: ");
				String ad = scan.nextLine();
				n = crud.updateStudentData(student, ch, ad);
				System.out.println("\n\t" + n + "-row Updated...!\n");

			} else if (ch == 6) {
				System.out.print("Enter NEW MARKS: ");
				double marks = scan.nextDouble();
				n = crud.updateStudentData(student, ch, marks);
				System.out.println("\n\t" + n + "-row Updated...!\n");

			} else if (ch == 7) {
				scan.nextLine();
				System.out.print("Enter NEW PASSWORD: ");
				String pwd = scan.nextLine();
				n = crud.updateStudentData(student, ch, pwd);
				System.out.println("\n\t" + n + "-row Updated...!\n");

			} else {
				System.out.println("\n\tIncorrect Update Option...!");
				update(student);
			}
		} catch (SQLException e) {
			System.out.println("\n\tGiven Record not found to Update...!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("=====Welcome to Student Portal=====\n`````````````````````````````````");
		boolean value = true;
		do {
			System.out.println(
					"Enter Your Choice..!\n\t1.Register for new Student\n\t2.Login for existing user\n\t3.Exist");
			int key = scan.nextInt();
			switch (key) {
			case 1: {
				
				
				int data = save();
				System.out.println("\t" + data + "-row Inserted...!\n");
				System.out.println("\tTHANK YOU for visiting Student-REGISTRATION-Portal..!");
			}
				break;

			case 2: {
				Student student = userLogin();
				if (student != null) {
					System.out.println(
							"\n\t........................................\n\t=====Student has LOGIN Successfully=====\n\t````````````````````````````````````````");
					boolean flag = true;
					do {
						System.out.println(
								"\tEnter Your Choice..!\n\t\t1.Fetch your data\n\t\t2.Fetch all Student Data\n\t\t3.Updata your Data\n\t\t4.Delete your Data\n\t\t5.Log-out from Student-Portal");
						int ch = scan.nextInt();
						switch (ch) {
						case 1: {
							try {
								crud.fetchStudentData(student.getId());
								System.out.println("\nRecord fetched...!\n");
							} catch (SQLException e) {
								System.out.println("\n\tSORRY! Something wrong...!\n");
//								e.printStackTrace();
							}
						}
							break;

						case 2: {
							try {
								crud.fetchAllStudentsData();
								System.out.println("\nRecord fetched...!\n");
							} catch (SQLException e) {
								System.out.println("\n\tSORRY! Something wrong...!\n");
//								e.printStackTrace();
							}
						}
							break;

						case 3: {
							update(student);
						}
							break;

						case 4: {
							try {
								int n = crud.deleteStudentData(student.getId());
								System.out.println("\n\t" + n + "-Record deleted...!\n");
								flag=false;
							} catch (SQLException e) {
								System.out.println("\n\tSORRY! Something wrong...!\n");
//								e.printStackTrace();
							}
						}
							break;

						case 5:{
							System.out.println("\tTHANK YOU for visiting Student-LOGIN-Portal..!");
							System.out.println("Exit from Student Login...!");
							flag = false;
						}							
							break;

						default:
							System.out.println("Incorrect choice...!");
							break;
						}
					} while (flag);
				}
			}
				break;

			case 3: {
				System.out.println("\tTHANK YOU for visiting Student-Portal..!");
				System.out.println("Exit from Student..!");
				value = false;
			}
				break;

			default:
				System.out.println("Incorrect input..!");
				break;
			}
			System.out.println();
		} while (value);
	}
}
