import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserEnterFrame extends JPanel {
	private boolean ok;
	private JDialog dialog;
	private JLabel username;
	private JLabel password;
	private JTextField Username;
	private JPasswordField Password;
	private JButton Enter;
	private JButton Cancel;
	private JButton Forget;
	private JButton Login;
	private JPanel buttonPanel;
	private JPanel panel;
	
	public UserEnterFrame(){
		
		setLayout(new BorderLayout());
		
		
		panel=new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		username=new JLabel("用户名：");
		username.setFont(new Font("Serif",Font.BOLD,28));
		password=new JLabel("密码：");
		password.setFont(new Font("Serif",Font.BOLD,28));
		Username=new JTextField("");
		Username.setFont(new Font("Serif",Font.PLAIN,28));
		Password=new JPasswordField();
		Password.setFont(new Font("Serif",Font.PLAIN,28));
		panel.add(username);
		panel.add(Username);
		panel.add(password);
		panel.add(Password);
		add(panel, BorderLayout.CENTER);
		
		Enter=new JButton("登录");
		Enter.setFont(new Font("Serif",Font.PLAIN,28));
		Enter.addActionListener(event -> {
			ok = true;
			dialog.setVisible(false);
		});
		Cancel=new JButton("取消");
		Cancel.setFont(new Font("Serif",Font.PLAIN,28));
		Cancel.addActionListener(event -> dialog.setVisible(false));
		Forget=new JButton("忘记密码？");
		Forget.setFont(new Font("Serif",Font.PLAIN,28));
		Login=new JButton("注册");
		Login.setFont(new Font("Serif",Font.PLAIN,28));
		buttonPanel=new JPanel();
		buttonPanel.add(Forget);
		buttonPanel.add(Login);
		buttonPanel.add(Enter);
		buttonPanel.add(Cancel);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public String getUser()
	{
		return Username.getText();
	}
	
	public String getPassword() {
		String password;
		password=new String(Password.getPassword());
		return password;
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
			dialog.getRootPane().setDefaultButton(Enter);
			dialog.pack();
		}

		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
}
