package co.lucjay.employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpServiceImpl extends EmpService {
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<EmpDTO> selectEmpAll() {
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		try {
			String sql = "select * from emp order by 1";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmployeeId(rs.getInt("employee_id"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setLastName(rs.getString("last_name"));
				dto.setEmail(rs.getString("email"));
				dto.setHireDate(rs.getString("hire_date"));
				dto.setSalary(rs.getInt("salary"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setJobId(rs.getString("job_id"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteEmp(int eId) {
		String sql = "delete from emp where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, eId);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제됨");
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

	@Override
	public int insertEmp(EmpDTO emp) {
		String sql = "insert into emp (employee_id, last_name, email, hire_date, job_id) values(emp_seq.nextval, ?,?,sysdate,?)";
		String sql2 = "select emp_seq.nextval as cnt from dual";

		int newEmpId = 0;
		try {
			// 새로 생성되는 emp값을 확인 후
			psmt = conn.prepareStatement(sql2);
			rs = psmt.executeQuery();
			if (rs.next()) {
				newEmpId = rs.getInt("cnt");
			}
			// empId 로 사용
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, newEmpId);
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getJobId());
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
		return newEmpId;
	}
}