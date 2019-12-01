package Controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error/*")
public class ErrorController extends Controller {
    private static final String path = "Errors";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = getCleanPath(req, path.toLowerCase());
            this.setPaths(req, "Error Found");

            req.getRequestDispatcher(getDispatch(path, action)).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
