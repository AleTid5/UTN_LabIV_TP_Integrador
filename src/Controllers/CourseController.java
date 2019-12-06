package Controllers;

import Exceptions.UnauthorizedException;
import Mappers.CourseMapper;
import Models.Course;
import Services.CourseService;
import Services.StudentPerCourseService;
import Services.SubjectService;
import Services.UserService;
import Utils.Errors;
import Utils.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/courses/*")
public class CourseController extends Controller {
	private static final String path = "Courses";

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, UnauthorizedException {
		String action = getCleanPath(req, path.toLowerCase());
		String courseId = req.getParameter("course-id");
		String error = Errors.getError(req.getParameter("errorId"));
		if (error != null) messages.add(new Message(true, error));

		if (action.equals("add")) {
			req.setAttribute("teachers", UserService.list(2));
			req.setAttribute("students", UserService.list(3));
			req.setAttribute("subjects", SubjectService.list());

			if (courseId != null)
				messages.add(new Message(false, "Se ha creado exitosamente al curso"));
		}

		if (action.equals("index")) {
			req.setAttribute("courses", CourseService.list((Integer) req.getSession().getAttribute("userDocket"),
							(Integer) req.getSession().getAttribute("userTypeId")));
		}

		if (action.equals("details")) {
			this.mustBeTeacher(req);
			req.setAttribute("students", StudentPerCourseService.list(3, Integer.parseInt(courseId)));
			req.setAttribute("courseId", courseId);
		}

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handledCall(req, resp, () -> {
			this.mustBeAdministrator(req);
			Course course = new CourseMapper(req).getCourse();
			CourseService.add(course);
			StudentPerCourseService.add(course);
			String uri = "add?" + (course.getErrorKey() != null ? "errorId=" + course.getErrorKey() :
					"course-id=" + course.getId());
			redirect(req, resp, path.toLowerCase() + "/" + uri);

			return null;
		});
	}
}
