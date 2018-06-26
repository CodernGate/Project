import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOFISGUI extends JPanel {
	private static BufferedReader  stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
	private static PrintWriter  stdOut =
			new  PrintWriter(System.out, true);
	private static PrintWriter  stdErr =
			new  PrintWriter(System.err, true);
	
	private UserDatabase usersDB;
	private DishDatabase dishesDB;
	private String[] fareName;
	private int index=0;
	
	private JPanel panel;
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH=1440;
	private static final int DEFAULT_HEIGHT=1024;
	private JRadioButton style;
	private JRadioButton name;
	private JTextField searchTextField;
	private JButton enterButton;
	private JButton registerButton;
	private JButton searchButton;
	private JLabel nameLabel;
	private ButtonGroup group;
	private UserEnterFrame dialog1 = null;
	private UserRegisterFrame dialog2 = null;
	private AddDishFrame dialog3 = null;
	private ModifyPasswordFrame dialog4 = null;

	private JLabel usernameLabel;
	private JLabel useraccountLabel;
	private JLabel userpictureLabel;
	private JButton myInfoButton;
	private JButton myFareButton;
	private JButton addFareButton;
	private JButton fareSearchButton;
	private JButton modifyPasswordButton;
	private JButton exitButton;
	private JButton userPictureButton;
	private JTextField fareSearchTextField;
	private JPanel personPanel;
	private JPanel dishPanel;
	private JPanel InfoPanel;
	private JPanel selectPanel;
	private JList fareList;
	private JButton returnButton;
	
	
	public static JFrame frame1;
	public static JFrame frame2;

	
	public static void  main(String[]  args) throws IOException  {
		
		frame1 = new JFrame("菜谱信息管理系统");

		frame1.setContentPane(new BOFISGUI());
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame1.setResizable(true);
		frame1.setVisible(true);
		
	}
	
	public BOFISGUI() {
		try {
			this.usersDB=loadUsersDB();
			this.dishesDB=loadDishesDB();

		} catch (FileNotFoundException fnfe) {
			stdErr.println("The file does not exist!");
			System.exit(1);
		} catch (DataFormatException dfe) {
			stdErr.println("The file contains malformed data: "
			               + dfe.getMessage());
			System.exit(1);
		} catch(IOException ioe) {
			stdErr.print("IOException ocurrs!");
			System.exit(1);
		}
		
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		
		nameLabel=new JLabel("菜谱信息管理系统");
		nameLabel.setFont(new Font("Serif",Font.BOLD,64));
		
		enterButton=new JButton("登录");
		enterButton.addActionListener(new UserEnter());
		registerButton=new JButton("注册");
		registerButton.addActionListener(new UserRegister());
		searchButton=new JButton("搜索");
		searchButton.setFont(new Font("Serif",Font.PLAIN,32));
		searchButton.addActionListener(event->{
			try {
				Search1(null, 1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		searchTextField=new JTextField();
		searchTextField.setFont(new Font("Serif",Font.PLAIN,36));
		buttonPanel=new JPanel();
		buttonPanel.add(enterButton);
		buttonPanel.add(registerButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(searchTextField);
		
		style=new JRadioButton("菜品风格：", true);
		name=new JRadioButton("菜品名称：");
		
		group=new ButtonGroup();
		group.add(style);
		group.add(name);
		
		
		add(nameLabel,new GBC(0, 2, 6, 3).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(enterButton,new GBC(4, 0).setAnchor(GBC.EAST).setWeight(10, 50));
		add(registerButton,new GBC(5, 0).setAnchor(GBC.WEST).setWeight(10, 50));
		add(searchTextField,new GBC(1, 4, 1, 2).setFill(GBC.HORIZONTAL).setWeight(200, 200));
		add(searchButton,new GBC(2, 4, 1, 2).setAnchor(GBC.WEST).setWeight(100, 200));
		add(style, new GBC(0, 4, 1, 1).setAnchor(GBC.SOUTHEAST).setWeight(150, 250));
		add(name,new GBC(0, 5, 1, 1).setAnchor(GBC.NORTHEAST).setWeight(150, 300));
		
	}
	
	public String getSearch() {
		String text=searchTextField.getText();
		return text;
	}
	
	public String getChoose() {
		String choose;
		if(group.isSelected(style.getModel())) {
			choose="菜品风格";
		}else{
			choose="菜品名称";
		}
		return choose;
	}
	
	private UserDatabase loadUsersDB() throws FileNotFoundException ,
					DataFormatException , IOException{
		UserDatabase udb=UserDatabase.getSingeltonInstance();
		
		BufferedReader br = new BufferedReader(new FileReader("UsersDB.txt") );
		String line = br.readLine();
		while(line!=null) {
			StringTokenizer st = new StringTokenizer(line, " ");
			User someUser = new User();
			someUser.setAccount(st.nextToken());
			someUser.setName(st.nextToken());
			someUser.setTelNum(st.nextToken());
			someUser.setAddress(st.nextToken());
			someUser.setEmail(st.nextToken());
			someUser.setQQNum(st.nextToken());
			someUser.setPassword(st.nextToken());
			
			udb.addUser(someUser);
			
			line = br.readLine();
		}
		br.close();
		
		return udb;
	}
	
	private DishDatabase loadDishesDB() throws FileNotFoundException ,
				DataFormatException , IOException{
		DishDatabase ddb=DishDatabase.getSingeltonInstance();
		
		BufferedReader br = new BufferedReader(new FileReader("DishesDB.txt") );
		String line = br.readLine();
		while(line!=null) {
			StringTokenizer st = new StringTokenizer(line, " ");
			Dish someDish = new Dish();
			someDish.setCode(st.nextToken());
			someDish.setName(st.nextToken());
			someDish.setMaterial(st.nextToken());
			someDish.setMethod(st.nextToken());
			someDish.setPictureAddress(st.nextToken());
			someDish.setVideoAddress(st.nextToken());
			someDish.setComment(st.nextToken());
			someDish.setStyle(st.nextToken());
			someDish.setPromulgatorAccount(st.nextToken());

			ddb.addDish(someDish);
			
			line = br.readLine();
		}
		br.close();
		
		return ddb;
	}
	
	private class UserEnter implements ActionListener {
		public void actionPerformed(ActionEvent event){
			int x;
			x=1;
			if (dialog1 == null) {
				dialog1 = new UserEnterFrame();
			}
			if (dialog1.showDialog(BOFISGUI.this, "登录"))
			{
				
				String account;
				String password;
				User user=null;
				account=dialog1.getUser();
				password=dialog1.getPassword();
				if(account.equals("")||password.equals("")) {
					JOptionPane.showMessageDialog(
							null,
							"请输入账号和密码! ");
				}
				
				for(Iterator i = usersDB.getUsersIterator(); i.hasNext();) {
					user=(User) i.next();
					if(account.equals(user.getAccount())&&password.equals(user.getPassword())) {
						JOptionPane.showMessageDialog(
								null,
								"登陆成功！\n  欢迎您 "+user.getName()+"\n   : )");
						try {
							UsersRun(user);
						}catch(IllegalArgumentException | IOException e){
							stdErr.println(e);
							x=0;
						}finally {
							x=0;
						}
						break;
					}
				}
				if(x!=0) {
					JOptionPane.showMessageDialog(
							null,
							"账号或密码错误！");
				}
			}
		}
	}
	
	class UserRegister implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			int x;
			String account = null;
			String userName = null;
			String password1 = null;
			String password2 = null;
			String email=null;
			String telnum=null;
				
			x=1;
			if (dialog2 == null) 
				dialog2 = new UserRegisterFrame();
			if (dialog2.showDialog(BOFISGUI.this, "注册"))
			{
				email=dialog2.getEmail();
				account=dialog2.getAccount();
				userName=dialog2.getUsername();
				telnum=dialog2.getTelNum();
				do {
					password1=dialog2.getPassword1();
					password2=dialog2.getPassword2();
					if(!password1.equals(password2)) {
						JOptionPane.showMessageDialog(
								null,
								"两次密码输入不一样！");
					}
					
				}while(!password2.equals(password1));
				int y=usersDB.getNumberOfUsers();
				if(y!=0) {
					for(Iterator i = usersDB.getUsersIterator(); i.hasNext();) {
						User user=(User) i.next();
						if(account.equals(user.getAccount())) {
							JOptionPane.showMessageDialog(
									null,
									"该账号已存在！");
							x=0;
						}
					}
				}
				if(account.equals("")||userName.equals("")||password2.equals("")) {
					JOptionPane.showMessageDialog(
							null,
							"账号/用户名/密码不能为空！");
					x=0;
				}
				index=0;
				
				for(int j=0;j<email.length();j++) {
					if(email.charAt(j)=='@')
						index++;
				}
				if(!email.contains(".com")) {
					index=0;
					for(int j=0;j<email.length();j++) {
						if(email.charAt(j)=='@')
						index++;
					}
				}
				if(((index!=1)||(!email.contains(".com")))&&(email.length()!=0)) {
					JOptionPane.showMessageDialog(
							null,
							"邮箱格式错误！");
					x=0;
				}
				if(x!=0) {
					try {
						if(account.equals("")||userName.equals("")||password2.equals("")) {
							return;
						}
						
						User user=new User();
						user.setAccount(account);
						user.setName(userName);
						user.setTelNum(telnum);
						user.setAddress("NoAddress");
						user.setEmail(email);
						user.setQQNum("NoQQNum");
						user.setPassword(password2);
						usersDB.addUser(user);
							
						PrintWriter pt = new PrintWriter(new FileWriter("UsersDB.txt",true));
						pt.println(user.toString());
						pt.close();
						
						JOptionPane.showMessageDialog(
								null,
								"注册成功,并已经录入本地数据库！\n请尽快完善个人信息");
						
						UsersRun(user);
					}catch(IllegalArgumentException | IOException e) {
						stdErr.println(e);
					}
				}
			}
		}
	}
	
	public void Search1(User user, int x) throws IOException {
		String choose=getChoose();
		String text=getSearch();

		displayDishInfo(user, x);
		
		if(choose.equals("菜品风格")) {
			SearchByStyle(text, user);
		}else if(choose.equals("菜品名称")){
			SearchByFarename(text, user);
		}
	}
	
	public void SearchByStyle(String text, User user) throws IOException {
		int num=0;
		fareName=new String[dishesDB.getNumberOfDishs()];
		for(Iterator i = dishesDB.getDishsIterator(); i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(text.equals(dish.getStyle())) {
				fareName[num]=dish.getCode()+" "+dish.getName();
				num++;
			}
		}
		if(num==0)
			JOptionPane.showMessageDialog(
					null,
					"没有这种风格的菜品~");
		else {
			fareList=new JList(fareName);
			fareList.setFont(new Font("Serif",Font.BOLD,20));
			fareList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			fareList.setVisibleRowCount(10);
			fareList.setFixedCellWidth(150);
			
			if(user==null)
				fareList.addListSelectionListener(event->DishInfo(null, 1));
			else
				fareList.addListSelectionListener(event->DishInfo(user.getAccount(), 1));
				
			selectPanel.removeAll();
			selectPanel.setVisible(false);
			selectPanel.add(fareList);
			selectPanel.setVisible(true);
		}
		fareName=null;
	}
	
	public void SearchByFarename(String text, User user) throws IOException {
		Dish dish=null;
		int num=0;
		fareName=new String[dishesDB.getNumberOfDishs()];
		for(Iterator i = dishesDB.getDishsIterator(); i.hasNext();) {
			dish=(Dish) i.next();
			if(text.equals(dish.getName()) ) {
				fareName[num]=dish.getCode()+" "+dish.getName();
				num++;
			}
		}
		if(num==0) {
			JOptionPane.showMessageDialog(null, "您搜索的菜品不存在");
			return;
		}else {
			fareList=new JList(fareName);
			fareList.setFont(new Font("Serif",Font.BOLD,20));
			fareList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			fareList.setVisibleRowCount(10);
			fareList.setFixedCellWidth(150);
			
			if(user==null)
				fareList.addListSelectionListener(event->DishInfo(null, 1));
			else
				fareList.addListSelectionListener(event->DishInfo(user.getAccount(), 1));
				
			selectPanel.removeAll();
			selectPanel.setVisible(false);
			selectPanel.add(fareList);
			selectPanel.setVisible(true);
		}
		fareName=null;
	}
	
	public void displayDishInfo(User user, int x) {
		frame1.dispose();
		
		frame1=new JFrame("菜谱信息管理系统-菜品信息");
		
		frame1.setContentPane(new DishFrame(user, x));
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame1.setResizable(true);
		frame1.setVisible(true);
	}
	
	private class DishFrame extends JPanel{
		public DishFrame(User user, int x) {
			
			returnButton=new JButton("返回");
			returnButton.setFont(new Font("Serif", Font.BOLD, 20));
			returnButton.addActionListener(event->{
				if(x==1) {
					frame1.dispose();
					
					frame1=new JFrame("菜谱信息管理系统");
					
					frame1.setContentPane(new BOFISGUI());
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
					frame1.setResizable(true);
					frame1.setVisible(true);
				}else if(x==2) {
					frame1.dispose();
					
					frame1=new JFrame("菜谱信息管理系统-个人中心");
					
					frame1.setContentPane(new PersonalFrame(user));
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
					frame1.setResizable(true);
					frame1.setVisible(true);
				}
			});
			if(user==null) {
				usernameLabel=new JLabel("用户名：未登录");
				usernameLabel.setFont(new Font("Serif",Font.BOLD,14));
				useraccountLabel=new JLabel("帐号：未登录");
				useraccountLabel.setFont(new Font("Serif",Font.BOLD,14));
			}else {
				usernameLabel=new JLabel("用户名："+user.getName());
				usernameLabel.setFont(new Font("Serif",Font.BOLD,14));
				useraccountLabel=new JLabel("账号："+user.getAccount());
				useraccountLabel.setFont(new Font("Serif",Font.BOLD,14));
			}
			
			
			buttonPanel=new JPanel(new GridLayout(3,1));
			buttonPanel.add(returnButton);
			buttonPanel.add(useraccountLabel);
			buttonPanel.add(usernameLabel);
			
			selectPanel = new JPanel();
			selectPanel.setBorder(BorderFactory.createTitledBorder(""));
			
			dishPanel = new JPanel();
			dishPanel.setLayout(new GridLayout(2, 1));
			dishPanel.setBorder(BorderFactory.createTitledBorder(""));
			dishPanel.add(buttonPanel);
			dishPanel.add(selectPanel);
			
			InfoPanel = new JPanel();
			InfoPanel.setBorder(BorderFactory.createTitledBorder(""));
			
			setLayout(new BorderLayout());
			add(dishPanel, BorderLayout.WEST);
			add(InfoPanel, BorderLayout.CENTER);
		}
	}
	
	public void UsersRun(User user) throws IOException{
		frame1.dispose();
		
		frame1=new JFrame("菜谱信息管理系统-个人中心");
		
		frame1.setContentPane(new PersonalFrame(user));
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame1.setResizable(true);
		frame1.setVisible(true);
		
		
	}
	
	private class PersonalFrame extends JPanel{
		public PersonalFrame(User user) {
			
			usernameLabel=new JLabel("用户名："+user.getName());
			usernameLabel.setFont(new Font("Serif",Font.BOLD,14));
			useraccountLabel=new JLabel("账号："+user.getAccount());
			useraccountLabel.setFont(new Font("Serif",Font.BOLD,14));
			userpictureLabel=new JLabel();
			
			myInfoButton=new JButton("个人信息");
			myInfoButton.setFont(new Font("Serif",Font.PLAIN,28));
			myInfoButton.addActionListener(event-> PersonInfo(user.getAccount()));
			myFareButton=new JButton("我的菜品");
			myFareButton.setFont(new Font("Serif",Font.PLAIN,28));
			myFareButton.addActionListener(event-> myFare(user.getAccount()));
			addFareButton=new JButton("添加菜品");
			addFareButton.setFont(new Font("Serif",Font.PLAIN,28));
			addFareButton.addActionListener(event-> AddDish(user.getAccount()));
			fareSearchButton=new JButton("查找菜品");
			fareSearchButton.setFont(new Font("Serif",Font.PLAIN,28));
			fareSearchButton.addActionListener(event->{
				try {
					Search2(user, 2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			modifyPasswordButton=new JButton("修改密码");
			modifyPasswordButton.setFont(new Font("Serif",Font.PLAIN,28));
			modifyPasswordButton.addActionListener(event-> ModifyPassword(user.getAccount()));
			exitButton=new JButton("退出登录");
			exitButton.setFont(new Font("Serif",Font.PLAIN,28));
			exitButton.addActionListener(event-> Exit());
			userPictureButton=new JButton("设置头像");
			
			style=new JRadioButton("菜品风格", true);
			name=new JRadioButton("菜品名称");
			
			
			group=new ButtonGroup();
			group.add(style);
			group.add(name);
			
			fareSearchTextField=new JTextField();
			fareSearchTextField.setFont(new Font("Serif",Font.PLAIN,36));
			
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(1,2));
			panel.add(style);
			panel.add(name);
			
			buttonPanel=new JPanel();
			buttonPanel.setLayout(new GridLayout(13, 1));
			buttonPanel.add(fareSearchTextField);
			buttonPanel.add(panel);
			buttonPanel.add(fareSearchButton);
			buttonPanel.add(userpictureLabel);
			buttonPanel.add(userPictureButton);
			buttonPanel.add(useraccountLabel);
			buttonPanel.add(usernameLabel);
			buttonPanel.add(myInfoButton);
			buttonPanel.add(myFareButton);
			buttonPanel.add(addFareButton);
			buttonPanel.add(modifyPasswordButton);
			buttonPanel.add(exitButton);
			
			selectPanel = new JPanel();
			selectPanel.setBorder(BorderFactory.createTitledBorder(""));
			personPanel = new JPanel();
			personPanel.setLayout(new GridLayout(2,1));
			personPanel.setBorder(BorderFactory.createTitledBorder(""));
			personPanel.add(buttonPanel);
			personPanel.add(selectPanel);
			
			
			InfoPanel = new JPanel();
			InfoPanel.setBorder(BorderFactory.createTitledBorder(""));
			
			setLayout(new BorderLayout());
			add(personPanel, BorderLayout.WEST);
			add(InfoPanel, BorderLayout.CENTER);
		}
		
		public String getSearch() {
			String text=fareSearchTextField.getText();
			return text;
		}
		
		public String getChoose() {
			String choose;
			if(group.isSelected(style.getModel())) {
				choose="菜品风格";
			}else{
				choose="菜品名称";
			}
			return choose;
		}
		public void Search2(User user, int x) throws IOException {
			String choose=getChoose();
			String text=getSearch();

			displayDishInfo(user, x);
			
			if(choose.equals("菜品风格")) {
				SearchByStyle(text, user);
			}else if(choose.equals("菜品名称")){
				SearchByFarename(text, user);
			}
		}
	}
	
	public void PersonInfo(String account) {
		User user=usersDB.getUser(account);
		
		JButton Modify=new JButton("修改");
		Modify.addActionListener(event-> ModifyPersonInfo(account, user));
		
		InfoPanel.removeAll();
		InfoPanel.setVisible(false);
		InfoPanel.add(user.UserInfo(false));
		InfoPanel.add(Modify);
		InfoPanel.setVisible(true);
	}
	
	public void ModifyPersonInfo(String account, User user) {
		
		JButton Issue=new JButton("提交");
		
		InfoPanel.removeAll();
		InfoPanel.setVisible(false);
		InfoPanel.add(user.UserInfo(true));
		InfoPanel.add(Issue);
		InfoPanel.setVisible(true);
		
		Issue.addActionListener(event-> IssuePersonInfo(account,user));
	}
	
	public void IssuePersonInfo(String account, User user) {
		
		User newuser=new User();
		
		try {
			int index=0;
			do {
			newuser.setAccount(user.getAccount2());
			newuser.setName(user.getName2());
			newuser.setTelNum(user.getTelNum2());
			newuser.setAddress(user.getAddress2());
			newuser.setEmail(user.getEmail2());
			newuser.setQQNum(user.getQQNum2());
			newuser.setPassword(user.getPassword());
				
			index=0;
			for(int j=0;j<newuser.getEmail().length();j++) {
				if(newuser.getEmail().charAt(j)=='@')
					index++;
			}
			
			if((index!=1)||(!newuser.getEmail().contains(".com"))) {
				JOptionPane.showMessageDialog(
						null,
						"您输入的邮箱格式错误！");
					
				User newuser2=new User();	
				newuser2.setAccount(newuser.getAccount());
				newuser2.setName(newuser.getName());
				newuser2.setTelNum(newuser.getTelNum());
				newuser2.setAddress(newuser.getAddress());
				newuser2.setEmail(user.getEmail());
				newuser2.setQQNum(newuser.getQQNum());
				newuser2.setPassword(newuser.getPassword());
					
				ModifyPersonInfo(account, newuser2);
				break;
			}
			
			savePersonInfo(account, newuser);
			}while((index!=1)||(!newuser.getEmail().contains(".com")));
		}catch(IllegalArgumentException e) {
			stdErr.println(e);
		}
	}
	
	public void savePersonInfo(String account, User user) {
		try {
			File theFile = new File("UsersDB.txt");
			BufferedReader br = new BufferedReader(new FileReader("UsersDB.txt") );
			File tmpFile = new File("000.tmp");
			PrintWriter pt = new PrintWriter(new FileWriter("000.tmp",true));
			
			boolean flag=false;
			
			String line = br.readLine();
			while(line!=null) {
				if(line.startsWith(account)) {
					pt.println(user.toString());
					
					flag = true;
				}
				else {
					pt.println(line);
				}
				line = br.readLine();
			}
			br.close();
			pt.flush();
			pt.close();
			if(flag) {
				theFile.delete();
				tmpFile.renameTo( new File(theFile.getAbsolutePath()) );
			}else
				tmpFile.delete();
			
			JButton Modify=new JButton("修改");
			Modify.addActionListener(event-> ModifyPersonInfo(account, user));
			
			InfoPanel.removeAll();
			InfoPanel.setVisible(false);
			InfoPanel.add(user.UserInfo(false));
			InfoPanel.add(Modify);
			InfoPanel.setVisible(true);
		}catch(IllegalArgumentException | IOException e) {
			stdErr.println(e);
		}
	}
	
	public void myFare(String account) {
		int num=0;
		fareName=new String[dishesDB.getNumberOfDishs()];
		for(Iterator i = dishesDB.getDishsIterator(); i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(account.equals(dish.getPromulgatorAccount())) {
				fareName[num]=dish.getCode()+" "+dish.getName();
				num++;
			}
		}
		if(num==0)
			JOptionPane.showMessageDialog(
					null,
					"您没有上传任何菜品！");
		else {
			fareList=new JList(fareName);
			fareList.setFont(new Font("Serif",Font.BOLD,20));
			fareList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			fareList.setVisibleRowCount(10);
			fareList.setFixedCellWidth(150);

			fareList.addListSelectionListener(event->DishInfo(account,2));
			
			selectPanel.removeAll();
			selectPanel.setVisible(false);
			selectPanel.add(fareList);
			selectPanel.setVisible(true);
		}
		fareName=null;
	}
	
	public void DishInfo(String account, int x) {
		String code = (String) fareList.getSelectedValue();
		String[] info=code.split(" ");
		
		Dish dish = dishesDB.getDish(info[0]);
		JPanel panelx=new JPanel(new GridLayout(1,2));
		if(x==1) {
			JButton Issue=new JButton("提交");
			Issue.addActionListener(event-> IssueDishInfo(account,dish,1));
			panelx.add(Issue);
		}else if(x==2) {
			JButton Issue=new JButton("提交");
			Issue.addActionListener(event-> IssueDishInfo(account,dish,2));
			JButton Modify=new JButton("修改");
			Modify.addActionListener(event-> ModifyDishInfo(account, dish));
			panelx.add(Modify);
			panelx.add(Issue);
		}
		
		InfoPanel.removeAll();
		InfoPanel.setVisible(false);
		InfoPanel.add(dish.DishInfo(false, account));
		InfoPanel.add(panelx);
		InfoPanel.setVisible(true);
		
		
	}
	
	public void ModifyDishInfo(String account, Dish dish) {
		
		
		JButton Issue=new JButton("提交");
		
		
		InfoPanel.removeAll();
		InfoPanel.setVisible(false);
		InfoPanel.add(dish.DishInfo(true, account));
		InfoPanel.add(Issue);
		InfoPanel.setVisible(true);
		
		Issue.addActionListener(event-> IssueDishInfo(account,dish,2));
	}
	
	public void IssueDishInfo(String account,Dish dish,int x) {
		
		Dish newdish=new Dish();
		newdish.setCode(dish.getCode2());
		newdish.setName(dish.getName2());
		newdish.setStyle(dish.getStyle2());
		newdish.setPromulgatorAccount(dish.getPromulgatorAccount2());
		newdish.setMaterial(dish.getMaterial2());
		newdish.setMethod(dish.getMethod2());
		newdish.setComment(dish.getComment2());
		
		try {
			File theFile = new File("DishesDB.txt");
			BufferedReader br = new BufferedReader(new FileReader("DishesDB.txt") );
			File tmpFile = new File("000.tmp");
			PrintWriter pt = new PrintWriter(new FileWriter("000.tmp",true));
			
			boolean flag=false;
			
			String line = br.readLine();
			while(line!=null) {
				if(line.startsWith(dish.getCode())) {
					pt.println(newdish.toString());
					
					flag = true;
				}
				else {
					pt.println(line);
				}
				line = br.readLine();
			}
			br.close();
			pt.flush();
			pt.close();
			if(flag) {
				theFile.delete();
				tmpFile.renameTo( new File(theFile.getAbsolutePath()) );
			}else
				tmpFile.delete();
			
			JOptionPane.showMessageDialog(
					null,
					"修改成功！并已经录入本地数据库");
			
			JPanel panelx=new JPanel(new GridLayout(1,2));
			if(x==1) {
				JButton Issue=new JButton("提交");
				Issue.addActionListener(event-> IssueDishInfo(account, newdish,1));
				panelx.add(Issue);
			}else if(x==2) {
				JButton Issue=new JButton("提交");
				Issue.addActionListener(event-> IssueDishInfo(account, newdish,2));
				JButton Modify=new JButton("修改");
				Modify.addActionListener(event-> ModifyDishInfo(account, newdish));
				panelx.add(Modify);
				panelx.add(Issue);
			}
			InfoPanel.removeAll();
			InfoPanel.setVisible(false);
			InfoPanel.add(newdish.DishInfo(false, account));
			InfoPanel.add(panelx);
			InfoPanel.setVisible(true);
			
		}catch(IllegalArgumentException | IOException e) {
			stdErr.println(e);
		}
	}
	
	public void AddDish(String account) {
		if (dialog3 == null) 
			dialog3 = new AddDishFrame();
		if (dialog3.showDialog(BOFISGUI.this, "添加菜品"))
		{
			String code;
			String newname,newstyle,newmaterial,newmethod,newpictural,newvedio;
			int x=dishesDB.getNumberOfDishs()+1;
			code=x+"";
			newname=dialog3.getDishName();
			newstyle=dialog3.getStyle();
			newmaterial=dialog3.getMaterial();
			newmethod=dialog3.getMethod();
			newpictural=dialog3.getPicture();
			newvedio=dialog3.getVideo();
			
			Dish dish = new Dish();
			dish.setCode(code);
			dish.setName(newname);
			dish.setStyle(newstyle);
			dish.setPromulgatorAccount(account);
			dish.setMaterial(newmaterial);
			dish.setMethod(newmethod);
			dish.setPictureAddress(newpictural);
			dish.setVideoAddress(newvedio);
			dish.setComment("no comments");
			dishesDB.addDish(dish);
			try {
				PrintWriter pt = new PrintWriter(new FileWriter("DishesDB.txt",true) );
				pt.println(dish.toString());
				pt.close();
			}catch(IllegalArgumentException | IOException e) {
				stdErr.println(e);
			}
		}
	}
	
	public void ModifyPassword(String account) {
		int x=0;
		if (dialog4 == null) 
			dialog4 = new ModifyPasswordFrame();
		if (dialog4.showDialog(BOFISGUI.this, "修改密码"))
		{
			
			String oldpassword,newpassword1,newpassword2;
			for(Iterator i=usersDB.getUsersIterator();i.hasNext();) {
				User user=(User) i.next();
				if(account.equals(user.getAccount())) {
					oldpassword=dialog4.getOldPassword();
					newpassword1=dialog4.getNewPassword1();
					newpassword2=dialog4.getNewPassword2();
					if(!oldpassword.equals(user.getPassword())) {
						JOptionPane.showMessageDialog(
								null,
								"原密码输入不正确！");
						x=1;
						break;
					}
					if(!newpassword1.equals("")) {
						JOptionPane.showMessageDialog(
								null,
								"密码不能为空！");
						x=1;
						break;
					}
					if(!newpassword1.equals(newpassword2)) {
						JOptionPane.showMessageDialog(
								null,
								"两次密码输入不一样！");
						x=1;
						break;
					}
					user.setPassword(newpassword2);
					try {	
						File theFile = new File("UsersDB.txt");
						BufferedReader br = new BufferedReader(new FileReader("UsersDB.txt") );
						File tmpFile = new File("000.tmp");
						PrintWriter pt = new PrintWriter(new FileWriter("000.tmp",true));
						
						boolean flag=false;
						
						String line = br.readLine();
						while(line!=null) {
							if(line.contains(account)) {
								pt.println(user.getAccount()+" "+user.getName()+" "
							+user.getTelNum()+" "+user.getAddress()+" "+user.getEmail()+" "
										+user.getQQNum()+" "+newpassword2);
								
								flag = true;
							}
							else {
								pt.println(line);
							}
							line = br.readLine();
						}
						br.close();
						pt.flush();
						pt.close();
						if(flag) {
							theFile.delete();
							tmpFile.renameTo( new File(theFile.getAbsolutePath()) );
						}else
							tmpFile.delete();
						break;
					}catch(IllegalArgumentException | IOException e) {
						stdErr.println(e);
					}
				}
			}
			if(x==0)
				JOptionPane.showMessageDialog(
						null,
						"密码修改完成！");
		}
	}
	
	public void Exit() {
		frame1.dispose();
		
		frame1 = new JFrame("菜谱信息管理系统");

		frame1.setContentPane(new BOFISGUI());
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame1.setResizable(true);
		frame1.setVisible(true);
	}

}


