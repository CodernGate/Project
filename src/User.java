import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class User {

	private String account;
	private String name;
	private String telephoneNumber;
	private String address;
	private String email;
	private String QQNumber;
	
	private String password;
	
	private JTextField Account;
	private JTextField Name;
	private JTextField TelNum;
	private JTextField Address;
	private JTextField Email;
	private JTextField QQNum;
	
	public String getTelNum() {
		return telephoneNumber;
	}
	
	public void setTelNum(String initialTelphoneNum) {
		this.telephoneNumber=initialTelphoneNum;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String initialAddress) {
		this.address=initialAddress;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String initialEmail) {
		this.email=initialEmail;
	}
	
	public String getQQNum() {
		return QQNumber;
	}
	
	public void setQQNum(String initialQQNum) {
		this.QQNumber=initialQQNum;
	}

	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String initialAccount) {
		this.account=initialAccount;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String initialPassword) {
		this.password=initialPassword;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String initialName) {
		this.name=initialName;
	}
	
	public boolean equals(Object object) {
		return (object instanceof User)&&((User) object).getAccount()==this.getAccount();
	}
	
	public String toStringNoPassword() {
		return getAccount()+" "+getName()+" "+getTelNum()+" "+getAddress()+" "
				+getEmail()+" "+getQQNum();
	}
	public String toString() {
		return toStringNoPassword() +" "+getPassword();
	}
	
	public JPanel UserInfo(boolean b) {
		JPanel UserInfo=new JPanel(new BorderLayout());
		
		Account=new JTextField(getAccount(), 17);
		Name=new JTextField(getName(), 17);
		TelNum=new JTextField(getTelNum(), 17);
		Address=new JTextField(getAddress(), 17);
		Email=new JTextField(getEmail(), 17);
		QQNum=new JTextField(getQQNum(), 17);
		
		JLabel account1=new JLabel("帐号：");
		JLabel name1=new JLabel("用户名：");
		JLabel telnum1=new JLabel("联系电话：");
		JLabel address1=new JLabel("联系地址：");
		JLabel email1=new JLabel("邮箱：");
		JLabel qqnum=new JLabel("QQ：");
		
		Account.setEditable(false);
		Name.setEditable(b);
		TelNum.setEditable(b);
		Address.setEditable(b);
		Email.setEditable(b);
		QQNum.setEditable(b);
		
		JPanel panel1=new JPanel(new GridLayout(6,1));
		panel1.add(account1);
		panel1.add(name1);
		panel1.add(telnum1);
		panel1.add(address1);
		panel1.add(email1);
		panel1.add(qqnum);
		
		JPanel panel2=new JPanel(new GridLayout(6,1));
		panel2.add(Account);
		panel2.add(Name);
		panel2.add(TelNum);
		panel2.add(Address);
		panel2.add(Email);
		panel2.add(QQNum);
		
		UserInfo.add(panel1, "West");
		UserInfo.add(panel2, "Center");
		
		return UserInfo;
	}
	
	public String getAccount2() {
		String account2=Account.getText();
		Account.setText(account2);
		return Account.getText();
	}
	
	public String getName2() {
		String name2=Name.getText();
		Name.setText(name2);
		return Name.getText();
	}
	
	public String getTelNum2() {
		String telnum2=TelNum.getText();
		TelNum.setText(telnum2);
		return TelNum.getText();
	}
	
	public String getAddress2() {
		String address2=Address.getText();
		Address.setText(address2);
		return Address.getText();
	}
	
	public String getEmail2() {
		String email2=Email.getText();
		Email.setText(email2);
		return Email.getText();
	}
	
	public String getQQNum2() {
		String qqnum2=QQNum.getText();
		QQNum.setText(qqnum2);
		return QQNum.getText();
	}
}
