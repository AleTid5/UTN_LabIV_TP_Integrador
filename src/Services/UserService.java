package Services;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Models.Location;
import Models.Province;
import Models.User;
import Models.UserType;

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

            query = String.format(query, user.getUserType().getId(), toDBMD5(user.getPassword()), toDBString(user.getName().trim()),
                    toDBString(user.getLastname().trim()), toDBString(user.getBorndate(false).trim()), toDBString(user.getAddress().trim()),
                    user.getLocation().getId(), user.getProvince().getId(), toDBString(user.getEmail().trim()),
                    toDBString(user.getPhoneNumber()));

            user.setDocket(Service.execInsert(query, true));
        } catch (SQLIntegrityConstraintViolationException e) {
            user.setErrorKey(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void edit(User user) {
        try {
            String query = "UPDATE %s.%s SET " +
                    "name = %s, lastname = %s, born_date = %s, address = %s, locationId = %d, provinceId = %d, email = %s, " +
                    "phone_number = %s WHERE docket = %d AND userTypeId = %d";

            query = String.format(query, database, table, toDBString(user.getName().trim()), toDBString(user.getLastname().trim()),
                    toDBString(user.getBorndate(false).trim()), toDBString(user.getAddress().trim()),
                    user.getLocation().getId(), user.getProvince().getId(), toDBString(user.getEmail().trim()),
                    toDBString(user.getPhoneNumber().trim()), user.getDocket(), user.getUserType().getId());

            Service.execUpdate(query);
        } catch (SQLIntegrityConstraintViolationException e) {
            user.setErrorKey(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void remove(Integer docket) {
    }

    public static final ArrayList<User> list(Integer userTypeId) {
        ArrayList<User> users = new ArrayList<User>();

        try {
            String query = "SELECT U.*, P.name AS province, L.name AS location FROM %s.%s AS U " +
                    "INNER JOIN %s.provinces AS P on P.id = U.provinceId " +
                    "INNER JOIN %s.locations AS L on L.id = U.locationId " +
                    "WHERE U.status = 'A' AND userTypeId = %d";
            query = String.format(query, database, table, database, database, userTypeId);
            ResultSet rs = execSelect(query);
            while (rs.next()) {
                User user = new User();
                user.setAddress(rs.getString("address"));
                user.setBorndate(rs.getString("born_date"));
                user.setDocket(rs.getInt("docket"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setLocation(new Location(rs.getInt("locationId"), rs.getString("location")));
                user.setProvince(new Province(rs.getInt("provinceId"), rs.getString("province")));
                user.setPhoneNumber(rs.getString("phone_number"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static final User getUserByDocket(Integer docket, Integer userTypeId) {
        User user = new User();

        try {
            String query = "SELECT * FROM %s.%s WHERE status = 'A' AND userTypeId = %d AND docket = %d";
            query = String.format(query, database, table, userTypeId, docket);
            ResultSet rs = execSelect(query);
            rs.next();
            user.setAddress(rs.getString("address"));
            user.setBorndate(rs.getString("born_date"));
            user.setDocket(docket);
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setLocation(new Location(rs.getInt("locationId")));
            user.setProvince(new Province(rs.getInt("provinceId")));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setUserType(new UserType(userTypeId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
