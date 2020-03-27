package co.lucjay.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalInsertServlet")
public class CalInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalInsertServlet() {
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
		String groupId = request.getParameter("groupId");
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		CalEvent e = new CalEvent();
		e.setGroupId(Integer.parseInt(groupId));
		e.setTitle(title);
		e.setStart_date(start);
		e.setEnd_date(end);

		CalDAO dao = new CalDAO();
		dao.insertCal(e);
	}
}
