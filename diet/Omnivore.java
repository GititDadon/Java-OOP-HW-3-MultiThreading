package diet;

import java.util.Arrays;

import animals.Animal;
import animals.Lion;
import food.EFoodType;
import food.IEdible;
import mobility.Point;



/**
 * Defines Animals That Eats Both Meat And Vegtables.
 * @version ....
 * @author Gitit Dadon-212280911, Michal Tenenboim-326945763
 * @see Bear
 */

public class Omnivore  implements IDiet{
	private Carnivore carn=new Carnivore();
	private Herbviore herb=new Herbviore();

	/**
	 * Default Constructor
	 */
	public Omnivore() {
		
	}

	
	/**
	 * Checks If Food Type Given Is Legal For carnivore To eat.
	 * @parameter EFoodType food- Gets an Enum-EFoodType Parameter that Mentions Any Kind Of Animal's Food Type.
	 * @return If Food Type Is Meat or Vegtable Returns True, Else False.
	 * @see EFoodType
	 */
	@Override
	public boolean canEat(EFoodType food) {
		
		
			if(food==EFoodType.NOTFOOD) {
				
				return false;
			}
			return true;
	}
	
	
	
	/**
	 * Checks if animal can eat given food and returns the weight the animal gained.
	 * @param  animal: animal from type Animal to Access Animal's Weight.
	 * 		   food: food from type IEdible to feed the animal
	 * @return how much weight the animal gained throughout eating.
	 * @see IEdible
	 */

	@Override
	public double eat(Animal animal, IEdible food)
	{

			if(this.carn.canEat(food.getFoodtype())==true) 
			{
				
				return this.carn.eat(animal, food);
			}
			else if(this.herb.canEat(food.getFoodtype())==true) 
			{
				return this.herb.eat(animal, food);
			}
		
		
		return 0;
	}
	
	
	
	

	
	
	
}
