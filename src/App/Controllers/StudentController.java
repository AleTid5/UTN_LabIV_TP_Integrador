package App.Controllers;

import App.Exceptions.UnauthorizedException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/students", "/students/add", "/students/edit", "/students/remove" })
public class StudentController extends Controller {
	private static final String path = "Students";

	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getRequestURL().indexOf("/add") != -1 ? "add" :
				req.getRequestURL().indexOf("/edit") != -1 ? "edit" : "index";

		req.getRequestDispatcher(getDispatch(path, action)).forward(req, res);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator();
			this.setContext(req, "Alumnos");
			this.processRequest(req, resp);
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
