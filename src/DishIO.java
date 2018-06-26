import java.util.*;
import java.io.*;

public class DishIO {
	
	public static DishDatabase readDish(String filename)throws IOException,
	FileNotFoundException,NoSuchElementException,NumberFormatException{
		DishDatabase dishes=null;
		Dish dish=new Dish();
		System.out.println("");
		BufferedReader text=new BufferedReader(new FileReader(filename));
//		BufferedReader text=new BufferedReader(new InputStreamReader(System.in));
		
		String line=text.readLine();
		StringTokenizer token=new StringTokenizer(line," ");
		
		while(line!=null) {
			while(token.hasMoreElements()) {
				dish.setCode(token.nextToken());
				dish.setName(token.nextToken());
				dish.setStyle(token.nextToken());
				dish.setPromulgatorAccount(token.nextToken());
				dish.setMaterial(token.nextToken());
				dish.setMethod(token.nextToken());
				dish.setPictureAddress(token.nextToken());
				dish.setVideoAddress(token.nextToken());
			}
			dishes.addDish(dish);
			line=text.readLine();
		}
		return dishes;
		
	}
	
	public static void writeDish(String filename,Vector vector) {
		
	}

}
