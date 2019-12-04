package Controllers;

import Services.CourseService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/courses/*")
public class CourseController extends Controller {
	private static final String path = "Courses";

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = getCleanPath(req, path.toLowerCase());

		if (action.equals("index")) req.setAttribute("courses",
				CourseService.list((Integer) req.getSession().getAttribute("userDocket"),
						(Integer) req.getSession().getAttribute("userTypeId")));

		req.setAttribute("messages", messages);
		req.getRequestDispatcher(getDispatch(path, action)).forward(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handledCall(req, resp, () -> {
			this.mustBeLogged((Integer) req.getSession().getAttribute("userTypeId"));
			this.setContext(req, "Cursos");
			this.processRequest(req, resp);
			return null;
		});
	}
}
