
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class AddFareFrame extends JFrame {
	public static final int TEXT_ROWS=5;
	public static final int TEXT_COLUMNS=20;
	
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH=1440;
	private static final int DEFAULT_HEIGHT=1024;
	private JLabel farename;
	private JLabel material;
	private JLabel method;
	private JLabel picture;
	private JLabel video;
	private JLabel style;
	private JLabel hint;
	private JTextField Farename;
	private JTextField Style;
	private JTextArea Material;
	private JTextArea Method;
	private JTextArea Video;
	private JTextArea Picture;
	private JButton refer;
	private JButton cancel;
	
	public AddFareFrame() {
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		
		farename=new JLabel("*菜品名称：");
		farename.setFont(new Font("Serif",Font.BOLD,20));
		material=new JLabel("*使用材料：");
		material.setFont(new Font("Serif",Font.BOLD,20));
		method=new JLabel("*制作方法：");
		method.setFont(new Font("Serif",Font.BOLD,20));
		style=new JLabel("*菜品风格：");
		style.setFont(new Font("Serif",Font.BOLD,20));
		picture=new JLabel("*菜品图片：");
		picture.setFont(new Font("Serif",Font.BOLD,20));
		video=new JLabel("制作视频：");
		video.setFont(new Font("Serif",Font.BOLD,20));
		hint=new JLabel("“*”为必填项。");
		
		
		Farename=new JTextField();
		Farename.setFont(new Font("Serif",Font.PLAIN,20));
		Style=new JTextField();
		Style.setFont(new Font("Serif",Font.PLAIN,20));
		Material=new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		Material.setFont(new Font("Serif",Font.PLAIN,20));
		Method=new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		Method.setFont(new Font("Serif",Font.PLAIN,20));
		Video=new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		Video.setFont(new Font("Serif",Font.PLAIN,20));
		Picture=new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		Picture.setFont(new Font("Serif",Font.PLAIN,20));
		
		refer=new JButton("提交");
		refer.setFont(new Font("Serif",Font.PLAIN,20));
		cancel=new JButton("取消");
		cancel.setFont(new Font("Serif",Font.PLAIN,20));
		buttonPanel=new JPanel();
		buttonPanel.add(refer);
		buttonPanel.add(cancel);
		
		add(farename,new GBC(1,1).setAnchor(GBC.EAST).setWeight(25, 0));
		add(Farename,new GBC(2,1).setFill(GBC.HORIZONTAL).setWeight(50, 0));
		add(style,new GBC(1,2).setAnchor(GBC.EAST));
		add(Style,new GBC(2,2).setFill(GBC.HORIZONTAL));
		add(material,new GBC(1,3).setAnchor(GBC.NORTHEAST));
		add(Material,new GBC(2,3).setFill(GBC.HORIZONTAL));
		add(method,new GBC(1,4).setAnchor(GBC.NORTHEAST));
		add(Method,new GBC(2,4).setFill(GBC.HORIZONTAL));
		add(picture,new GBC(1,5).setAnchor(GBC.NORTHEAST));
		add(Picture,new GBC(2,5).setFill(GBC.HORIZONTAL));
		add(video,new GBC(1,6).setAnchor(GBC.NORTHEAST));
		add(Video,new GBC(2,6).setFill(GBC.HORIZONTAL));
		add(hint,new GBC(3,7).setAnchor(GBC.WEST).setWeight(25, 0));
		add(refer,new GBC(2,8).setAnchor(GBC.NORTHWEST));
		add(cancel,new GBC(2,8).setAnchor(GBC.CENTER));
	}
}
