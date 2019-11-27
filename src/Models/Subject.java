package Models;

public class Subject extends Model {
    private int id;
    private Career career;
    private String name;
    private String status;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Career getCareer() {
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
