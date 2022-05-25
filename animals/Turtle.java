package animals;
import diet.Carnivore;
import diet.Herbviore;
import food.IEdible;
import graphics.ZooPanel;
import food.EFoodType;
import mobility.Point;
//import diet.IDiet;
import privateutil.ChewAnimals;

/**
 * a class which will build new objects from turtle type.
 * 
 * @author Gitit Dadon-212280911, Michal Tenenboim-326045763 Ashdod Campus
 *@see ChewAnimals
 *
 */
public class Turtle extends ChewAnimals  {
	
	/**
	 * 
	 */
	private double weight =1;
	/**
	 * 
	 */
	private Point p=new Point(80,0);
	/**
	 * 
	 */
	private int age=1;
	/**
	 *  a constructor which gets 1 parameter: name and the other parameters that were not given will be initialized 
	 * and a new object will be created from all the parameters.
	 * 
	 * @param name the name of the  turtle
	 */
    public Turtle(String name){
    	
		this.setName(name);
		this.setWeight(this.weight);
		this.setLocation(p);
		this.setDiet(new Herbviore());
	}
    
    public Turtle(int size,String color,ZooPanel panel,int horSpeed,int verSpeed)
	{
		
		super(size,color,panel,horSpeed,verSpeed);
		this.setLocation(p);
		
		this.setWeight(this.weight);
		
		this.setDiet(new Herbviore());

	}
    /**
     * a constructor which gets 2 parameters: name and location and the other parameters that were not given will be initialized 
	 * after that a new object will be created from all the parameters.
     * 
     * @param name the name of the turtle
     * @param p1 the location of the turtle
     */
	public Turtle(String name,Point p1)
	{
		super(name,p1);
		this.setWeight(this.weight);
		this.setDiet(new Herbviore());
	}
	/**
	 * a constructor which gets 2 parameters: name and age and the other parameters that were not given will be initialized 
	 * after that a new object will be created from all the parameters.
	 * 
	 * @param name the name of the turtle
	 * @param age the age of the turtle
	 */
	public Turtle(String name,int age)
	{
		this.setName(name);
		this.setAge(age);
		this.setWeight(weight);
		this.setDiet(new Herbviore());
	}
	/**
	 * Updates The turtle's age according to the given parameter.
	 * @param age -int, represents turtle's age 
	 * @return true if the age is legal,else returns false.
	 */
	public boolean setAge(int age) {
		
		if (age<0||age>500) {
			
			
			
			return false;
		}
		else
			
		this.age=age;
		
		
		
		return true;
	}
	@Override
	/**
	 * prints how the turtle makes sound when it chews.
	 */
	public void makeSound() {
		
		chew("Retracts its head in then eats quietly");
	}
	
	
	
	
}	