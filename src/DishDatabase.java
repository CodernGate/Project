import java.util.ArrayList;
import java.util.Iterator;

public class DishDatabase {
	private ArrayList<Dish> dishs;
	static private DishDatabase singletonInstance = null;

	
	private DishDatabase() {
		dishs=new ArrayList<Dish>();
	}
	
	static public DishDatabase getSingeltonInstance() {
		if (singletonInstance == null) 
			singletonInstance = new DishDatabase();
		return singletonInstance;
	}
	
	public void addDish(Dish dish) {
		dishs.add(dish);
	}
	
	public Iterator<Dish> getDishsIterator(){
		return dishs.iterator();
	}
	
	public Dish getDish(String code) {
		for(Iterator<Dish> i=iterator();i.hasNext();) {
			Dish dish=i.next();
			if(dish.getCode().equals(code)) {
				return dish;
			}
		}
		return null;
	}
	
	public int getNumberOfDishs() {
		return dishs.size();
	}
	
	public Iterator<Dish> iterator(){
		return this.getDishsIterator();
	}
}
