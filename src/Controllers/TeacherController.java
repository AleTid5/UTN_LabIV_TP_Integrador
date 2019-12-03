package Controllers;

import Models.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/teachers/*")
public class TeacherController extends UserController {
	private void setContext() {
		this.path = "Teachers";
		this.nameType = "profesor";
		this.navbarTitle = "Profesores";
		this.user = new Teacher();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		handledCall(req, resp, () -> {
			this.setContext();
			this.get(req, resp);
			return null;
		});
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		if (this.compare(req.getParameter("method"), "PUT")) {
			doPut(req, resp);
			return;
		}

		handledCall(req, resp, () -> {
			this.setContext();
			this.post(req, resp);
			return null;
		});
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
		handledCall(req, resp, () -> {
			this.setContext();
			this.put(req, resp);
			return null;
		});
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
		handledCall(req, resp, () -> {
			this.setContext();
			this.delete(req, resp);
			resp.setContentType("application/json");
			resp.getWriter().print("{\"status\": 200}");
			return null;
		});
	}
}
