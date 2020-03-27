package co.lucjay.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertEmpSetvlet")
public class InsertEmpSetvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertEmpSetvlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hireDate");
		String jobId = request.getParameter("jobId");

		EmpDTO emp = new EmpDTO();
		emp.setLastName(lName);
		emp.setEmail(email);
		emp.setHireDate(hireDate);
		emp.setJobId(jobId);

		EmpService service = new EmpServiceImpl();
		int empId = service.insertEmp(emp);

		PrintWriter out = response.getWriter();
		out.print(empId);
	}
}
