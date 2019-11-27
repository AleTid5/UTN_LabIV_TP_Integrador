package Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class SessionService {
    private static HttpSession session = null;

    public static final void create(HttpServletRequest req) {
        if (session == null) session = req.getSession(true);
    }

    public static final void addParameter(String key, Object value) {
        session.setAttribute(key, value);
    }

    public static final Object getParameter(String key) {
        return session.getAttribute(key);
    }

    public static final void clean() {
        try {
            if (session == null) return;
            session.invalidate();
            session = null;
        } catch (IllegalStateException e) {
            session = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
