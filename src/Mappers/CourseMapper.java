package Mappers;

import Models.*;

import javax.servlet.http.HttpServletRequest;

public class CourseMapper extends Mapper {
    private Course course;

    public CourseMapper(HttpServletRequest req) {
        this.course = new Course();
        this.course.setSemester(Integer.parseInt(getParameter(req, "semester")));
        this.course.setSubject(new Subject(Integer.parseInt(getParameter(req,"subject")), null));
        this.course.setTeacher(new Teacher(Integer.parseInt(getParameter(req,"teacher[]"))));
        for (String studentDocket : req.getParameterValues("students[]")) {
            this.course.addStudent(Integer.parseInt(studentDocket));
        }
    }

    public final Course getCourse() {
        return this.course;
    }
}
