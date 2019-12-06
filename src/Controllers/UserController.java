package Controllers;

import Exceptions.UnauthorizedException;
import Mappers.UserMapper;
import Models.Student;
import Models.User;
import Services.UserService;
import Utils.Errors;
import Utils.Helper;
import Utils.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
abstract public class UserController extends Controller {
    protected String path = null;
    protected String nameType = null;
    protected String navbarTitle = null;
    protected User user = null;

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getCleanPath(req, path.toLowerCase());

        String docket = req.getParameter("user-docket");
        String error = Errors.getError(req.getParameter("errorId"));
        if (error != null) messages.add(new Message(true, error));

        if (action.equals("add")) {
            Helper.addLocationsAndProvinces(req);

            if (docket != null)
                messages.add(new Message(false, "El legajo del " + nameType + " creado es " + docket));
        }

        if (action.equals("edit")) {
            Helper.setUser(req, this.user.getUserType().getId());
            Helper.addLocationsAndProvinces(req);
            if (docket != null)
                messages.add(new Message(false, "Se ha editado exitosamente al " + nameType + " con legajo " + docket));
        }

        if (action.equals("index")) req.setAttribute("users", UserService.list(this.user.getUserType().getId()));

        req.setAttribute("messages", messages);
        req.getRequestDispatcher(getDispatch(path, action)).forward(req, resp);
    }

    public final void get(HttpServletRequest req, HttpServletResponse resp) {
        handledCall(req, resp, () -> {
            this.mustBeAdministrator(req);
            this.setContext(req, navbarTitle);
            this.processRequest(req, resp);
            return null;
        });
    }

    public final void post(HttpServletRequest req, HttpServletResponse resp) {
        handledCall(req, resp, () -> {
            this.mustBeAdministrator(req);
            User user = new UserMapper(req, this.user).getUser();
            UserService.add(user);
            String uri = "add?" + (user.getErrorKey() != null ? "errorId=" + user.getErrorKey() :
                    "user-docket=" + user.getDocket());
            redirect(req, resp, path.toLowerCase() + "/" + uri);

            return null;
        });
    }

    public final void put(HttpServletRequest req, HttpServletResponse resp) {
        handledCall(req, resp, () -> {
            this.mustBeAdministrator(req);
            User user = new UserMapper(req, this.user).getUser();
            user.setDocket(Integer.parseInt(req.getParameter("docket")));
            UserService.edit(user);
            String uri = "edit?" + (user.getErrorKey() != null ? "errorId=" + user.getErrorKey() : "user-docket=" + user.getDocket()) +
                    "&docket=" + user.getDocket();
            redirect(req, resp, path.toLowerCase() + "/" + uri);

            return null;
        });
    }

    public final void delete(HttpServletRequest req, HttpServletResponse resp) {
        handledCall(req, resp, () -> {
            this.mustBeAdministrator(req);
            UserService.remove(Integer.parseInt(req.getParameter("docket")), this.user.getUserType().getId());

            return null;
        });
    }
}