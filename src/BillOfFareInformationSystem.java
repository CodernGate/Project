import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BillOfFareInformationSystem {

	private static BufferedReader  stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
	private static PrintWriter  stdOut =
			new  PrintWriter(System.out, true);
	private static PrintWriter  stdErr =
			new  PrintWriter(System.err, true);
	
	private UserDatabase usersDB;
	private DishDatabase dishesDB;
	

	public static void  main(String[]  args) throws IOException  {
		
		System.out.println("``Loading data from the local database...");
		System.out.println("-------------------------------------------");
		BillOfFareInformationSystem  application = new  BillOfFareInformationSystem();
		System.out.println("DONE");
		
		System.out.println("\n欢迎来到 菜谱信息管理系统!");
		System.out.println("Welcome to Bill Of Fare Information System（BOFIS) !");
		System.out.println("献立表取り扱いシステムへ　ようこそ！\n");
		application.run();
	}
	
	private  BillOfFareInformationSystem(){
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
	
	private void run() throws IOException  {

		int  choice = getChoice();

		while (choice != 0)  {

			if (choice == 1)  {
				Enter();
			} else if (choice == 2)  {
				Login();
			} else if (choice == 3)  {
				SearchByStyle();
			} else if (choice == 4)  {
				SearchByFarename();
			} 

			choice = getChoice();
		}
	}
	
	private int  getChoice() throws IOException  {

		int  input;

		do  {
			try  {
				stdErr.println();
				stdErr.print(
					  "[0] 退出\n"
					+ "[1] 登录\n"
					+ "[2] 注册\n"
					+ "[3] 按菜品风格查找菜品\n"
					+ "[4] 按菜品名称查找菜品\n"
					+ "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && 4 >= input)  {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException  nfe)  {
				stdErr.println(nfe);
			}
		}  while (true);

		return  input;
	}
	
	public void Enter() throws IOException{
		int x;
		String account;
		String password;
		User user=null;
		do {
			x=1;
			System.out.print("请输入账号：");
			account=stdIn.readLine();
			System.out.print("请输入密码：");
			password=stdIn.readLine();
		
			for(Iterator i = usersDB.getUsersIterator(); i.hasNext();) {
				user=(User) i.next();
				if(account.equals(user.getAccount())&&password.equals(user.getPassword())) {
					System.out.println("登陆成功！\n  欢迎您 "+user.getName()+"\n   : )");
					UsersRun(user.getAccount(),user.getName());
					x=0;
				}
			}
			if(x!=0)
				System.out.println("账号或密码错误！");
		}while(x!=0);

	}
	
	public void Login() throws IOException{
		int x;
		String account;
		String username;
		String password1;
		String password2;
		
		do {
			x=1;
			System.out.print("请输入账号：");
			account=stdIn.readLine();
			System.out.print("请输入用户名：");
			username=stdIn.readLine();
			do {
				System.out.print("请输入密码：");
				password1=stdIn.readLine();
				System.out.println("请再次输入密码：");
				password2=stdIn.readLine();
				if(!password1.equals(password2)) {
					System.out.println("两次密码输入不一样！");
				}
			}while(!password2.equals(password1));
			int y=usersDB.getNumberOfUsers();
			if(y!=0) {
				for(Iterator i = usersDB.getUsersIterator(); i.hasNext();) {
					User user=(User) i.next();
					if(account.equals(user.getAccount())) {
						System.out.println("该账号已存在！");
						x=0;
					}
					if(username.equals(user.getName())) {
						System.out.println("该用户名已存在！");
						x=0;
					}
				}
			}
		}while(x==0);
		
		User user=new User();
		user.setAccount(account);
		user.setName(username);
		user.setTelNum("NoTelNum");
		user.setAddress("NoAddress");
		user.setEmail("NoEmail");
		user.setQQNum("NoQQNum");
		user.setPassword(password2);
		usersDB.addUser(user);
		
		PrintWriter pt = new PrintWriter(new FileWriter("UsersDB.txt",true));
		pt.println(user.toString());
		pt.close();
		
		System.out.println("注册成功,并已经录入本地数据库！\n请尽快完善个人信息");
		
		UsersRun(user.getAccount(),user.getName());
	}
	
	public void SearchByStyle() throws IOException {
		String style;
		System.out.print("请输入菜品风格：");
		style=stdIn.readLine();
		
		int num=0;
		for(Iterator i = dishesDB.getDishsIterator(); i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(style.equals(dish.getStyle())) {
				System.out.println(dish.getCode()+" "+dish.getName());
				num++;
			}
		}
		if(num==0)
			System.out.println("没有找到您需要的菜品！");
		else
			SearchByCode();
	}
	
	public void SearchByFarename() throws IOException {
		String farename;
		System.out.print("请输入菜品名称：");
		farename=stdIn.readLine();
		
		int num=0;
		for(Iterator i=dishesDB.getDishsIterator();i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(farename.equals(dish.getName()))
				num++;
		}
		if(num==0)
			System.out.println("没有找到您需要的菜品！");
		else if(num==1) {
			for(Iterator i=dishesDB.getDishsIterator();i.hasNext();) {
				Dish dish=(Dish) i.next();
				if(farename.equals(dish.getName())) {
					System.out.println("菜品编号："+dish.getCode()+"\n"
							+"菜品名称："+dish.getName()+"\n"
							+"菜品风格："+dish.getStyle()+"\n"
							+"上传者："+dish.getPromulgatorAccount()+"\n"
							+"原材料："+dish.getMaterial()+"\n"
							+"制作方法："+dish.getMethod()+"\n"
							+"菜品图片："+dish.getPictureAddress()+"\n"
							+"制作视频："+dish.getVideoAddress());
				}
			}
		}else {
			for(Iterator i=dishesDB.getDishsIterator();i.hasNext();) {
				Dish dish=(Dish) i.next();
				if(farename.equals(dish.getName()))
					System.out.println(dish.getCode()+" "+dish.getName());
			}
			SearchByCode();
		}
	}
	
	public String SearchByCode() throws IOException {
		String code;
		System.out.print("请输入菜品编号：");
		code=stdIn.readLine();
		
		for(Iterator i=dishesDB.getDishsIterator();i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(code.equals(dish.getCode())) {
				System.out.println("\n菜品编号："+dish.getCode()+"\n"
						+"菜品名称："+dish.getName()+"\n"
						+"菜品风格："+dish.getStyle()+"\n"
						+"上传者账号："+dish.getPromulgatorAccount()+"\n"
						+"原材料："+dish.getMaterial()+"\n"
						+"制作方法："+dish.getMethod()+"\n"
						+"菜品图片："+dish.getPictureAddress()+"\n"
						+"制作视频："+dish.getVideoAddress());
			}
		}
		
		return code;
	}
	
	public void UsersRun(String account,String name) throws IOException{
		
		int  choice = UsergetChoice(account,name);
		

		while (choice != 0)  {

			if (choice == 1)  {
				PersonalInfo(account);
			} else if (choice == 2)  {
				ModifyPersonalInfo(account);
			} else if (choice == 3)  {
				MyFare(account);
			} else if (choice == 4)  {
				AddFare(account);
			} else if (choice == 5)  {
				ModifyFareInfo(account);
			} else if (choice == 6) {
				ModifyPassword(account);
			}

			choice = UsergetChoice(account,name);
		}
	}
	
	private int UsergetChoice(String account,String name) throws IOException  {

		int  input;

		do  {
			try  {
				System.out.println("");

				stdErr.println();
				stdErr.print(
					  "[0] 退出登录\n"
					+ "[1] 查看个人信息\n"
					+ "[2] 修改个人信息\n"
					+ "[3] 我的菜品\n"
					+ "[4] 添加菜品\n"
					+ "[5] 修改菜品信息\n"
					+ "[6] 修改登录密码\n"
					+ "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && 6 >= input)  {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException  nfe)  {
				stdErr.println(nfe);
			}
		}  while (true);

		return  input;
	}
	
	public void PersonalInfo(String account) {
		for(Iterator i=usersDB.getUsersIterator();i.hasNext();) {
			User user=(User) i.next();
			if(account.equals(user.getAccount()))
				System.out.println("账号："+user.getAccount()+"\n"
						+"用户名："+user.getName()+"\n"
						+"联系电话："+user.getTelNum()+"\n"
						+"联系地址："+user.getAddress()+"\n"
						+"邮箱："+user.getEmail()+"\n"
						+"QQ号码："+user.getQQNum());
		}
	}
	
	public void ModifyPersonalInfo(String account) throws IOException
	{
		PersonalInfo(account);
		int index=0;//统计邮箱格式中“@”的数量，若不为1则需要重新输入邮箱。
		for(Iterator i=usersDB.getUsersIterator();i.hasNext();) {
			User someUser=(User) i.next();
			if(account.equals(someUser.getAccount())) {
				System.out.print("请输入新的用户名：");
				someUser.setName(stdIn.readLine());
				System.out.print("请输入新的联系电话：");
				someUser.setTelNum(stdIn.readLine());
				System.out.print("请输入新的联系地址：");
				someUser.setAddress(stdIn.readLine());
				System.out.print("请输入新的邮箱：");
//				someUser.setEmail(stdIn.readLine());
				String email=stdIn.readLine();
				for(int j=0;j<email.length();j++) {
					if(email.charAt(j)=='@')
						index++;
				}
				while((index!=1)||(!email.contains(".com"))) {
					index=0;
					System.out.println("您输入的邮箱格式错误！");
					System.out.print("请输入新的邮箱：");
					email=stdIn.readLine();
					for(int j=0;j<email.length();j++) {
						if(email.charAt(j)=='@')
							index++;
					}
					if(!email.contains(".com")) {
						index=0;
						System.out.println("您输入的邮箱格式错误！");
						System.out.print("请输入新的邮箱：");
						email=stdIn.readLine();
						for(int j=0;j<email.length();j++) {
							if(email.charAt(j)=='@')
								index++;
						}
					}
				}
				
				someUser.setEmail(email);
				
				System.out.print("请输入新的QQ号码：");
				someUser.setQQNum(stdIn.readLine());

				File theFile = new File("UsersDB.txt");
				BufferedReader br = new BufferedReader(new FileReader("UsersDB.txt") );
				File tmpFile = new File("000.tmp");
				PrintWriter pt = new PrintWriter(new FileWriter("000.tmp",true));
				
				boolean flag=false;
				
				String line = br.readLine();
				while(line!=null) {
					if(line.startsWith(account)) {
						pt.println(someUser.toString());
						
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

			}
		}
		System.out.println("用户名修改完成！\n");
	}
	
	public void MyFare(String account) throws IOException {
		int num=0;
		for(Iterator i = dishesDB.getDishsIterator(); i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(account.equals(dish.getPromulgatorAccount())) {
				System.out.println(dish.getCode()+" "+dish.getName());
				num++;
			}
		}
		if(num==0)
			System.out.println("您没有上传任何菜品！");
		else {
			System.out.println("\n您想查看哪个您的菜品？");
			SearchByCode();	
		}	
	}
	
	public void AddFare(String account) throws IOException{
		String code;
		String newname,newstyle,newmaterial,newmethod,newpictural,newvedio;
		int x=dishesDB.getNumberOfDishs()+1;
		code=x+"";
		System.out.print("菜品名称：");
		newname=stdIn.readLine();
		System.out.print("菜品风格：");
		newstyle=stdIn.readLine();
		System.out.print("菜品原材料：");
		newmaterial=stdIn.readLine();
		System.out.print("菜品制作方法：");
		newmethod=stdIn.readLine();
		System.out.print("菜品图片：");
		newpictural=stdIn.readLine();
		System.out.print("菜品制作视频：");
		newvedio=stdIn.readLine();
		
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
		
		PrintWriter pt = new PrintWriter(new FileWriter("DishesDB.txt",true) );
		pt.println(dish.toString());
		pt.close();
	}
	
	public void ModifyFareInfo(String account) throws IOException
	{
		String code;

		ArrayList<Dish> userDish = new ArrayList<Dish>();
		
		System.out.println("\n您的菜品：");
		for(Iterator i = dishesDB.getDishsIterator(); i.hasNext();) {
			Dish oneDish=(Dish) i.next();
			if(account.equals(oneDish.getPromulgatorAccount())) {
				System.out.println(oneDish.getCode()+" "+oneDish.getName());
				userDish.add(oneDish);
			}
		}
		
		Dish dishToBeModified = null;
		int sign = 0;
		if(userDish.size()==0)
			System.out.println("您没有上传任何菜品！");
		else {
			System.out.print("\n请输入想要修改其信息的菜品编号：");
			code=stdIn.readLine();
			for(Iterator i=userDish.iterator();i.hasNext();) {
				dishToBeModified=(Dish) i.next();
				if(code.equals(dishToBeModified.getCode())) {
					sign = 1;
					System.out.println("\n显示原信息...");
					System.out.println("菜品编号："+dishToBeModified.getCode()+"\n"
							+"菜品名称："+dishToBeModified.getName()+"\n"
							+"菜品风格："+dishToBeModified.getStyle()+"\n"
							+"上传者："+dishToBeModified.getPromulgatorAccount()+"\n"
							+"菜品原材料："+dishToBeModified.getMaterial()+"\n"
							+"菜品制作方法："+dishToBeModified.getMethod()+"\n"
							+"菜品图片："+dishToBeModified.getPictureAddress()+"\n"
							+"菜品制作视频："+dishToBeModified.getVideoAddress());
					System.out.println("\n请输入新的信息");
					System.out.print("菜品名称：");
					dishToBeModified.setName(stdIn.readLine());
					System.out.print("菜品风格：");
					dishToBeModified.setStyle(stdIn.readLine());
					System.out.print("菜品原材料：");
					dishToBeModified.setMaterial(stdIn.readLine());
					System.out.print("菜品制作方法：");
					dishToBeModified.setMethod(stdIn.readLine());
					System.out.print("菜品图片：");
					dishToBeModified.setPictureAddress(stdIn.readLine());
					System.out.print("菜品制作视频：");
					dishToBeModified.setVideoAddress(stdIn.readLine());
					
					File theFile = new File("DishesDB.txt");
					BufferedReader br = new BufferedReader(new FileReader("DishesDB.txt") );
					File tmpFile = new File("000.tmp");
					PrintWriter pt = new PrintWriter(new FileWriter("000.tmp",true));
					
					boolean flag=false;
					
					String line = br.readLine();
					while(line!=null) {
						if(line.startsWith(code)) {
							pt.println(dishToBeModified.toString());
							
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
					
					System.out.println("\n修改成功！并已经录入本地数据库");
				}
			}
			if(sign == 0)
				System.out.println("\n您没有上传过这个编号的菜品！");
		}
	}

	public void ModifyPassword(String account) throws IOException
	{
		String newpassword1,newpassword2;
		for(Iterator i=usersDB.getUsersIterator();i.hasNext();) {
			User user=(User) i.next();
			if(account.equals(user.getAccount())) {
				do {
					System.out.println("请输入新密码：");
					newpassword1=stdIn.readLine();
					System.out.println("请再次输入新密码：");
					newpassword2=stdIn.readLine();
					if(!newpassword1.equals(newpassword2)) {
						System.out.println("两次密码输入不一样！");
					}
				}while(!newpassword2.equals(newpassword1));
				user.setPassword(newpassword2);
				
				File theFile = new File("UsersDB.txt");
				BufferedReader br = new BufferedReader(new FileReader("UsersDB.txt") );
				File tmpFile = new File("000.tmp");
				PrintWriter pt = new PrintWriter(new FileWriter("000.tmp",true));
				
				boolean flag=false;
				
				String line = br.readLine();
				while(line!=null) {
					if(line.contains(account)) {
						pt.println(user.getAccount()+" "+user.getName()+" "+newpassword2);
						
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
			}
		}

		System.out.println("密码修改完成！");
	}
}
