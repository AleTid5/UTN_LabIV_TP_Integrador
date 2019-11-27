package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.UnauthorizedException;
import Models.Location;
import Models.Province;
import Models.Teacher;
import Models.User;
import Services.UserService;
import Utils.Errors;
import Utils.Helper;

@SuppressWarnings("serial")
@WebServlet("/teachers/*")
public class TeacherController extends Controller {
	private static final String path = "Teachers";

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURL().indexOf("/add") != -1 ? "add" :
				req.getRequestURL().indexOf("/edit") != -1 ? "edit" : "index";

		String docket = req.getParameter("user-docket");

		if (action.equals("add")) {
			Helper.addLocationsAndProvinces(req);
			if (docket != null) req.setAttribute("docket", Helper.setFlashMessage("El legajo del profesor creado es " + docket, false));
		}
		
		if (action.equals("edit")) {
			User user = Helper.setUserData(req, 2);
			Helper.addLocationsAndProvinces(req, user.getLocation().getId(), user.getProvince().getId());
			if (docket != null) req.setAttribute("docket", Helper.setFlashMessage("Se ha editado exitosamente al profesor con legajo " + docket, false));
		};
		
		if (action.equals("index")) Helper.setUserList(req, 2);

		req.setAttribute("error", Helper.setFlashMessage(Errors.getError(req.getParameter("error")), true));

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
			teacher.setName(getParameter(req, "name"));
			teacher.setLastname(getParameter(req, "lastname"));
			teacher.setBorndate(req.getParameter("borndate"));
			teacher.setAddress(getParameter(req, "address"));
			teacher.setLocation(new Location(Integer.parseInt(req.getParameter("location"))));
			teacher.setProvince(new Province(Integer.parseInt(req.getParameter("province"))));
			teacher.setEmail(getParameter(req, "email"));
			teacher.setPhoneNumber(getParameter(req, "phoneNumber"));

			Boolean isEditing = this.compare(req.getParameter("method"), "PUT");
			String uri;

			if (isEditing) {
				teacher.setDocket(Integer.parseInt(req.getParameter("docket")));
				UserService.edit(teacher);
				uri = "edit?" + (teacher.getErrorKey() != null ? "errorId=" + teacher.getErrorKey() : "user-docket=" + teacher.getDocket()) +
						"&docket=" + teacher.getDocket();
			} else {
				UserService.add(teacher);
				uri = "add?" + (teacher.getErrorKey() != null ? "errorId=" + teacher.getErrorKey() :
						"user-docket=" + teacher.getDocket());
			}

			redirect(req, resp, "teachers/" + uri);
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
