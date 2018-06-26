import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserRegisterFrame extends JPanel {
	private boolean ok;
	private JDialog dialog;
	private JLabel usernameLabel;
	private JLabel accountLabel;
	private JLabel password1Label;
	private JLabel password2Label;
	private JLabel authcodeLabel;
	private JLabel telLabel;
	private JLabel emailLabel;
	private JTextField usernameTextField;
	private JTextField accountTextField;
	private JPasswordField password1PasswordField;
	private JPasswordField password2PasswordField;
	private JTextField authCodeTextField;
	private JTextField telTextField;
	private JTextField emailTextField;
	private JButton authcodeButton;
	private JButton cancelButton;
	private JButton registerButton;
	private JPanel buttonPanel;
	private JPanel panel;
//	private Monitor monitor;//新建监听器使“取消”按钮能够关闭JPanel.
	
	public UserRegisterFrame() {
		
		setLayout(new BorderLayout());
		
		accountLabel=new JLabel("请输入帐号：");
		accountLabel.setFont(new Font("Serif",Font.BOLD,20));
		usernameLabel=new JLabel("请输入用户名：");
		usernameLabel.setFont(new Font("Serif",Font.BOLD,20));
		password1Label=new JLabel("请输入密码：");
		password1Label.setFont(new Font("Serif",Font.BOLD,20));
		password2Label=new JLabel("请再次输入密码：");
		password2Label.setFont(new Font("Serif",Font.BOLD,20));
		telLabel=new JLabel("请输入手机号码：");
		telLabel.setFont(new Font("Serif",Font.BOLD,20));
		authcodeLabel=new JLabel("请输入验证码：");
		authcodeLabel.setFont(new Font("Serif",Font.BOLD,20));
		emailLabel=new JLabel("请输入邮箱：");
		emailLabel.setFont(new Font("Serif",Font.BOLD,20));
		
		accountTextField=new JTextField();
		accountTextField.setFont(new Font("Serif",Font.PLAIN,20));
		usernameTextField=new JTextField();
		usernameTextField.setFont(new Font("Serif",Font.PLAIN,20));
		password1PasswordField=new JPasswordField();
		password1PasswordField.setFont(new Font("Serif",Font.PLAIN,20));
		password2PasswordField=new JPasswordField();
		password2PasswordField.setFont(new Font("Serif",Font.PLAIN,20));
		telTextField=new JTextField();
		telTextField.setFont(new Font("Serif",Font.PLAIN,20));
		authCodeTextField=new JTextField();
		authCodeTextField.setFont(new Font("Serif",Font.PLAIN,20));
		emailTextField=new JTextField();
		emailTextField.setFont(new Font("Serif",Font.PLAIN,20));
		
		registerButton=new JButton("确定");
		registerButton.setFont(new Font("Serif",Font.PLAIN,28));
		registerButton.addActionListener(event -> {
			ok = true;
			dialog.setVisible(false);
		});
		cancelButton=new JButton("取消");
		cancelButton.setFont(new Font("Serif",Font.PLAIN,28));
		cancelButton.addActionListener(event->{
			dialog.setVisible(false);
		});
		
		authcodeButton=new JButton("获取验证码");
		authcodeButton.setFont(new Font("Serif",Font.PLAIN,28));
		
		panel=new JPanel();
		panel.setLayout(new GridLayout(6,2));
		panel.add(accountLabel);
		panel.add(accountTextField);
		panel.add(usernameLabel);
		panel.add(usernameTextField);
		panel.add(password1Label);
		panel.add(password1PasswordField);
		panel.add(password2Label);
		panel.add(password2PasswordField);
		panel.add(telLabel);
		panel.add(telTextField);
		panel.add(emailLabel);
		panel.add(emailTextField);

		add(panel, BorderLayout.CENTER);
		
		buttonPanel=new JPanel();
		buttonPanel.add(registerButton);
		buttonPanel.add(cancelButton);

		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public String getAccount() {
		return accountTextField.getText();
	}
	
	public String getUsername() {
		return usernameTextField.getText();
	}
	
	public String getPassword1() {
		String passwordOne = new String(password1PasswordField.getPassword());
		return passwordOne;
	}
	
	public String getPassword2() {
		String passwordTwo = new String(password2PasswordField.getPassword());
		return passwordTwo;
	}
	
	public String getEmail() {
		return emailTextField.getText();
	}
	
	public String getTelNum() {
		return telTextField.getText();
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
			dialog.getRootPane().setDefaultButton(registerButton);
			dialog.pack();
		}

		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
}
