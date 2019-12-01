package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.UnauthorizedException;
import Mappers.UserMapper;
import Models.Teacher;
import Services.UserService;
import Utils.Errors;
import Utils.Helper;
import Utils.Message;

@SuppressWarnings("serial")
@WebServlet("/teachers/*")
public class TeacherController extends Controller {
	private static final String path = "Teachers";

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = getCleanPath(req, path.toLowerCase());

		String docket = req.getParameter("user-docket");
		String error = Errors.getError(req.getParameter("errorId"));
		if (error != null) messages.add(new Message(true, error));

		if (action.equals("add")) {
			Helper.addLocationsAndProvinces(req);

			if (docket != null)
				messages.add(new Message(false, "El legajo del profesor creado es " + docket));
		}

		if (action.equals("edit")) {
			Helper.setUser(req, 2);
			Helper.addLocationsAndProvinces(req);
			if (docket != null)
				messages.add(new Message(false, "Se ha editado exitosamente al profesor con legajo " + docket));
		}

		if (action.equals("index")) req.setAttribute("users", UserService.list(2));

		req.setAttribute("messages", messages);
		req.getRequestDispatcher(getDispatch(path, action)).forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator((Integer) req.getSession().getAttribute("userTypeId"));
			this.setContext(req, "Profesores");
			this.processRequest(req, resp);
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (this.compare(req.getParameter("method"), "PUT")) {
				doPut(req, resp);
				return;
			}

			this.mustBeAdministrator((Integer) req.getSession().getAttribute("userTypeId"));

			Teacher teacher = (Teacher) new UserMapper(req, new Teacher()).getUser();

			UserService.add(teacher);
			String uri = "add?" + (teacher.getErrorKey() != null ? "errorId=" + teacher.getErrorKey() :
					"user-docket=" + teacher.getDocket());

			redirect(req, resp, "teachers/" + uri);
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
		try {
			this.mustBeAdministrator((Integer) req.getSession().getAttribute("userTypeId"));
			Teacher teacher = (Teacher) new UserMapper(req, new Teacher()).getUser();

			teacher.setDocket(Integer.parseInt(req.getParameter("docket")));

			UserService.edit(teacher);
			String uri = "edit?" + (teacher.getErrorKey() != null ? "errorId=" + teacher.getErrorKey() : "user-docket=" + teacher.getDocket()) +
					"&docket=" + teacher.getDocket();

			redirect(req, resp, "teachers/" + uri);
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
