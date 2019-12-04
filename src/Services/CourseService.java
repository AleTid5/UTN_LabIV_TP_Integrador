package Services;

import Models.Course;
import Models.Subject;
import Models.Teacher;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseService extends Service {
    private static final String table = "courses";

    public static final void add() {
    }

    public static final ArrayList<Course> list(Integer docket, Integer userTypeId) {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            String isTeacher = userTypeId == 2 ? (" AND C.teacherDocket = " + docket) : "";
            String query = "SELECT C.*, S.name AS subjectName, U.name AS teacherName, U.lastname AS teacherLastName " +
                    "FROM %s.%s C " +
                    "INNER JOIN %s.subjects S ON C.subjectId = S.id " +
                    "INNER JOIN %s.users U ON C.teacherDocket = U.docket " +
                    "WHERE C.status = 'A' AND U.userTypeId = 2" + isTeacher;
            query = String.format(query, database, table, database, database);
            ResultSet rs = execSelect(query);
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setSubject(new Subject(rs.getInt("subjectId"), rs.getString("subjectName")));
                course.setSemester(rs.getInt("semester"));
                course.setYear(rs.getInt("year"));
                course.setTeacher(new Teacher(rs.getInt("teacherDocket"),
                        rs.getString("teacherName"),
                        rs.getString("teacherLastName")));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }
}
