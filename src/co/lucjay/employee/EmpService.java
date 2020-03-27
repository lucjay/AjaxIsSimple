package co.lucjay.employee;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public abstract class EmpService {
	public Connection conn;

	private String driver;
	private String url;
	private String user;
	private String password;

	public EmpService() {
//		getConnection();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void getConnection() {
		Properties properties = new Properties();
		try {
			Reader reader = new FileReader("config/db.properties");
			properties.load(reader);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract List<EmpDTO> selectEmpAll();

	public abstract void deleteEmp(int eId);

	public abstract int insertEmp(EmpDTO emp);
}