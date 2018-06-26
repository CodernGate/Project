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
		
		System.out.println("\n��ӭ���� ������Ϣ����ϵͳ!");
		System.out.println("Welcome to Bill Of Fare Information System��BOFIS) !");
		System.out.println("������ȡ��Q�������ƥ�ء��褦������\n");
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
					  "[0] �˳�\n"
					+ "[1] ��¼\n"
					+ "[2] ע��\n"
					+ "[3] ����Ʒ�����Ҳ�Ʒ\n"
					+ "[4] ����Ʒ���Ʋ��Ҳ�Ʒ\n"
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
			System.out.print("�������˺ţ�");
			account=stdIn.readLine();
			System.out.print("���������룺");
			password=stdIn.readLine();
		
			for(Iterator i = usersDB.getUsersIterator(); i.hasNext();) {
				user=(User) i.next();
				if(account.equals(user.getAccount())&&password.equals(user.getPassword())) {
					System.out.println("��½�ɹ���\n  ��ӭ�� "+user.getName()+"\n   : )");
					UsersRun(user.getAccount(),user.getName());
					x=0;
				}
			}
			if(x!=0)
				System.out.println("�˺Ż��������");
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
			System.out.print("�������˺ţ�");
			account=stdIn.readLine();
			System.out.print("�������û�����");
			username=stdIn.readLine();
			do {
				System.out.print("���������룺");
				password1=stdIn.readLine();
				System.out.println("���ٴ��������룺");
				password2=stdIn.readLine();
				if(!password1.equals(password2)) {
					System.out.println("�����������벻һ����");
				}
			}while(!password2.equals(password1));
			int y=usersDB.getNumberOfUsers();
			if(y!=0) {
				for(Iterator i = usersDB.getUsersIterator(); i.hasNext();) {
					User user=(User) i.next();
					if(account.equals(user.getAccount())) {
						System.out.println("���˺��Ѵ��ڣ�");
						x=0;
					}
					if(username.equals(user.getName())) {
						System.out.println("���û����Ѵ��ڣ�");
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
		
		System.out.println("ע��ɹ�,���Ѿ�¼�뱾�����ݿ⣡\n�뾡�����Ƹ�����Ϣ");
		
		UsersRun(user.getAccount(),user.getName());
	}
	
	public void SearchByStyle() throws IOException {
		String style;
		System.out.print("�������Ʒ���");
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
			System.out.println("û���ҵ�����Ҫ�Ĳ�Ʒ��");
		else
			SearchByCode();
	}
	
	public void SearchByFarename() throws IOException {
		String farename;
		System.out.print("�������Ʒ���ƣ�");
		farename=stdIn.readLine();
		
		int num=0;
		for(Iterator i=dishesDB.getDishsIterator();i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(farename.equals(dish.getName()))
				num++;
		}
		if(num==0)
			System.out.println("û���ҵ�����Ҫ�Ĳ�Ʒ��");
		else if(num==1) {
			for(Iterator i=dishesDB.getDishsIterator();i.hasNext();) {
				Dish dish=(Dish) i.next();
				if(farename.equals(dish.getName())) {
					System.out.println("��Ʒ��ţ�"+dish.getCode()+"\n"
							+"��Ʒ���ƣ�"+dish.getName()+"\n"
							+"��Ʒ���"+dish.getStyle()+"\n"
							+"�ϴ��ߣ�"+dish.getPromulgatorAccount()+"\n"
							+"ԭ���ϣ�"+dish.getMaterial()+"\n"
							+"����������"+dish.getMethod()+"\n"
							+"��ƷͼƬ��"+dish.getPictureAddress()+"\n"
							+"������Ƶ��"+dish.getVideoAddress());
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
		System.out.print("�������Ʒ��ţ�");
		code=stdIn.readLine();
		
		for(Iterator i=dishesDB.getDishsIterator();i.hasNext();) {
			Dish dish=(Dish) i.next();
			if(code.equals(dish.getCode())) {
				System.out.println("\n��Ʒ��ţ�"+dish.getCode()+"\n"
						+"��Ʒ���ƣ�"+dish.getName()+"\n"
						+"��Ʒ���"+dish.getStyle()+"\n"
						+"�ϴ����˺ţ�"+dish.getPromulgatorAccount()+"\n"
						+"ԭ���ϣ�"+dish.getMaterial()+"\n"
						+"����������"+dish.getMethod()+"\n"
						+"��ƷͼƬ��"+dish.getPictureAddress()+"\n"
						+"������Ƶ��"+dish.getVideoAddress());
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
					  "[0] �˳���¼\n"
					+ "[1] �鿴������Ϣ\n"
					+ "[2] �޸ĸ�����Ϣ\n"
					+ "[3] �ҵĲ�Ʒ\n"
					+ "[4] ��Ӳ�Ʒ\n"
					+ "[5] �޸Ĳ�Ʒ��Ϣ\n"
					+ "[6] �޸ĵ�¼����\n"
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
				System.out.println("�˺ţ�"+user.getAccount()+"\n"
						+"�û�����"+user.getName()+"\n"
						+"��ϵ�绰��"+user.getTelNum()+"\n"
						+"��ϵ��ַ��"+user.getAddress()+"\n"
						+"���䣺"+user.getEmail()+"\n"
						+"QQ���룺"+user.getQQNum());
		}
	}
	
	public void ModifyPersonalInfo(String account) throws IOException
	{
		PersonalInfo(account);
		int index=0;//ͳ�������ʽ�С�@��������������Ϊ1����Ҫ�����������䡣
		for(Iterator i=usersDB.getUsersIterator();i.hasNext();) {
			User someUser=(User) i.next();
			if(account.equals(someUser.getAccount())) {
				System.out.print("�������µ��û�����");
				someUser.setName(stdIn.readLine());
				System.out.print("�������µ���ϵ�绰��");
				someUser.setTelNum(stdIn.readLine());
				System.out.print("�������µ���ϵ��ַ��");
				someUser.setAddress(stdIn.readLine());
				System.out.print("�������µ����䣺");
//				someUser.setEmail(stdIn.readLine());
				String email=stdIn.readLine();
				for(int j=0;j<email.length();j++) {
					if(email.charAt(j)=='@')
						index++;
				}
				while((index!=1)||(!email.contains(".com"))) {
					index=0;
					System.out.println("������������ʽ����");
					System.out.print("�������µ����䣺");
					email=stdIn.readLine();
					for(int j=0;j<email.length();j++) {
						if(email.charAt(j)=='@')
							index++;
					}
					if(!email.contains(".com")) {
						index=0;
						System.out.println("������������ʽ����");
						System.out.print("�������µ����䣺");
						email=stdIn.readLine();
						for(int j=0;j<email.length();j++) {
							if(email.charAt(j)=='@')
								index++;
						}
					}
				}
				
				someUser.setEmail(email);
				
				System.out.print("�������µ�QQ���룺");
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
		System.out.println("�û����޸���ɣ�\n");
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
			System.out.println("��û���ϴ��κβ�Ʒ��");
		else {
			System.out.println("\n����鿴�ĸ����Ĳ�Ʒ��");
			SearchByCode();	
		}	
	}
	
	public void AddFare(String account) throws IOException{
		String code;
		String newname,newstyle,newmaterial,newmethod,newpictural,newvedio;
		int x=dishesDB.getNumberOfDishs()+1;
		code=x+"";
		System.out.print("��Ʒ���ƣ�");
		newname=stdIn.readLine();
		System.out.print("��Ʒ���");
		newstyle=stdIn.readLine();
		System.out.print("��Ʒԭ���ϣ�");
		newmaterial=stdIn.readLine();
		System.out.print("��Ʒ����������");
		newmethod=stdIn.readLine();
		System.out.print("��ƷͼƬ��");
		newpictural=stdIn.readLine();
		System.out.print("��Ʒ������Ƶ��");
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
		
		System.out.println("\n���Ĳ�Ʒ��");
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
			System.out.println("��û���ϴ��κβ�Ʒ��");
		else {
			System.out.print("\n��������Ҫ�޸�����Ϣ�Ĳ�Ʒ��ţ�");
			code=stdIn.readLine();
			for(Iterator i=userDish.iterator();i.hasNext();) {
				dishToBeModified=(Dish) i.next();
				if(code.equals(dishToBeModified.getCode())) {
					sign = 1;
					System.out.println("\n��ʾԭ��Ϣ...");
					System.out.println("��Ʒ��ţ�"+dishToBeModified.getCode()+"\n"
							+"��Ʒ���ƣ�"+dishToBeModified.getName()+"\n"
							+"��Ʒ���"+dishToBeModified.getStyle()+"\n"
							+"�ϴ��ߣ�"+dishToBeModified.getPromulgatorAccount()+"\n"
							+"��Ʒԭ���ϣ�"+dishToBeModified.getMaterial()+"\n"
							+"��Ʒ����������"+dishToBeModified.getMethod()+"\n"
							+"��ƷͼƬ��"+dishToBeModified.getPictureAddress()+"\n"
							+"��Ʒ������Ƶ��"+dishToBeModified.getVideoAddress());
					System.out.println("\n�������µ���Ϣ");
					System.out.print("��Ʒ���ƣ�");
					dishToBeModified.setName(stdIn.readLine());
					System.out.print("��Ʒ���");
					dishToBeModified.setStyle(stdIn.readLine());
					System.out.print("��Ʒԭ���ϣ�");
					dishToBeModified.setMaterial(stdIn.readLine());
					System.out.print("��Ʒ����������");
					dishToBeModified.setMethod(stdIn.readLine());
					System.out.print("��ƷͼƬ��");
					dishToBeModified.setPictureAddress(stdIn.readLine());
					System.out.print("��Ʒ������Ƶ��");
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
					
					System.out.println("\n�޸ĳɹ������Ѿ�¼�뱾�����ݿ�");
				}
			}
			if(sign == 0)
				System.out.println("\n��û���ϴ��������ŵĲ�Ʒ��");
		}
	}

	public void ModifyPassword(String account) throws IOException
	{
		String newpassword1,newpassword2;
		for(Iterator i=usersDB.getUsersIterator();i.hasNext();) {
			User user=(User) i.next();
			if(account.equals(user.getAccount())) {
				do {
					System.out.println("�����������룺");
					newpassword1=stdIn.readLine();
					System.out.println("���ٴ����������룺");
					newpassword2=stdIn.readLine();
					if(!newpassword1.equals(newpassword2)) {
						System.out.println("�����������벻һ����");
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

		System.out.println("�����޸���ɣ�");
	}
}
