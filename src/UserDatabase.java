import java.util.*;

public class UserDatabase implements Iterable<User> {
	
	private ArrayList<User> users;
	static private UserDatabase singletonInstance = null;
	
	private UserDatabase() {
		users=new ArrayList<User>();
	}
	
	static public UserDatabase getSingeltonInstance() {
		if (singletonInstance == null) 
			singletonInstance = new UserDatabase();
		return singletonInstance;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public Iterator<User> getUsersIterator(){
		return users.iterator();
	}
	
	public User getUser(String account) {
		for(Iterator<User> i=iterator();i.hasNext();) {
			User user=i.next();
			if(user.getAccount().equals(account)) {
				return user;
			}
		}
		return null;
	}
	
	public int getNumberOfUsers() {
		return users.size();
	}
	
	public Iterator<User> iterator(){
		return this.getUsersIterator();
	}
}
