package animals;
import java.util.Arrays;
import java.util.List;

import diet.Carnivore;
import diet.IDiet;
import diet.Omnivore;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;
import privateutil.RoarAnimals;

/**
 * Bear Class Represents Bear behavior.
 * 
 * @author Gitit Dadon-212280911, Michal Tenenboim-326945763 Ashdod Campus
 *@see RoarAnimals
 */
public class Bear extends RoarAnimals  {
	
	/**
	 * 
	 */
	private double weight =308.2;
	
	/**
	 * 
	 */
	private  Point startLocation_x=new Point(100,5);
	
	/**
	 * 
	 */
	private String furColor="GRAY";
	
	
	
	/**
	 * a constructor which gets 2 parameters: name and fur color and the other parameters that were not given will be initialized 
	 * after that a new object will be created from all the parameters.
	 * 
	 * @param name the name of the bear
	 * @param furColor the fur color of the bear
	 */
	public Bear(String name,String furColor) {
		
	
			
			this.setName(name);
			
			
			this.setFurColor(furColor);
			
			this.setWeight(this.weight);
			
			this.setLocation(startLocation_x);
			
			this.setDiet(new Omnivore());
			
			
		}
	
			
	/**
	 * a constructor which gets 3 parameters: name ,fur color and location and the other parameters that were not given will be initialized 
	 * after that a new object will be created from all the parameters.
	 * 
	 * @param name the name of the bear
	 * @param location the location of the bear
	 * @param furColor the fur color of the bear
	 */
	public Bear(String name,Point location,String furColor) {
		
		super(name,location);
		
		this.setWeight(this.weight);
		
		this.setFurColor(furColor);
		
		this.setDiet(new Omnivore());
		
		
			
	}
		
	
	/**
	 * a constructor which gets 1 parameter: name and the other parameters that were not given will be initialized 
	 * and a new object will be created from all the parameters.
	 * 
	 * @param name the name of the bear
	 */
	public Bear(String name) {
		
		
		this.setName(name);
		
		
		this.setFurColor(this.furColor);
		
		this.setWeight(this.weight);
		
		this.setLocation(startLocation_x);
		
		this.setDiet(new Omnivore());
		
		
	}
	public Bear(int size,String color,ZooPanel panel,int horSpeed, int verSpeed)
	{
		
		super(size,color,panel,horSpeed,verSpeed);
		this.setLocation(startLocation_x);
		
		this.setWeight(this.weight);
		
		this.setDiet(new Omnivore());

	}
	
	/**
	 *  updates Bear's fur color.
	 * @param furColor -String, Bear's fur color.
	 * @return true if fur color is legal,else returns false.
	 */
	public boolean setFurColor(String furColor) {
		
		
		if(Arrays.asList("BLACK", "WHITE", "GRAY").contains(furColor)) {
			
			this.furColor=furColor;
			
			
			return true;
		}
		else
		
		return false;
	}
	/**
	 *returns the fur color of the Bear.
	 * 
	 * @return the fur color of the object we are working with.
	 */
	public String getFurColor() {
		
		
		
		
	    return this.furColor;
	}
	
	
	
	
	
	
	
	@Override
	/**
	 *  prints how the bear make sound when it roars.
	 * 
	 */
	public void makeSound() {
		
		this.roar("Stands on its hind legs, roars and scratches its belly");
		
	}
    /**
     Checks If Bear Can Eat The Food Type & Updates Bear's Weight After Eating
     * @param var - IEdible represents a food type
     * @return true if its meat or vegetables  else returns false.
     */
	public boolean eat(IEdible var) {
		if(this.getDiet().canEat(var.getFoodtype()))
		{
			if(var.getFoodtype()==EFoodType.MEAT)
				this.setWeight(this.getDiet().eat(this, var)+this.getWeight());
				this.makeSound();
				return true;
		}
		else if(var.getFoodtype()==EFoodType.VEGTABLE)
		{
			this.setWeight(this.getDiet().eat(this, var)+this.getWeight());
			this.makeSound();
			return true;
		}
		return false;
	}


	

	
	
}