import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserLoginFrame extends JPanel {
	private boolean ok;
	private JDialog dialog;
	private JLabel username;
	private JLabel account;
	private JLabel password1;
	private JLabel password2;
	private JLabel Authcode;
	private JLabel Tel;
	private JLabel email;
	private JTextField Username;
	private JTextField Account;
	private JPasswordField Password1;
	private JPasswordField Password2;
	private JTextField AuthCode;
	private JTextField tel;
	private JTextField Email;
	private JButton authcode;
	private JButton Cancel;
	private JButton Login;
	private JPanel buttonPanel;
	private JPanel panel;
	
	public UserLoginFrame() {
		
		setLayout(new BorderLayout());
		
		account=new JLabel("请输入帐号：");
		account.setFont(new Font("Serif",Font.BOLD,28));
		username=new JLabel("请输入用户名：");
		username.setFont(new Font("Serif",Font.BOLD,28));
		password1=new JLabel("请输入密码：");
		password1.setFont(new Font("Serif",Font.BOLD,28));
		password2=new JLabel("请再次输入密码：");
		password2.setFont(new Font("Serif",Font.BOLD,28));
		Tel=new JLabel("请输入手机号码：");
		Tel.setFont(new Font("Serif",Font.BOLD,28));
		Authcode=new JLabel("请输入验证码：");
		Authcode.setFont(new Font("Serif",Font.BOLD,28));
		email=new JLabel("请输入邮箱：");
		email.setFont(new Font("Serif",Font.BOLD,28));
		
		Account=new JTextField();
		Account.setFont(new Font("Serif",Font.PLAIN,28));
		Username=new JTextField();
		Username.setFont(new Font("Serif",Font.PLAIN,28));
		Password1=new JPasswordField();
		Password1.setFont(new Font("Serif",Font.PLAIN,28));
		Password2=new JPasswordField();
		Password2.setFont(new Font("Serif",Font.PLAIN,28));
		tel=new JTextField();
		tel.setFont(new Font("Serif",Font.PLAIN,28));
		AuthCode=new JTextField();
		AuthCode.setFont(new Font("Serif",Font.PLAIN,28));
		Email=new JTextField();
		Email.setFont(new Font("Serif",Font.PLAIN,28));
		
		Login=new JButton("注册");
		Login.setFont(new Font("Serif",Font.PLAIN,28));
		Login.addActionListener(event -> {
			ok = true;
			dialog.setVisible(false);
		});
		Cancel=new JButton("取消");
		Cancel.setFont(new Font("Serif",Font.PLAIN,28));
		Cancel.addActionListener(event -> setVisible(false));
		authcode=new JButton("获取验证码");
		authcode.setFont(new Font("Serif",Font.PLAIN,28));
		
		panel=new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.add(account);
		panel.add(Account);
		panel.add(username);
		panel.add(Username);
		panel.add(password1);
		panel.add(Password1);
		panel.add(password2);
		panel.add(Password2);
		add(panel, BorderLayout.CENTER);
		
		buttonPanel=new JPanel();
		buttonPanel.add(Login);
		buttonPanel.add(Cancel);
//		buttonPanel.add(authcode);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public String getAccount() {
		return Account.getText();
	}
	
	public String getUsername() {
		return Username.getText();
	}
	
	public String getPassword1() {
		String PasswordOne = new String(Password1.getPassword());
		return PasswordOne;
	}
	
	public String getPassword2() {
		String PasswordTwo = new String(Password2.getPassword());
		return PasswordTwo;
	}
	
	public boolean showDialog(Component parent, String title)
	{
		ok = false;

		Frame owner = null;
		if (parent instanceof Frame)
			owner = (Frame) parent;
		else
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);

		if (dialog == null || dialog.getOwner() != owner)
		{
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(Login);
			dialog.pack();
		}

		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
}
