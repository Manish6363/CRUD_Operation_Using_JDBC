package student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import com.mysql.cj.jdbc.Driver;

import student.dto.Student;

public class StudentCRUD {	

	public Connection createTable() throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/studentdb?createDatabaseIfNotExist=true", "root", "root");
		Statement ps = conn.createStatement();
		String query = "CREATE TABLE IF NOT EXISTS STUDENT (ID INT(5) PRIMARY KEY, NAME VARCHAR(40) NOT NULL, EMAIL VARCHAR(50) UNIQUE NOT NULL, PHONE BIGINT(10) UNIQUE NOT NULL, AGE TINYINT(2) NOT NULL, ADDRESS VARCHAR(140) NOT NULL, MARKS DECIMAL(4,2) NOT NULL, RANKS INT, PASSWORD VARCHAR(40) NOT NULL)";
		ps.execute(query);
		ps.close();
		return conn;
	}

	
	
	public Student login(int id) throws SQLException {
		Student student = null;
		Connection conn = createTable();
		PreparedStatement s = conn.prepareStatement("SELECT * FROM STUDENT WHERE ID=? ");
		s.setInt(1, id);
		ResultSet res = s.executeQuery();
		if (res.next()) {
			student = new Student(res.getInt(1), res.getString(2), res.getString(3), res.getLong(4), res.getInt(5),
					res.getString(6), res.getDouble(7), res.getString(9));
		}
		s.close();
		conn.close();
		return student;
	}
	
	

	public int insertStudentData(ArrayList<Student> al) throws SQLException {
		int data = 0;
		Connection conn = createTable();
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO STUDENT (ID, NAME, EMAIL, PHONE, AGE, ADDRESS, MARKS, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		for (Student stu : al) {
			ps.setInt(1, stu.getId());
			ps.setString(2, stu.getName());
			ps.setString(3, stu.getEmail());
			ps.setLong(4, stu.getPhone());
			ps.setInt(5, stu.getAge());
			ps.setString(6, stu.getAddress());
			ps.setDouble(7, stu.getMarks());
			ps.setString(8, stu.getPassword());
			ps.addBatch();
		}
		int[] len = ps.executeBatch();
		data = len.length;
		ps.close();
		conn.close();
		return data;
	}

	
	public void fetchStudentData(int id) throws SQLException {
		setRank();
		Connection conn = createTable();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM STUDENT WHERE ID=?");
		ps.setInt(1, id);
		ResultSet res = ps.executeQuery();
		System.out.println("ID\tNAME\t\tEMAIL\t\tMOBILE NO.   AGE \tADDRESS\t\tMARKS  RANKS\tPASSWORD");
		System.out.println("==\t====\t\t=====\t\t==========   === \t=======\t\t=====  =====\t=========");
		while (res.next()) {
			System.out.println(res.getInt(1) + "   " + res.getString(2) + "   " + res.getString(3) + "   "
					+ res.getLong(4) + "   " + res.getInt(5) + "   " + res.getString(6) + "   " + res.getDouble(7)
					+ "   " + res.getInt(8) + "   " + res.getString(9));
		}
		ps.close();
		conn.close();
	}

	public void setRank() throws SQLException {
		Connection conn = createTable();
		ArrayList<Double> al=new ArrayList<Double>();
		PreparedStatement s = conn.prepareStatement("SELECT DISTINCT(MARKS) FROM STUDENT ORDER BY MARKS DESC");
		ResultSet res = s.executeQuery();
		
		while (res.next()) {
			al.add(res.getDouble(1));
		}
		PreparedStatement ps=conn.prepareStatement("UPDATE STUDENT SET RANKS=? WHERE MARKS=?");
		int rank=0;
		for(double d: al) {
			ps.setInt(1, ++rank);
			ps.setDouble(2, d);
			ps.addBatch();
		}	
		ps.executeBatch();
		s.close();
		conn.close();
	}
	
	public void fetchAllStudentsData() throws SQLException {
		setRank();
		Connection conn = createTable();
		PreparedStatement s = conn.prepareStatement("SELECT * FROM STUDENT ORDER BY RANKS");
		ResultSet res1 = s.executeQuery();
		
		System.out.println("ID\tNAME\t\tEMAIL\t\tMOBILE NO.   AGE \tADDRESS\t\tMARKS  RANKS\tPASSWORD");
		System.out.println("==\t====\t\t=====\t\t==========   === \t=======\t\t=====  =====\t=========");
		while (res1.next()) {
			System.out.println(res1.getInt(1) + "   " + res1.getString(2) + "   " + res1.getString(3) + "   "
					+ res1.getLong(4) + "   " + res1.getInt(5) + "   " + res1.getString(6) + "   " + res1.getDouble(7)
					+ "   " + res1.getInt(8) + "   " + res1.getString(9));
		}
		s.close();
		conn.close();
	}
	
	

	public int updateStudentData(Student stu, int i, Object o) throws SQLException {
		Connection conn = createTable();
		PreparedStatement ps = null;
		int data = 0;
		if (i == 1) {
			String sn = (String) o;
			ps = conn.prepareStatement("UPDATE STUDENT SET NAME=? WHERE ID=?");
			ps.setString(1, sn);
			ps.setInt(2, stu.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 2) {
			String e = (String) o;
			ps = conn.prepareStatement("UPDATE STUDENT SET EMAIL=? WHERE ID=?");
			ps.setString(1, e);
			ps.setInt(2, stu.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 3) {
			long ph = (long) o;
			ps = conn.prepareStatement("UPDATE STUDENT SET PHONE=? WHERE ID=?");
			ps.setLong(1, ph);
			ps.setInt(2, stu.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 4) {
			int a = (int) o;
			ps = conn.prepareStatement("UPDATE STUDENT SET AGE=? WHERE ID=?");
			ps.setInt(1, a);
			ps.setInt(2, stu.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 5) {
			String ad = (String) o;
			ps = conn.prepareStatement("UPDATE STUDENT SET ADDRESS=? WHERE ID=?");
			ps.setString(1, ad);
			ps.setInt(2, stu.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 6) {
			double m = (double) o;
			ps = conn.prepareStatement("UPDATE STUDENT SET MARKS=? WHERE ID=?");
			ps.setDouble(1, m);
			ps.setInt(2, stu.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 7) {
			String pwd = (String) o;
			ps = conn.prepareStatement("UPDATE STUDENT SET PASSWORD=? WHERE ID=?");
			ps.setString(1, pwd);
			ps.setInt(2, stu.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}
		return 0;
	}

	public int deleteStudentData(int id) throws SQLException {
		Connection conn = createTable();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM STUDENT WHERE ID=?");
		ps.setInt(1, id);
		int data = ps.executeUpdate();		
		ps.close();
		conn.close();
		return data;
	}
}
