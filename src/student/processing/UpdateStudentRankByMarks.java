package student.processing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateStudentRankByMarks {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
		PreparedStatement ps=conn.prepareStatement("SELECT DISTINCT(MARKS) FROM STUDENT ORDER BY MARKS DESC");
		ResultSet res=ps.executeQuery();
		ArrayList<Double> al=new ArrayList<Double>();
		while(res.next()) {
			al.add(res.getDouble(1));
		}
		System.out.println(al);
		PreparedStatement s=conn.prepareStatement("UPDATE STUDENT SET RANKS=? WHERE MARKS=?");
		int r=0;
		for(double d: al) {
			s.setInt(1, ++r);
			s.setDouble(2, d);
			s.addBatch();
		}
		s.executeBatch();
		PreparedStatement ps1=conn.prepareStatement("SELECT * FROM STUDENT ORDER BY RANKS ASC");
		ResultSet res1=ps1.executeQuery();
		System.out.println("ID\tNAME\t\tEMAIL\t\tMOBILE NO.   AGE \tADDRESS\t\tMARKS  RANKS\tPASSWORD");
		System.out.println("==\t====\t\t=====\t\t==========   === \t=======\t\t=====  =====\t=========");
		while (res1.next()) {
			System.out.println(res1.getInt(1) + "   " + res1.getString(2) + "   " + res1.getString(3) + "   "
					+ res1.getLong(4) + "   " + res1.getInt(5) + "   " + res1.getString(6) + "   " + res1.getDouble(7)
					+ "   " + res1.getInt(8) + "   " + res1.getString(9));
		}
		ps.close();
		s.close();
		ps1.close();
		conn.close();
	}

}
