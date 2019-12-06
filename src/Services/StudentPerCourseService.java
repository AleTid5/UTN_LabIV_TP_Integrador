package Services;

import Models.*;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class StudentPerCourseService extends Service {
    private static final String table = "studentsPerCourses";

    public static final void add(Course course) {
        try {
            StringBuilder studentPerCourses = new StringBuilder();
            for (Student student : course.getStudents())
                studentPerCourses.append("(" + course.getId() + ", " + student.getDocket() +"), ");

            String students = studentPerCourses.substring(0, studentPerCourses.length() - 2);

            String query = "INSERT INTO %s.%s (courseId, studentDocket) VALUES " + students + ";";

            query = String.format(query, database, table);

            Service.execInsert(query);
        } catch (SQLIntegrityConstraintViolationException e) {
            course.setErrorKey(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final ArrayList<User> list(Integer userTypeId, Integer courseId) {
        ArrayList<User> users = new ArrayList<>();

        try {
            String query = "SELECT U.*, P.name AS provinceName, L.name AS locationName " +
                    "FROM %s.%s AS SC " +
                    "INNER JOIN %s.users U on SC.studentDocket = U.docket " +
                    "INNER JOIN %s.provinces AS P on P.id = U.provinceId " +
                    "INNER JOIN %s.locations AS L on L.id = U.locationId " +
                    "WHERE U.status = 'A' AND SC.courseId = %d";
            query = String.format(query, database, table, database, database, database, courseId);

            ResultSet rs = execSelect(query);
            while (rs.next()) {
                User user = new User();
                UserService.getUser(rs.getInt("docket"), userTypeId, user, rs);
                user.setLocation(new Location(rs.getInt("locationId"), rs.getString("locationName")));
                user.setProvince(new Province(rs.getInt("provinceId"), rs.getString("provinceName")));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
