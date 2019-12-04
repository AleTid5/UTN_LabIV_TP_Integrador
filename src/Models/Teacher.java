package Models;

public class Teacher extends User {
    public Teacher() {
        this.setUserType(new UserType(2));
    }

    public Teacher(Integer docket, String name, String lastName) {
        this();
        this.setDocket(docket);
        this.setName(name);
        this.setLastname(lastName);
    }
}