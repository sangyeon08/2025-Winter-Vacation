package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

public class JDBConnection {
	public Connection con;
	public Statement stmt = null;
	public PreparedStatement psmt;
	public ResultSet rs;
	public JDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/shopdb", "root",
					"1220");
			System.out.println("DB 연결 완료");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from member");
			printData(rs,"id","name","addr");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
	}
	
	private static void printData(ResultSet rs, String col1, String col2, String col3) throws SQLException {
		while(rs.next()) {
			if(!col1.equals(""))
				System.out.print(rs.getString("id"));
		}	if(!col2.equals(""))
			System.out.print("\t|\t"+rs.getString("name"));
		if(!col3.equals(""))
			System.out.println("\t|\t"+rs.getString("addr"));
	}
		
	public static void main(String[] args) {
		JDBConnection jdbc = new JDBConnection();

	}

}
