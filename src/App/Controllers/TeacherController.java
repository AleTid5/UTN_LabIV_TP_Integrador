package App.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import App.Exceptions.UnauthorizedException;
import App.Models.Location;
import App.Models.Province;
import App.Models.Teacher;
import App.Services.UserService;
import App.Utils.Helper;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/teachers", "/teachers/add", "/teachers/edit", "/teachers/remove" })
public class TeacherController extends Controller {
	private static final String path = "Teachers";

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURL().indexOf("/add") != -1 ? "add" :
				req.getRequestURL().indexOf("/edit") != -1 ? "edit" : "index";

		if (action.equals("add") || action.equals("edit")) Helper.addLocationsAndProvinces(req);

		Helper.processResponse(req, resp, "error");
		Helper.processResponse(req, resp, "user-docket");

		req.getRequestDispatcher(getDispatch(path, action)).forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator();
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
			this.mustBeAdministrator();
			Teacher teacher = new Teacher();
			teacher.setName("Docente");
			teacher.setLastname("Prueba");
			teacher.setBorndate("1995/10/03");
			teacher.setAddress("una calle");
			teacher.setLocation(new Location(5));
			teacher.setProvince(new Province(10));
			teacher.setEmail("ale2@ale.com");
			teacher.setPhoneNumber("1231231");

			UserService.add(teacher);
			
			resp.addHeader("user-docket", Integer.toString(teacher.getDocket()));
			resp.addHeader("error", teacher.getError());
			
			redirect(req, resp, "teachers/add");
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator();

		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator();

		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
