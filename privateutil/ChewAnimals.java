package privateutil;

import animals.Animal;
import graphics.ZooPanel;
import mobility.Point;


/**
* Describes Animals That Has Chewing Behavior.
* 
* @author Gitit Dadon:212280911, Michal Tenenboim:326945763, Campus-Ashdod
* @see Elephant,Giraffe,turtle
*
*/
public abstract class ChewAnimals extends Animal {
	
	/**
	 * Default Constructor
	 */
	public ChewAnimals() {
		
	}
	/**
	 * Parameterized Constructor
	 * @param name-animal's name
	 * @param location-animal's location
	 */
	public ChewAnimals(String name,Point location) {
		
		super(name,location);
	}
	
	public ChewAnimals(int size,String color,ZooPanel panel, int horSpeed, int verSpeed)
	{
		
		super(size,color,panel,horSpeed,verSpeed);
	}
	
	/**
	 * Animal's Behavior
	 * @param behavior-String of Animal's Behavior
	 */
	public void chew(String behavior) {
		
		System.out.println(behavior);
	}
	
}
