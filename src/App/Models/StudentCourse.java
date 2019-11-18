package App.Models;

public class StudentCourse extends Model {
    private Course course;
    private Student student;
    private String status;
    private int gradeP1;
    private int gradeR1;
    private int gradeP2;
    private int gradeR2;
    private String studentCondition;

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

	public int getGradeP1() {
		return gradeP1;
	}
	public void setGradeP1(int gradeP1) {
		this.gradeP1 = gradeP1;
	}

    public int getGradeR1() {
        return gradeR1;
    }
    public void setGradeR1(int gradeR1) {
        this.gradeR1 = gradeR1;
    }

	public int getGradeP2() {
		return gradeP2;
	}
	public void setGradeP2(int gradeP2) {
		this.gradeP2 = gradeP2;
	}

	public int getGradeR2() {
		return gradeR2;
	}
	public void setGradeR2(int gradeR2) {
		this.gradeR2 = gradeR2;
	}

	public String getStudentCondition() {
		return studentCondition;
	}
	public void setStudentCondition(String studentCondition) {
		this.studentCondition = studentCondition;
	}
}
