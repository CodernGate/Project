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
import javax.swing.JTextArea;
import java.io.*;

public class AddDishFrame extends JPanel {
	private boolean ok;
	private JDialog dialog;
	private JLabel dishname;
	private JLabel style;
	private JLabel material;
	private JLabel method;
	private JTextField Dishname;
	private JTextField Style;
	private JTextArea Material;
	private JTextArea Method;
	private JButton Issue;
	private JButton Cancel;
	private JButton picture;
	private JButton video;
	private JPanel buttonPanel;
	private JPanel panel;
	
	public AddDishFrame() {
		setLayout(new BorderLayout());
		
		JPanel panel1=new JPanel(new GridLayout(2, 2));
		dishname=new JLabel("菜品名称：", SwingConstants.RIGHT);
		dishname.setFont(new Font("Serif", Font.BOLD, 24));
		style=new JLabel("菜品风格：", SwingConstants.RIGHT);
		style.setFont(new Font("Serif", Font.BOLD, 24));
		Dishname=new JTextField("");
		Dishname.setFont(new Font("Serif", Font.PLAIN, 24));
		Style=new JTextField("");
		Style.setFont(new Font("Serif", Font.PLAIN, 24));
		panel1.add(dishname);
		panel1.add(Dishname);
		panel1.add(style);
		panel1.add(Style);
		
		JPanel panel2=new JPanel(new GridLayout(2, 2));
		material=new JLabel("使用材料：", SwingConstants.RIGHT);
		material.setFont(new Font("Serif", Font.BOLD, 24));
		method=new JLabel("制作方法：", SwingConstants.RIGHT);
		method.setFont(new Font("Serif", Font.BOLD, 24));
		Material=new JTextArea("");
		Material.setFont(new Font("Serif", Font.PLAIN, 24));
		Method=new JTextArea("");
		Method.setFont(new Font("Serif", Font.PLAIN, 24));
		panel2.add(material);
		panel2.add(Material);
		panel2.add(method);
		panel2.add(Method);
		
		panel=new JPanel(new GridLayout(2,1));
		panel.add(panel1);
		panel.add(panel2);
		add(panel, BorderLayout.CENTER);
		
		picture=new JButton("选择图片");
		picture.setFont(new Font("Serif", Font.PLAIN, 24));
		
		video=new JButton("选择视频");
		video.setFont(new Font("Serif", Font.PLAIN, 24));
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
		buttonPanel.add(picture);
		buttonPanel.add(video);
		buttonPanel.add(Issue);
		buttonPanel.add(Cancel);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public String getDishName() {
		return Dishname.getText();
	}
	
	public String getStyle() {
		return Style.getText();
	}
	
	public String getMaterial() {
		return Material.getText();
	}
	
	public String getMethod() {
		return Method.getText();
	}
	
	public String getPicture() {
		return picture.getText();
	}
	
	public String getVideo() {
		return video.getText();
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
