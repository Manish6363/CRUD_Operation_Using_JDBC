package student.dto;

public class Student implements Comparable<Student>{
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	private String address;
	private double marks;
	private int ranks;
	private String password;
	
	public Student(int id, String name, String email, long phone, int age, String address, double marks, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.address = address;
		this.marks = marks;
		this.password = password;
	}
	
	
	
	public Student(int id, String name, String email, long phone, int age, String address, double marks, int ranks,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.address = address;
		this.marks = marks;
		this.ranks = ranks;
		this.password = password;
	}



	public String toString() {
		return "\n" + id + "   " + name + "   " + email + "   " + phone + "   " + age
				+ "   " + address + "   " + marks + "   " + ranks + "   " + password ;
	}
	
	public int compareTo(Student o) {
		
		if(o.marks>marks)
			return 1;
		if(o.marks<marks)
			return -1;
		
		return 0;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getRanks() {
		return ranks;
	}
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}	
}
