package animals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.TabStop;

import diet.Carnivore;
import diet.IDiet;
import mobility.Point;
import plants.Cabbage;
import mobility.Mobile;
import food.EFoodType;
import food.IEdible;
import graphics.AllFoodTypes;
import graphics.IAnimalBehavior;
import graphics.IDrawable;

import graphics.ZooFrame;
import graphics.ZooPanel;
import diet.IDiet;
import privateutil.*;

/**
 * Describes The Behavior That All The Animals Have.
 * 
 * @author  Gitit Dadon-212280911, Michal Tenenboim-326945763 Ashdod Campus
 *
 */
public abstract class Animal extends Mobile implements IEdible,IDrawable,IAnimalBehavior,Runnable{
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private ZooFrame zooFrame;
	private  double weight=0;
	
	/**
	 * 
	 */
	private IDiet diet = null;
	
	
	
	private final int EAT_DISTANCE = 10;
	private int size;
	private String color;
	private int horSpeed;//next hw
	private int verSpeed;// next hw
	private boolean coordChanged;
	protected Thread thread;//next hw
	private int x_dir=1;
	private int y_dir=1;
	private int eatCount;
	private ZooPanel panel;
	private BufferedImage img1, img2;
	//String animalType=this.getAnimalName();
	protected boolean threadSuspended=false;

	public final static String PICTURE_PATH = "C:\\Users\\Administrator\\Desktop\\Java Projects\\Zoo 2\\assignment2_pictures\\";
	
	/**
	 *  a constructor which gets 2 parameters: name and location ,and the other parameters that were not given will be initialized 
	 * and a new object will be created from all the parameters.
	 * 
	 * @param name the name of the animal
	 * @param location the location of the animal
	 */
	public Animal(String name, Point location)
	{
		
		
		super(location);
	
		loadImages(this.color);

			
		this.setName(name);
		
		
	}
	public void setZooFrame(ZooFrame zooFrame)
	{
		this.zooFrame=zooFrame;
	}
	public synchronized void setXDir(int xDir)
	{
		this.x_dir=xDir;
	}
	public void setYDir(int yDir)
	{
		this.y_dir=yDir;
	}
	
	public Animal(int size,String color,ZooPanel pan,int horSpeed,int verSpeed) 
	{
		this.size=size;
		this.color=color;
		this.setWeight(this.weight);
		this.panel=pan;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.thread=new Thread(this);
		this.setXDir(1);
		this.setYDir(1);
		this.thread.start();
		this.threadSuspended=false;
		 


	}
	public boolean setThreadSuspended(boolean isSuspended)
	{
		threadSuspended=isSuspended;
		return threadSuspended;
	}
	public int getXDir()
	{
		return this.x_dir;
	}
	public int getYDir()
	{
		return this.y_dir;
	}
	
	public void setColor(String color)
	{
		this.color=color;
		
	}
	public void setSize(int size)
	{
		this.size=size;
		
	}
	public String getColor()
	{
		return this.color;
		
	}
	public int getSize()
	{
		return this.size;
		
	}
	
	/**
	 * a default constructor
	 */
	public Animal() 
	{
		loadImages(this.color);

	}
	/**
	 *returns an object which implements the interface Idiet. 
	 * 
	 * @return returns an object which implements the interface Idiet. 
	 */
	public IDiet getDiet() {
		return diet;
	}
	
	/**
	 * abstract function describes the behavior of each animal.
	 */
	public abstract void makeSound();
	
	/**
	 *  returns animal's Food Type.
	 * @return animal's food type from EFoodtype .
	 */
    public EFoodType getFoodtype() {
		
		return EFoodType.MEAT;
	}
		 
	/**
	 
	 * @return the name of  animal.
	 */
	public String getName() {
		
		
		//MessageUtility getter=new MessageUtility();
		
		
		//getter.logGetter(this.name, "getName", this.name);
		
		
		return this.name;
	}
	
	/**
	    changes the object name with the given parameter. 
	 * @param name the name of the animal
	 * @return returns true if the object's name was changed to the given parameter else will return false.
	 */
	public boolean setName(String name) {
		
		
		this.name=name;
		
		
		
		
		
		
		return true;
	}
	
	/**
	 
	 * @return returns the weight of the animal object we are working on.
	 */
	public double getWeight()
	{
		
		
		return this.weight;
	}
	
	/**
	 *  returns true if succeeded with changing the animal weight to the given parameter else returns false.
	 * @param weight the weight of the animal.
	 * @return returns true if the animal weight of the object was changed to the given parameter else returns false.
	 */
	public synchronized boolean setWeight(double weight) {
		
		if (weight>0)
		{
			this.weight=weight;
			
			return true;
		}
		return false;
	}
	/**
	 * Returns a string that describes animal object
	 */
	public String toString(){
		
		
		return "["+this.getClass().getSimpleName()+"]"+this.getName()+" "+this.getColor()+" "+this.getSize();
	}
	
	
public String toString2(){
		
		
		return "["+this.getClass().getSimpleName()+"]"+" ,"+"Size:"+this.getSize()+","+"Color: "+this.getColor();
	}
	
	/**
	 * function which returns the type of the animal object.
	 * 
	 * @return the type of the object we are working with.
	 */
	public Object getType() {
		
		return this.getClass();
	}
	
	/**
	 * moves the location of the object ,to a different location  
	 * and returns current calculation .
	 * @param point the point of the animal it's a new location 
	 * @return returns the calculation of the distance the object did this time, but not the total distance from all the times.
	 */
	public  double move(Point point)
	{ 
		Point p1=new Point(0,0);
		
		if(p1.checkBoundries(point))
		{
			this.addTotalDistance(this.calcDistance(point)); 
			
			point.setpoint(point);
			
		    this.setWeight(this.getWeight()-(this.calcDistance(point)*this.getWeight()*0.00025));
		   
		   
		    return this.calcDistance(point);
		}
			return 0;
		
	}
	/**
	 * receives a value from IEdible -a type of food if the object can eat it the function return true else 
	 * returns false.
	 * 
	 * @param var   an IEdible type object 
	 * @return returns true if the object can eat the food type else returns false.
	 * @see IEdible
	 */
	public  synchronized boolean eat(IEdible var) {
		double weight=diet.eat(this, var);
		if( weight>0) 
		{
			
			this.setWeight(weight+this.getWeight());
			this.makeSound();
			this.eatInc();
			return true;
			
		}
		return false;
	}
	/**
	 * 
	 * Updates Animal's Diet.
	 * @param diet -Object That Implement IDiet Interface
	 * @return true if succeeded changing the parameter diet of the object to the given parameter
	 * else false..
	 */
	public  boolean setDiet(IDiet diet) {
		this.diet=diet;
		return true;
	}
		
	
	
	public void loadImages(String nm)
	{
		
		final String [] links= {"_b_1.png","_b_2.png","_n_1.png","_n_2.png","_r_1.png","_r_2.png"};
		String animalType=this.getClass().getSimpleName();
		if(Arrays.asList("Red","Natural","Blue").contains(nm) || Arrays.asList("red","natural","blue").contains(nm))
		{
			
			if(animalType.equals("Lion"))
			{
				switch (nm)
				{
					
						case "Red","red":
								{
								
								try {
									this.img1=ImageIO.read(new File(PICTURE_PATH+"lio"+links[4]));
									this.img2=ImageIO.read(new File(PICTURE_PATH+"lio"+links[5]));
								} catch (IOException e) {
									
									System.out.println("Cant Load Image.");;
								}
								
								};break;
						case "Blue","blue":{
							try {
								this.img1=ImageIO.read(new File(PICTURE_PATH+"lio"+links[0]));
								this.img2=ImageIO.read(new File(PICTURE_PATH+"lio"+links[1]));
								
							} catch (IOException e) {
								
								System.out.println("Cant Load Image.");;
							}
							
							};break;
						case "Natural","natural":{
							try {
								this.img1=ImageIO.read(new File(PICTURE_PATH+"lio"+links[2]));
								this.img2=ImageIO.read(new File(PICTURE_PATH+"lio"+links[3]));
								
							} catch (IOException e) {
								
								System.out.println("Cant Load Image.");;
							}
							
							};break;
							
						
				}
			}
			if(animalType.equals("Bear"))
			{
				switch(nm)
				{
				case "Red","red":
				{
				
				try {
					this.img1=ImageIO.read(new File(PICTURE_PATH+"bea"+links[4]));
					this.img2=ImageIO.read(new File(PICTURE_PATH+"bea"+links[5]));
					
				} catch (IOException e) {
					
					System.out.println("Cant Load Image.");;
				}
				
				};break;
				case "Blue","blue":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"bea"+links[0]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"bea"+links[1]));
						
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");
					}
					
					};break;
				case "Natural","natural":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"bea"+links[2]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"bea"+links[3]));
						
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");
					}
			
			};break;
			
				}
			
			}
			
			if(animalType.equals("Elephant"))
			{
				switch (nm)
				{
				case "Red","red":
				{
				
				try {
					this.img1=ImageIO.read(new File(PICTURE_PATH+"elf"+links[4]));
					this.img2=ImageIO.read(new File(PICTURE_PATH+"elf"+links[5]));
					
				} catch (IOException e) {
					
					System.out.println("Cant Load Image.");;
				}
				
				};break;
				case "Blue","blue":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"elf"+links[0]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"elf"+links[1]));
						
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");
					}
					
					};break;
				case "Natural","natural":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"elf"+links[2]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"elf"+links[3]));
						
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");;
					}
			
			};break;
			
				}
			}
			
			if(animalType.equals("Turtle"))
			{
				switch (nm)
				{
				case "Red","red":
				{
				
				try {
					this.img1=ImageIO.read(new File(PICTURE_PATH+"trt"+links[4]));
					this.img2=ImageIO.read(new File(PICTURE_PATH+"trt"+links[5]));
					
				} catch (IOException e) {
					
					System.out.println("Cant Load Image.");;
				}
				
				};break;
				case "Blue","blue":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"trt"+links[0]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"trt"+links[1]));
						
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");;
					}
					
					};break;
				case "Natural","natural":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"trt"+links[2]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"trt"+links[3]));
						
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");;
					}
					
			};break;
			
				}
			}
			
			if(animalType.equals("Giraffe"))
			{
				switch (nm)
				{
				
				case "Red","red":
				{
				
				try {
					this.img1=ImageIO.read(new File(PICTURE_PATH+"grf"+links[4]));
					this.img2=ImageIO.read(new File(PICTURE_PATH+"grf"+links[5]));
					
				} catch (IOException e) {
					
					System.out.println("Cant Load Image.");
				}
				
				};break;
				case "Blue","blue":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"grf"+links[0]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"grf"+links[1]));
						
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");
					}
					
					};break;
				case "Natural","natural":{
					try {
						this.img1=ImageIO.read(new File(PICTURE_PATH+"grf"+links[2]));
						this.img2=ImageIO.read(new File(PICTURE_PATH+"grf"+links[3]));
					} catch (IOException e) {
						
						System.out.println("Cant Load Image.");
					}
				}
					
				
					
				}
			
					
			}
		}
		}
			
				
		

	
	
	public synchronized void drawObject (Graphics g)
		
	{
		if(this.x_dir==1)
		{
			
			g.drawImage(img1, this.getLocation().getX()-this.getSize()/2+getHorSpeed(), this.getLocation().getY()-this.getSize()/10,this.getSize()/2,this.getSize(),this.panel);
		}
		if(x_dir==-1)
		{	
			g.drawImage(img2, this.getLocation().getX(), this.getLocation().getY()-this.getSize()/10,getSize()/2 ,getSize(),this.panel);
		}
		
	}
	
	public String getAnimalName()
	{
		return this.getClass().getSimpleName();
	}
	public synchronized void eatInc()
	{
		
		
			this.eatCount+=1;
		
	}
	
	public synchronized int getEatCount()
	{
		return this.eatCount;
	}
	
	public  boolean getChanges()
	{
		return this.coordChanged;
	}
	public void setChanges(boolean state)
	{	
		this.coordChanged=state;
		
	}

	@Override
	public void run() {
		
		int oldX,oldY,newX,newY;

		while (true)
		{
			
					 oldX=this.getLocation().getX();
					 oldY=this.getLocation().getY();
					 newX=oldX+(this.getHorSpeed()*this.getXDir());
					 newY=oldY+(this.getVerSpeed()*this.getYDir());
					 this.setLocation(new Point(newX,newY));
				
					 if(threadSuspended) 
					 {
						 setSuspended();
		             }
		       
					System.out.println(getAnimalName()+" locaton:"+this.getLocation().toString());

				 
			
					try {
						 Thread.sleep(20);
					} catch (InterruptedException e) {
						System.out.println("Interrupted Exception Caught.");
					}
				
				if(1400<=getLocation().getX() || getLocation().getX()<=0)
				{
					x_dir*=-1;
				}
				if(550<=getLocation().getY() || getLocation().getY()<=0)
				{
					verSpeed=-verSpeed;
				}
				else if (getLocation().getX()>0 && getLocation().getX()<=10) {
					x_dir*=-1;
				}
				if(getLocation().getX()>0 && getLocation().getX()<=5
						&& getLocation().getY()>=550)
				{
					setXDir(1);
				}
				if( getLocation().getY()<=5)
					setLocation(new Point(getLocation().getX(),getLocation().getY()-1));
				moveAnimalToFood();
				

			}
			}
			
	@Override
	public void setSuspended() {
		
			synchronized (thread) {
				try {
					getThread().wait();
					System.err.println("Wait();");
				} catch (Exception e) {
				}
				
			}
			
		
		
	}
	public synchronized void setHorSpeed(int horSpeed)
	{
		this.horSpeed=horSpeed;
	}
	public synchronized void setVerSpeed(int verSpeed)
	{
		this.verSpeed=verSpeed;
	}
	public int getHorSpeed()
	{
		return this.horSpeed;
	}
	public  int getVerSpeed()
	{
		return this.verSpeed;
	}
	public synchronized Thread getThread()
	{
		return this.thread;
	}
	
	@Override
	public void setResumed() {
			synchronized (thread) {
				try {
					thread.notifyAll();
					System.out.println("notifyAll();");
					setThreadSuspended(false);

				} catch (Exception e) {
				}
				
			}
		
	}
	public synchronized void moveAnimalToFood()
	{
		for(AllFoodTypes food: AllFoodTypes.getFood())
		{
			if(food!=null)
			{
				if(getLocation().getX()<food.getLocation().getX())
				{
					move(new Point(getLocation().getX()+horSpeed,getLocation().getY()+verSpeed));
					
				}
				else {
					setXDir(-1);
					move(new Point(getLocation().getX()-horSpeed,getLocation().getY()-verSpeed));

					
				}
			}
			
		}
	}
	public boolean getThreadSuspended() {
		return threadSuspended;
	}
	
	
	
	
	

}