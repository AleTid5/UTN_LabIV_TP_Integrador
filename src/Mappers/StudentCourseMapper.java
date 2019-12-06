package Mappers;

import Models.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class StudentCourseMapper extends Mapper {
    private ArrayList<StudentCourse> studentsCourse = new ArrayList<>();

    public StudentCourseMapper(HttpServletRequest req) {
        for (String studentDocket : req.getParameterValues("students[]")) {
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudent(new Student(Integer.parseInt(studentDocket)));
            studentCourse.setCourse(new Course(Integer.parseInt(getParameter(req, "courseId"))));
            studentCourse.setGradeP1(Integer.parseInt(getParameter(req,"gradeP1-" + studentDocket)));
            studentCourse.setGradeP2(Integer.parseInt(getParameter(req,"gradeP2-" + studentDocket)));
            studentCourse.setGradeR1(Integer.parseInt(getParameter(req,"gradeR1-" + studentDocket)));
            studentCourse.setGradeR2(Integer.parseInt(getParameter(req,"gradeR2-" + studentDocket)));
            studentCourse.setStudentCondition(getParameter(req,"studentCondition-" + studentDocket));
            studentsCourse.add(studentCourse);
        }
    }

    public final ArrayList<StudentCourse> getStudentsCourse() {
        return this.studentsCourse;
    }
}
