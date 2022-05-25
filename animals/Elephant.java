package animals;
import java.awt.Panel;

import diet.Herbviore;
import diet.Omnivore;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;
import privateutil.ChewAnimals;

/**
 *Elephant Class That Describes Elephant behavior.
 * 
 * @author  Gitit Dadon-212280911, Michal Tenenboim-326045763 Ashdod Campus
 *@see ChewAnimals
 */
public class Elephant extends ChewAnimals  {
	/**
	 * 
	 */
	private double trunkLength;
	/**
	 * 
	 */
	private double weight=500;
	/**
	 * 
	 */
	private Point location=new Point(50,90);
	/**
	 * 
	 */
	private Herbviore herb=new Herbviore();
	
	/**
	 * a constructor which gets 1 parameter: name and the other parameters that were not given will be initialized 
	 * and a new object will be created from all the parameters.
	 * 
	 * @param name String ,name of the elephant
	 */
	public Elephant(String name) {
		this.setName(name);
		this.setWeight(weight);
		this.setLocation(location);
		this.setDiet(new Herbviore()); 
	}
	
	public Elephant(int size,String color,ZooPanel panel,int horSpeed, int verSpeed)
	{
		
		super(size,color,panel,horSpeed,verSpeed);
		this.setWeight(this.weight);
		this.setDiet(new Herbviore());
		this.setLocation(location);


	}
	/**
	 *a constructor which gets 2 parameters: name and location and the other parameters that were not given will be initialized 
	 * after that a new object will be created from all the parameters.
	 *
	 * @param name the name of the elephant
	 * @param location the location of the elephant
	 */
	public Elephant(String name,Point location) {
		
		super(name,location);
		this.setWeight(weight);
		this.setDiet(new Herbviore());
		
	}
    /**
     * a constructor which gets 2 parameters: name and trunk length and the other parameters that were not given will be initialized 
	 * after that a new object will be created from all the parameters.
     * 
     * @param name the name of the elephant
     * @param trunckLength the trunk length of the elephant
     */
	public Elephant(String name, double trunckLength)
	{
		this.setName(name);
		this.trunkLength=trunckLength;
		this.setDiet(new Herbviore());
		this.setLocation(location);
		this.setWeight(weight);
	}
	

	@Override
	/**
	 *  prints how the elephant makes sound when it chews.
	 */
	public void makeSound() {
		this.chew("Trumpets with joy while flapping its ears, then chews");
		
	}
	/**
	  Updates Elephant's Trunk Length According To the Given Parameter.
	 * @param d-double, Trunk length.
	 * @return true if trunk length is legal,else false.
	 */
	public boolean settrunkLength(double d) {
		if(d>=0.5 && d<=3)
		{
			this.trunkLength=d;
			return true;
		}
		return false;
		
	}
	

	

}
