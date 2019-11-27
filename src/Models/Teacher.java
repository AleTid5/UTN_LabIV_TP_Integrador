package Models;

public class Teacher extends User {
    public Teacher() {
        this.setUserType(new UserType(2));
    }
}