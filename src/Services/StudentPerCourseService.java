package Services;

import Models.Course;
import Models.Student;

import java.sql.SQLIntegrityConstraintViolationException;

public class StudentPerCourseService extends Service {
    private static final String table = "studentsPerCourses";

    public static final void add(Course course) {
        try {
            StringBuilder studentPerCourses = new StringBuilder();
            for (Student student : course.getStudents())
                studentPerCourses.append("(" + course.getId() + ", " + student.getDocket() +")");

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
}
