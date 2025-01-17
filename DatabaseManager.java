package Calendar2;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
	private final String url = "jdbc:mysql://localhost:3305/calendar_db";
	private final String user = "root";
	private final String password = "1220";

	// 데이터베이스 연결 객체 던지기 (전달)
	public Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, user, password);
	}

	// 일정 추가하기
	public void addSchedule(String date, String schedule) {
		String query = "INSERT INTO schedules (date, schedule) VALUES (?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, date);
			pstmt.setString(2, schedule);
			pstmt.executeUpdate();
			System.out.println("일정이 추가되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 특정 월의 일정 가져오기
	public Map<String, String> getSchedulesForMonth(int year, int month) {
		Map<String, String> schedules = new HashMap<>();
		String query = "SELECT date, schedule FROM schedules WHERE YEAR(date) = ? AND MONTH(date) = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				schedules.put(rs.getString("date"), rs.getString("schedule"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedules;
	}

	// 모든 일정 조회
	public Map<String, String> getSchedules() {
		String query = "SELECT date, schedule FROM schedules";
		Map<String, String> scheduleMap = new HashMap<>();
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				String date = rs.getString("date");
				String schedule = rs.getString("schedule");
				scheduleMap.put(date, schedule); // 날짜와 일정을 맵에 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleMap;
	}

	// 일정 삭제
	public void deleteSchedule(String date) {
		String query = "DELETE FROM schedules WHERE date = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, date);
			pstmt.executeUpdate();
			System.out.println("일정이 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 일정 수정
	public void updateSchedule(String date, String newSchedule) {
		String query = "UPDATE schedules SET schedule = ? WHERE date = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, newSchedule);
			pstmt.setString(2, date);
			pstmt.executeUpdate();
			System.out.println("일정이 수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
