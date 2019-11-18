package App.Services;

import App.Models.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserService extends Service {
    private static final String table = "users";

    public static final Boolean login(String email, String password) {
        Boolean isValid = false;

        try {
        	if (email == "" || password == "") return false;

            String query = "SELECT COUNT(*) AS isValid FROM %s.%s WHERE email = %s AND password = %s AND status = 'A'";
            query = String.format(query, database, table, toDBString(email), toDBMD5(password));
            ResultSet rs = execSelect(query);
            rs.next();
            isValid = rs.getBoolean("isValid");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    public static final void authenticate(HttpServletRequest req, String email) {
        try {
            SessionService.create(req);

            String query = "SELECT userTypeId, name, lastname FROM %s.%s WHERE email = %s";
            query = String.format(query, database, table, toDBString(email));
            ResultSet rs = execSelect(query);
            rs.next();
            SessionService.addParameter("email", email);
            SessionService.addParameter("userTypeId", rs.getInt("userTypeId"));
            SessionService.addParameter("name", rs.getString("name"));
            SessionService.addParameter("lastname", rs.getString("lastname"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void add(User user) {
        try {
            String query = Service.createInsert(table,
                    "userTypeId, password, name, lastname, born_date, address, locationId, provinceId, email, phone_number",
                    "%d, %s, %s, %s, %s, %s, %d, %d, %s, %s");

            query = String.format(query, user.getUserType().getId(), toDBMD5(user.getPassword()), toDBString(user.getName()),
                    toDBString(user.getLastname()), toDBString(user.getBorndate()), toDBString(user.getAddress()),
                    user.getLocation().getId(), user.getProvince().getId(), toDBString(user.getEmail()),
                    toDBString(user.getPhoneNumber()));

            user.setDocket(Service.execInsert(query, true));
        } catch (SQLIntegrityConstraintViolationException e) {
            user.setError("Ya existe un usuario con el E-Mail ingresado (" + user.getEmail() + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void edit(User user) {
    }

    public static final void list() {
    }

    public static final void remove(Integer docket) {
    }
}
