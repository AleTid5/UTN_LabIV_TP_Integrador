package App.Models;

public class Student extends User {
    public Student() {
        this.setUserType(new UserType(3));
    }
}