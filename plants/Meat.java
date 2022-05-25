package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import javax.imageio.ImageIO;

import food.EFoodType;
import food.IEdible;
import graphics.AllFoodTypes;
import graphics.Food;
import graphics.ZooFrame;
import graphics.ZooPanel;


public class Meat extends AllFoodTypes implements IEdible{
	private ZooPanel zooPanel;
	public Meat(ZooFrame f,ZooPanel zoopanel) {
		super(f,zoopanel);
		System.out.println("Created Meat Object");
		
	}
	@Override
	public EFoodType getFoodtype() {
		
		return EFoodType.MEAT ;
	}
	
	public String getFoodName()
	{
		return "meat";
	}
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return "natural";
	}
	
	
}