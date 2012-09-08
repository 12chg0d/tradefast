package bean;
/**
 *This bean is created for using in authentication page.
 */
public class LoginBean {
  private String userName;
  private String password;
  //Getter and Setter for each field.
  public String getUserName() {
	  return userName;
  }
  public void setUserName(String userName) {
	  this.userName = userName;
  }
  public String getPassword() {
	  return password;
  }
  public void setPassword(String password) {
	  this.password = password;
  }
}