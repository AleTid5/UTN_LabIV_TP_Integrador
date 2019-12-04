package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Exceptions.CredentialsException;
import Models.Location;
import Models.Province;
import Models.User;
import Models.UserType;

public class UserService extends Service {
    private static final String table = "users";

    public static final void login(String email, String password) throws CredentialsException, SQLException {
        if (email == "" || password == "") throw new CredentialsException();

        String query = "SELECT COUNT(*) AS isValid FROM %s.%s WHERE email = %s AND password = %s AND status = 'A'";
        query = String.format(query, database, table, toDBString(email), toDBMD5(password));
        ResultSet rs = execSelect(query);
        rs.next();
        if (! rs.getBoolean("isValid")) throw new CredentialsException();
    }

    public static final void authenticate(HttpServletRequest req, String email) {
        try {
            String query = "SELECT docket, userTypeId, name, lastname FROM %s.%s WHERE email = %s";
            query = String.format(query, database, table, toDBString(email));
            ResultSet rs = execSelect(query);
            rs.next();
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("userDocket", rs.getInt("docket"));
            req.getSession().setAttribute("userTypeId", rs.getInt("userTypeId"));
            req.getSession().setAttribute("name", rs.getString("name"));
            req.getSession().setAttribute("lastname", rs.getString("lastname"));
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

    public static final void remove(Integer docket, Integer userTypeId) {
        try {
            String query = "UPDATE %s.%s SET status = 'B' WHERE docket = %d AND userTypeId = %d";
            query = String.format(query, database, table, docket, userTypeId);

            Service.execUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final ArrayList<User> list(Integer userTypeId) {
        ArrayList<User> users = new ArrayList<User>();

        try {
            String query = "SELECT U.*, P.name AS provinceName, L.name AS locationName FROM %s.%s AS U " +
                    "INNER JOIN %s.provinces AS P on P.id = U.provinceId " +
                    "INNER JOIN %s.locations AS L on L.id = U.locationId " +
                    "WHERE U.status = 'A' AND userTypeId = %d";
            query = String.format(query, database, table, database, database, userTypeId);
            ResultSet rs = execSelect(query);
            while (rs.next()) {
                User user = new User();
                getUser(rs.getInt("docket"), user, rs);
                user.setLocation(new Location(rs.getInt("locationId"), rs.getString("locationName")));
                user.setProvince(new Province(rs.getInt("provinceId"), rs.getString("provinceName")));
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
            getUser(docket, user, rs);
            user.setLocation(new Location(rs.getInt("locationId")));
            user.setProvince(new Province(rs.getInt("provinceId")));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setUserType(new UserType(userTypeId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    private static void getUser(Integer docket, User user, ResultSet rs) throws SQLException {
        user.setAddress(rs.getString("address"));
        user.setBorndate(rs.getString("born_date"));
        user.setDocket(docket);
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setLastname(rs.getString("lastname"));
    }
}
