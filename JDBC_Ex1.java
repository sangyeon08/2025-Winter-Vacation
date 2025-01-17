package database;

import java.sql.*;

public class JDBC_Ex1 {

	public static void main(String[] args) {
		Connection conn;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/sampledb", "root", "1220");
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into student(name, id, dept) values('아무개', '0893012','컴퓨터공학')");
			printTable(stmt);
			stmt.executeUpdate("update student set if = '0189011' where name = '아무개'");
			printTable(stmt);
			stmt.executeUpdate("delete from student where name = '아무개'");
			printTable(stmt);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
	}

	private static void printTable(Statement stmt) throws SQLException {
		ResultSet srs = stmt.executeQuery("Select * from student");
		while(srs.next()) {
			System.out.print(srs.getString("name"));
			System.out.print("\t|\t" + srs.getString("id"));
			System.out.println("\t|\t" + srs.getString("dept"));
		}
	}
}
