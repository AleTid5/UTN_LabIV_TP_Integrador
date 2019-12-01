package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User extends Model {
	private int docket;
	private UserType userType;
	private String password;
	private String name;
	private String lastname;
	private String borndate;
	private String address;
	private Location location;
	private Province province;
	private String email;
	private String phoneNumber;
	private String status;
	private Date registerDate;

	public int getDocket() {
		return docket;
	}
	public void setDocket(Integer docket) {
		this.docket = docket;
	}

	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBorndate(Boolean toView) {
		String borndate = this.borndate;

		try {
			SimpleDateFormat viewFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat databaseFormat = new SimpleDateFormat("yyyy-MM-dd");
			borndate = toView ? viewFormat.format(databaseFormat.parse(borndate)) : databaseFormat.format(viewFormat.parse(borndate));
		} catch (ParseException e) {
			borndate = this.borndate;
		}

		return borndate;
	}
	public void setBorndate(String borndate) {
		try {
			SimpleDateFormat viewFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat databaseFormat = new SimpleDateFormat("yyyy-MM-dd");
			this.borndate = databaseFormat.format(viewFormat.parse(borndate));
		} catch (ParseException e) {
			this.borndate = borndate;
		}
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegisterDate() {
		return registerDate;
	}
}
