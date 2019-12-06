package Models;

import java.util.ArrayList;

public class Course extends Model {
	private Integer id;
	private Subject subject;
	private Integer year;
	private Integer semester;
	private Teacher teacher;
	private ArrayList<Student> students = new ArrayList<>();
	private String status;

	public Course() {}

	public Course(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSemester() {
		return semester;
	}
	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public ArrayList<Student> getStudents() {
		return this.students;
	}

	public void addStudent(Integer docket) {
		this.students.add(new Student(docket));
	}
}
