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

    public static final void edit(StudentCourse studentCourse) {
        try {
            String query = "UPDATE %s.%s SET " +
                    "gradeP1 = %d, gradeR1 = %d, gradeP2 = %d, gradeR2 = %d, studentCondition = %s " +
                    "WHERE courseId = %d AND studentDocket = %d";

            query = String.format(query, database, table, studentCourse.getGradeP1(), studentCourse.getGradeR1(),
                    studentCourse.getGradeP2(), studentCourse.getGradeR2(), toDBString(studentCourse.getStudentCondition()),
                    studentCourse.getCourse().getId(), studentCourse.getStudent().getDocket());

            Service.execUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final ArrayList<User> list(Integer userTypeId, Integer courseId, Integer teacherDocket) {
        ArrayList<User> users = new ArrayList<>();

        try {
            String query = "SELECT U.*, P.name AS provinceName, L.name AS locationName " +
                    "FROM %s.%s AS SC " +
                    "INNER JOIN %s.courses C on SC.courseId = C.id " +
                    "INNER JOIN %s.course AS P on P.id = U.provinceId " +
                    "INNER JOIN %s.provinces AS P on P.id = U.provinceId " +
                    "INNER JOIN %s.locations AS L on L.id = U.locationId " +
                    "WHERE U.status = 'A' AND SC.courseId = %d AND C.teacherDocket = %d";
            query = String.format(query, database, table, database, database, database, database, courseId, teacherDocket);

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

    public static final ArrayList<StudentCourse> list(Integer courseId, Integer teacherDocket) {
        ArrayList<StudentCourse> students = new ArrayList<>();

        try {
            String query = "SELECT U.*, P.name AS provinceName, L.name AS locationName, SC.* " +
                    "FROM %s.%s AS SC " +
                    "INNER JOIN %s.courses C on SC.courseId = C.id " +
                    "INNER JOIN %s.users U on SC.studentDocket = U.docket " +
                    "INNER JOIN %s.provinces AS P on P.id = U.provinceId " +
                    "INNER JOIN %s.locations AS L on L.id = U.locationId " +
                    "WHERE U.status = 'A' AND SC.courseId = %d AND C.teacherDocket = %d";
            query = String.format(query, database, table, database, database, database, courseId);

            ResultSet rs = execSelect(query);
            while (rs.next()) {
                StudentCourse studentCourse = new StudentCourse();
                UserService.getUser(rs.getInt("docket"), 3, studentCourse.getStudent(), rs);
                studentCourse.getStudent().setLocation(new Location(rs.getInt("locationId"), rs.getString("locationName")));
                studentCourse.getStudent().setProvince(new Province(rs.getInt("provinceId"), rs.getString("provinceName")));
                studentCourse.setGradeP1(rs.getInt("gradeP1"));
                studentCourse.setGradeP2(rs.getInt("gradeP2"));
                studentCourse.setGradeR1(rs.getInt("gradeR1"));
                studentCourse.setGradeR2(rs.getInt("gradeR2"));
                studentCourse.setStudentCondition(rs.getString("studentCondition"));
                students.add(studentCourse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}
