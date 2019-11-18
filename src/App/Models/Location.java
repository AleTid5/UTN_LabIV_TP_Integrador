package App.Models;

public class Location extends Model {
	private Integer id;
	private String name;
	private String status;

	public Location(Integer id) {
		this.setId(id);
	}

	public Location(Integer id, String name) {
		this(id);
		this.setName(name);
	}

	public Location(Integer id, String name, String status) {
		this(id, name);
		this.setStatus(status);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
