
public class Administrator extends User {
	private String email;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String initialEmail) {
		this.email=initialEmail;
	}
	public String toString() {
		return getAccount()+" "+getName()+" "+getEmail();
	}
}
