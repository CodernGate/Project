
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class PersonalFrame extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH=1440;
	private static final int DEFAULT_HEIGHT=1024;
	private JLabel username;
	private JLabel useraccount;
	private JLabel userpicture;
	private JButton myInfo;
	private JButton myFare;
	private JButton addFare;
	private JButton search;
	private JButton modifypassword;
	private JButton Exit;
	private JButton userPicture;
	private JRadioButton style;
	private JRadioButton name;
	private JTextField Search;
	
	public PersonalFrame() {
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		username=new JLabel("�û�����"+" ");
		username.setFont(new Font("Serif",Font.BOLD,14));
		useraccount=new JLabel("�˺ţ�"+" ");
		useraccount.setFont(new Font("Serif",Font.BOLD,14));
		userpicture=new JLabel();
		
		myInfo=new JButton("������Ϣ");
		myInfo.setFont(new Font("Serif",Font.PLAIN,28));
		myFare=new JButton("�ҵĲ�Ʒ");
		myFare.setFont(new Font("Serif",Font.PLAIN,28));
		addFare=new JButton("��Ӳ�Ʒ");
		addFare.setFont(new Font("Serif",Font.PLAIN,28));
		search=new JButton("���Ҳ�Ʒ");
		search.setFont(new Font("Serif",Font.PLAIN,28));
		modifypassword=new JButton("�޸�����");
		modifypassword.setFont(new Font("Serif",Font.PLAIN,28));
		Exit=new JButton("�˳���¼");
		Exit.setFont(new Font("Serif",Font.PLAIN,28));
		userPicture=new JButton("����ͷ��");
		
		style=new JRadioButton("��Ʒ���", true);
		name=new JRadioButton("��Ʒ����");
		
		ButtonGroup group=new ButtonGroup();
		group.add(style);
		group.add(name);
		
		Search=new JTextField();
		Search.setFont(new Font("Serif",Font.PLAIN,36));
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(style);
		panel.add(name);
		
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridLayout(12, 1));
		buttonPanel.add(Search);
		buttonPanel.add(panel);
		buttonPanel.add(search);
		buttonPanel.add(userpicture);
		buttonPanel.add(userPicture);
		buttonPanel.add(useraccount);
		buttonPanel.add(username);
		buttonPanel.add(myInfo);
		buttonPanel.add(myFare);
		buttonPanel.add(addFare);
		buttonPanel.add(modifypassword);
		buttonPanel.add(Exit);
		
		JPanel PersonPanel = new JPanel();
		PersonPanel.setBorder(BorderFactory.createTitledBorder(""));
		PersonPanel.add(buttonPanel);
		
		
		JPanel InfoPanel = new JPanel();
		InfoPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		setLayout(new BorderLayout());
		add(PersonPanel, BorderLayout.WEST);
		add(InfoPanel, BorderLayout.CENTER);
	}
}
