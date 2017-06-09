package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Student;

public class TestStudentJDBC {
	public static void main(String[] args) {
		try {
			System.out.println("Connecting to database");
			// 1. Driver
			// REFLECTION API

			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sa44", "root", "password");
			// 3. Statement
			Statement st = connection.createStatement();
			// 4. Execute the query
			ResultSet rs = st.executeQuery("SELECT * FROM sa44.student;");
			// 5. Process
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setNickName(rs.getString("nick_name"));
				s.setFee(rs.getDouble("fee"));
				System.out.println(s.toString());
			}
			// 6. Close
			st.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
