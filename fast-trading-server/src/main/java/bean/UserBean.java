package bean;
/**
 *This bean is created for throwing the data about the user which can be used in many classes.
 */
public class UserBean {
	/*
	 `mID` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `Fname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `Lname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `birthdate` date NOT NULL,
	  `address` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `tel_no` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	  `registration_date` date NOT NULL,
	  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
	 */
 	private String Fname;
	private String Lname;
	private String address;
	private String email;
	private String tel_no;
	private String username;

	//Getter and Setter for each field.
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
