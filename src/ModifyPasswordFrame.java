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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ModifyPasswordFrame extends JPanel {
	private boolean ok;
	private JDialog dialog;
	private JLabel password1;
	private JLabel password2;
	private JLabel password3;
	private JPasswordField Password1;
	private JPasswordField Password2;
	private JPasswordField Password3;
	private JButton Issue;
	private JButton Cancel;
	private JPanel Panel;
	private JPanel buttonPanel;
	
	public ModifyPasswordFrame() {
		setLayout(new BorderLayout());
		
		password1=new JLabel("请输入旧密码：", SwingConstants.RIGHT);
		password1.setFont(new Font("Serif", Font.BOLD, 24));
		password2=new JLabel("请输入新密码：", SwingConstants.RIGHT);
		password2.setFont(new Font("Serif", Font.BOLD, 24));
		password3=new JLabel("请再次输入新密码：", SwingConstants.RIGHT);
		password3.setFont(new Font("Serif", Font.BOLD, 24));
		Password1=new JPasswordField("");
		Password1.setFont(new Font("Serif", Font.PLAIN, 24));
		Password2=new JPasswordField("");
		Password2.setFont(new Font("Serif", Font.PLAIN, 24));
		Password3=new JPasswordField("");
		Password3.setFont(new Font("Serif", Font.PLAIN, 24));
		Panel=new JPanel(new GridLayout(3, 2));
		Panel.add(password1);
		Panel.add(Password1);
		Panel.add(password2);
		Panel.add(Password2);
		Panel.add(password3);
		Panel.add(Password3);
		add(Panel, BorderLayout.CENTER);
		
		Issue=new JButton("提交");
		Issue.setFont(new Font("Serif", Font.PLAIN, 24));
		Issue.addActionListener(event -> {
			ok = true;
			dialog.setVisible(false);
		});
		Cancel=new JButton("取消");
		Cancel.setFont(new Font("Serif", Font.PLAIN, 24));
		Cancel.addActionListener(event -> dialog.setVisible(false));
		buttonPanel=new JPanel();
		buttonPanel.add(Issue);
		buttonPanel.add(Cancel);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public String getOldPassword() {
		String oldpassword=new String(Password1.getPassword());
		return oldpassword;
	}
	
	public String getNewPassword1() {
		String newpassword1=new String(Password2.getPassword());
		return newpassword1;
	}
	
	public String getNewPassword2() {
		String newpassword2=new String(Password3.getPassword());
		return newpassword2;
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
			dialog.getRootPane().setDefaultButton(Issue);
			dialog.pack();
		}

		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
}
