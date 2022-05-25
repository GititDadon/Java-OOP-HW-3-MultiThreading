package privateutil;

import animals.Animal;
import graphics.ZooPanel;
import mobility.Point;



/**
* Describes Animals That Has Roar Behavior.
* 
* @author Gitit Dadon:212280911, Michal Tenenboim:326945763, Campus-Ashdod
* @see Elephant,Giraffe,turtle
*
*/
public abstract class RoarAnimals extends Animal {
	
	/**
	 * Default Constructor
	 */
	public RoarAnimals(){
		
	}
	
	

	/**
	 * Parameterized Constructor
	 * @param name-animal's name
	 * @param location-animal's location
	 */
	public RoarAnimals(String name,Point location){
		
		super(name,location);
	}
	
	public RoarAnimals(int size, String color, ZooPanel panel, int horSpeed, int verSpeed) {
		super(size,color,panel,horSpeed,verSpeed);
	}

	/**
	 * Animal's Behavior
	 * @param behavior-String of Animal's Behavior
	 */
	public void roar(String behavior) {
		System.out.println(behavior);
	}
}
