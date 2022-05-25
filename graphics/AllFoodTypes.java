package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import food.EFoodType;
import food.IEdible;
import mobility.ilocatable;

public abstract class AllFoodTypes  implements IEdible,IDrawable {

	private static CopyOnWriteArrayList<AllFoodTypes> food=new CopyOnWriteArrayList();
	
	public static List <AllFoodTypes> getFood()
	{
		return food;
	}
	private ZooPanel zooPanel;
	private ZooFrame zooFrame;
	private String getType=this.getClass().getSimpleName();
	private BufferedImage img;
	private final static String PICTURE_PATH="C:\\Users\\Administrator\\Desktop\\Java Projects\\Zoo 2\\assignment2_pictures\\";

	public AllFoodTypes(ZooFrame frame,ZooPanel panel) {
		this.zooFrame=frame;
		this.zooPanel=panel;
		
	}

	public String getFoodName()
	{
		return this.getType;
	}

	public void loadImages(String nm)
	{
		if(this.getClass().getSimpleName().equals("Lettuce"))
		{
			try {
					this.img=ImageIO.read(new File(PICTURE_PATH+"lettuce.png"));
				} catch (IOException e) {
								
					System.out.println("Cant Load Image.");
				}
		}
		 if(this.getClass().getSimpleName().equals("Cabbage"))
			{
				try {
					this.img=ImageIO.read(new File(PICTURE_PATH+"cabbage.png"));
				} catch (IOException e) {
								
					System.out.println("Cant Load Image.");
				}
			}
		 if(this.getClass().getSimpleName().equals("Meat"))
		 {
				try {
					this.img=ImageIO.read(new File(PICTURE_PATH+"meat.gif"));
				} catch (IOException e) {
					System.out.println("Cannot Load Meat Image");
				}

		 }
							
	}
	
	public void drawObject(Graphics g)
	{
		if(img!=null)
			g.drawImage(img,590,250, 100,100,zooPanel);
	}

	
	public mobility.Point getLocation() {
		return new mobility.Point(590,250);
	}

	public double getWeight() {
		return 50;
	}
	public void setZooFrame(ZooFrame zooFrame)
	{
		this.zooFrame=zooFrame;
	}

	

}
