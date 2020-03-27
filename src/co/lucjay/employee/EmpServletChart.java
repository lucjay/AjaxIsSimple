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

@WebServlet("/EmpServletChart")
public class EmpServletChart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpServletChart() {
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		List<EmpDTO> list = new ArrayList<>();
		EmpService service = new EmpServiceImpl();
		list = service.selectEmpAll();
		JSONArray ary = new JSONArray();

		for (EmpDTO emp : list) {
			JSONArray ary2 = new JSONArray();
			ary2.add(emp.getEmployeeId());
			ary2.add(emp.getFirstName());
			ary2.add(emp.getLastName());
			ary2.add(emp.getEmail());
			ary2.add(emp.getHireDate());
			ary2.add(emp.getSalary());
			ary.add(ary2);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("data", ary);
		out.print(obj.toString());
	}

}