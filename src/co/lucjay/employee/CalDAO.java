package co.lucjay.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertCal(CalEvent evnt) {
		getConnect();
		String sql = "insert into schedule values(?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, evnt.getGroupId());
			psmt.setString(2, evnt.getTitle());
			psmt.setString(3, evnt.getStart_date());
			psmt.setString(4, evnt.getEnd_date());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<CalEvent> getTotalEvent() {
		getConnect();
		String sql = "select * from schedule";
		List<CalEvent> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				CalEvent e = new CalEvent();
				e.setGroupId(rs.getInt("group_id"));
				e.setStart_date(rs.getString("start_date"));
				e.setEnd_date(rs.getString("end_date"));
				e.setTitle(rs.getString("title"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}
}
