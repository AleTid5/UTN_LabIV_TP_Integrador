package Models;

public class Province extends Model {
	private Integer id;
	private String name;
	private String status;

	public Province(Integer id) {
		this.setId(id);
	}

	public Province(Integer id, String name) {
		this(id);
		this.setName(name);
	}

	public Province(Integer id, String name, String status) {
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

	@Override
	public String toString() {
		return this.getName();
	}
}
