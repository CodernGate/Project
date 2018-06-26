
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class BeginFrame extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH=1440;
	private static final int DEFAULT_HEIGHT=1024;
	private JCheckBox style;
	private JCheckBox name;
	private JTextField search;
	private JButton Enter;
	private JButton Login;
	private JButton Search;
	private JLabel Name;

	
	public BeginFrame() {
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		
		Name=new JLabel("菜谱信息管理系统");
		Name.setFont(new Font("Serif",Font.BOLD,64));
		
		Enter=new JButton("登录");
		Login=new JButton("注册");
		Search=new JButton("搜索");
		Search.setFont(new Font("Serif",Font.PLAIN,32));
		search=new JTextField();
		search.setFont(new Font("Serif",Font.PLAIN,36));
		buttonPanel=new JPanel();
		buttonPanel.add(Enter);
		buttonPanel.add(Login);
		buttonPanel.add(Search);
		buttonPanel.add(search);
		
		style=new JCheckBox("菜品风格：");
		name=new JCheckBox("菜品名称：");
		
		add(Name,new GBC(0, 2, 6, 3).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(Enter,new GBC(4, 0).setAnchor(GBC.EAST).setWeight(10, 50));
		add(Login,new GBC(5, 0).setAnchor(GBC.WEST).setWeight(10, 50));
		add(search,new GBC(1, 4, 1, 2).setFill(GBC.HORIZONTAL).setWeight(200, 200));
		add(Search,new GBC(2, 4, 1, 2).setAnchor(GBC.WEST).setWeight(100, 200));
		add(style, new GBC(0, 4, 1, 1).setAnchor(GBC.SOUTHEAST).setWeight(150, 250));
		add(name,new GBC(0, 5, 1, 1).setAnchor(GBC.NORTHEAST).setWeight(150, 300));
	}
}
