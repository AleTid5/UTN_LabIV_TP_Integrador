package Models;

public class Student extends User {
    public Student() {
        this.setUserType(new UserType(3));
    }

    public Student(Integer docket) {
        this();
        this.setDocket(docket);
    }
}