package App.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/courses", "/courses/add", "/courses/details", "/courses/uploadNotes" })
public class CourseController extends Controller {
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.getRequestURL().indexOf("/add") != -1) {
			req.getRequestDispatcher("/App/Views/Authorized/Courses/add.jsp").forward(req, res);
			return;
		} else if (req.getRequestURL().indexOf("/details") != -1) {
			req.getRequestDispatcher("/App/Views/Authorized/Courses/details.jsp").forward(req, res);
			return;
		} else if (req.getRequestURL().indexOf("/uploadNotes") != -1) {
			req.getRequestDispatcher("/App/Views/Authorized/Courses/uploadNotes.jsp").forward(req, res);
			return;
		}

		Boolean isAdmin = false;

		if (isAdmin)
			req.getRequestDispatcher("/App/Views/Authorized/Courses/index.jsp").forward(req, res);
		else
			req.getRequestDispatcher("/App/Views/Authorized/Courses/index_teacher.jsp").forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			this.setContext(req, "Cursos");
			this.processRequest(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
