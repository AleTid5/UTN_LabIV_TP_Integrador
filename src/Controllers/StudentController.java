package Controllers;

import Exceptions.UnauthorizedException;
import Mappers.UserMapper;
import Models.Student;
import Services.UserService;
import Utils.Errors;
import Utils.Helper;
import Utils.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/students/*")
public class StudentController extends Controller {
	private static final String path = "Students";

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = getCleanPath(req, path.toLowerCase());

		String docket = req.getParameter("user-docket");
		String error = Errors.getError(req.getParameter("errorId"));
		if (error != null) messages.add(new Message(true, error));

		if (action.equals("add")) {
			Helper.addLocationsAndProvinces(req);

			if (docket != null)
				messages.add(new Message(false, "El legajo del alumno creado es " + docket));
		}

		if (action.equals("edit")) {
			Helper.setUser(req, 3);
			Helper.addLocationsAndProvinces(req);
			if (docket != null)
				messages.add(new Message(false, "Se ha editado exitosamente al alumno con legajo " + docket));
		}

		if (action.equals("index")) req.setAttribute("users", UserService.list(3));

		req.setAttribute("messages", messages);
		req.getRequestDispatcher(getDispatch(path, action)).forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator((Integer) req.getSession().getAttribute("userTypeId"));
			this.setContext(req, "Alumnos");
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

            Student student = (Student) new UserMapper(req, new Student()).getUser();

			UserService.add(student);
            String uri = "add?" + (student.getErrorKey() != null ? "errorId=" + student.getErrorKey() :
						"user-docket=" + student.getDocket());

			redirect(req, resp, "students/" + uri);
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
            Student student = (Student) new UserMapper(req, new Student()).getUser();

            student.setDocket(Integer.parseInt(req.getParameter("docket")));

            UserService.edit(student);
            String uri = "edit?" + (student.getErrorKey() != null ? "errorId=" + student.getErrorKey() : "user-docket=" + student.getDocket()) +
                    "&docket=" + student.getDocket();

			redirect(req, resp, "students/" + uri);
        } catch (NullPointerException | UnauthorizedException e) {
            redirect(req, resp, "login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
