
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class FareInfoFrame extends JFrame {
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
	private JLabel comment1;
	private JLabel comment2;
	private JTextField Farename;
	private JTextField Style;
	private JTextArea Material;
	private JTextArea Method;
	private JTextArea Comment1;
	private JTextArea Comment2;
	private JButton Issue;
	
	public FareInfoFrame() {
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
//		GridBagLayout layout=new GridBagLayout();
//		setLayout(layout);
		
//		farename=new JLabel("菜品名称："+" ");
//		farename.setFont(new Font("Serif",Font.BOLD,20));
//		material=new JLabel("使用材料："+" ");
//		material.setFont(new Font("Serif",Font.BOLD,20));
//		method=new JLabel("制作方法："+" ");
//		method.setFont(new Font("Serif",Font.BOLD,20));
//		style=new JLabel("菜品风格："+" ");
//		style.setFont(new Font("Serif",Font.BOLD,20));
//		picture=new JLabel(" ");
//		picture.setFont(new Font("Serif",Font.BOLD,20));
//		video=new JLabel(" ");
//		video.setFont(new Font("Serif",Font.BOLD,20));
//		comment1=new JLabel("评论：");
//		comment1.setFont(new Font("Serif",Font.BOLD,20));
//		comment2=new JLabel("发表评论：");
//		comment2.setFont(new Font("Serif",Font.BOLD,20));
//		
//		Material=new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
//		Material.setFont(new Font("Serif",Font.PLAIN,20));
//		Method=new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
//		Method.setFont(new Font("Serif",Font.PLAIN,20));
//		Comment1=new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
//		Comment1.setFont(new Font("Serif",Font.PLAIN,20));
//		Comment2=new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
//		Comment2.setFont(new Font("Serif",Font.PLAIN,20));
//		
//		Issue=new JButton("提交");
//		
//		add(farename,new GBC(1,1).setAnchor(GBC.EAST));
//		add(style,new GBC(1,2).setAnchor(GBC.EAST));
//		add(picture,new GBC(2,1).setAnchor(GBC.CENTER));
//		add(video,new GBC(2,2).setAnchor(GBC.CENTER));
//		add(material,new GBC(1,3).setAnchor(GBC.NORTHEAST).setWeight(25, 0));
//		add(Material,new GBC(2,3).setFill(GBC.HORIZONTAL).setWeight(50, 0));
//		add(method,new GBC(1,4).setAnchor(GBC.NORTHEAST));
//		add(Method,new GBC(2,4).setFill(GBC.HORIZONTAL));
//		add(comment1,new GBC(1,5).setAnchor(GBC.NORTHEAST));
//		add(Comment1,new GBC(2,5).setFill(GBC.HORIZONTAL));
//		add(comment2,new GBC(1,6).setAnchor(GBC.NORTHEAST));
//		add(Comment2,new GBC(2,6).setFill(GBC.HORIZONTAL));
//		add(Issue,new GBC(3,7).setAnchor(GBC.WEST).setWeight(25, 0));
		
		JPanel DishPanel = new JPanel();
		DishPanel.setBorder(BorderFactory.createTitledBorder(""));
//		DishPanel.add(buttonPanel);
		
		
		JPanel InfoPanel = new JPanel();
		InfoPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		setLayout(new BorderLayout());
		add(DishPanel, BorderLayout.WEST);
		add(InfoPanel, BorderLayout.CENTER);
	}
}
