package co.lucjay.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessor;

@WebServlet("/EmpServletJson")
public class EmpServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpServletJson() {
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
		response.setCharacterEncoding("utf-8"); // 한글깨질때
		PrintWriter out = response.getWriter();

		List<EmpDTO> list = new ArrayList<>();
		EmpService service = new EmpServiceImpl();
		list = service.selectEmpAll();
		JSONArray ary = new JSONArray();
		for (EmpDTO emp : list) {
			JSONObject obj = new JSONObject();
			obj.put("firstName", emp.getFirstName());
			obj.put("lastName", emp.getLastName());
			obj.put("email", emp.getEmail());
			obj.put("salary", emp.getSalary());
			obj.put("employeeId", emp.getEmployeeId());
			ary.add(obj);
		}

		out.print(ary.toString());

//		String emps = "{\"employees\":[";
//		int cnt = list.size();
//		int iCnt = 0;
//		for (EmpDTO emp : list) {
//			emps += "{\"empId\":\"" + emp.getEmployeeId() + "\",\"firstName\":\"" + emp.getFirstName()
//					+ "\",\"lastName\":\"" + emp.getLastName() + "\",\"email\":\"" + emp.getEmail() + "\",\"salary\":\""
//					+ emp.getSalary() + "\",\"hire\":\"" + emp.getHireDate() + "\",\"jobId\":\"" + emp.getJobId()
//					+ "\"}";
//			if (++iCnt != cnt) {
//				emps += ",";
//			}
//
//		}
//		emps += "]}";

//		out.print(JSONArray.fromObject(list.toString()));
//		response.getWriter().append(emps);
	}
}
