package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import animals.*;
import mobility.Point;
import plants.Plant;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Sets Up The Drawing For The Zoo.
 * 
 * @author  Gitit Dadon-212280911, Michal Tenenboim-326945763 Ashdod Campus
 *
 */
public class ZooPanel extends JPanel implements ActionListener,Runnable
{
	private JButton addAnimal=new JButton("Add Animal");
	private JButton moveAnimal=new JButton("Move Animal");
	private JButton clearAll=new JButton("Clear All");
	private JButton food=new JButton("Food");
	private JButton info=new JButton("Info");
	private JButton exit=new JButton("Exit");
	private static final int mainPanelWidth=2200;
	private static final int mainPanelHeight=667;
	private JPanel buttonsPanel=new JPanel();
	private final static String PICTURE_PATH="C:\\Users\\Administrator\\Desktop\\Java Projects\\Zoo 2\\assignment2_pictures\\";
	private static CopyOnWriteArrayList<Animal> animals=new CopyOnWriteArrayList();
	private BufferedImage img;
	static JPanel p=new JPanel();
	private Food foodType;
	private ZooFrame frame;
	private JButton sleep=new JButton("Sleep");
	private JButton wakeUp=new JButton("Wake Up");
	private Thread controller;
	private Timer timer;

	
	
	
	/**
	 * constructor recieves zoo frame in order to have access to the zooframe functionality.
	 * @param f- Of Type ZooFrame - all the options are displayed on the zooframe.
	 */
	public ZooPanel(ZooFrame f)
	{
		super();

		
		frame=f;
		addAnimal.addActionListener(this);
		moveAnimal.addActionListener(this);
		clearAll.addActionListener(this);
		food.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
		sleep.addActionListener(this);
		wakeUp.addActionListener(this);
		
		
		controller=new Thread(this);
		controller.start();
		
		timer=new Timer(10,this);
		moveAnimal.setEnabled(false);
		
		this.designButton(wakeUp);
		this.designButton(sleep);
		this.designButton(addAnimal);
		this.designButton(moveAnimal);
		this.designButton(clearAll);
		this.designButton(food);
		this.designButton(info);
		this.designButton(exit);
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setButtonPanel(buttonsPanel);
		this.setBounds(0,40,mainPanelWidth,mainPanelHeight);
		this.setPreferredSize(getPreferredSize());
		this.setVisible(true);
	}
	
	/**
	 * set up the buttons on a panel
	 * @param buttonsPanel -recieves JButton object and adds it to the displayButton panel
	 */
	private void setButtonPanel(JPanel buttonsPanel)
	{
		buttonsPanel.setSize(new Dimension(2200,70));
		buttonsPanel.add(addAnimal);
		buttonsPanel.add(sleep);
		buttonsPanel.add(wakeUp);
		//buttonsPanel.add(moveAnimal);
		buttonsPanel.add(clearAll);
		buttonsPanel.add(food);
		buttonsPanel.add(info);
		buttonsPanel.add(exit);
		
		this.add(buttonsPanel,BorderLayout.SOUTH);
		
	}
	/**
	 * Clears All Animals And Plants Thats Displayed On The Screen.
	 */
	private void clearAll()
	{
		
		animals.removeAll(animals);
		AllFoodTypes.getFood().removeAll(AllFoodTypes.getFood());
		this.food.setEnabled(true);
		repaint();
	}
	
	

	/*
	 * overriding action listener and adding events to the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==addAnimal)
		{
			AddAnimalDialog d=new AddAnimalDialog(frame,this.moveAnimal);
			
			
			
		}
		if(e.getSource()==moveAnimal)
		{
			new MoveAnimalDialog(frame);
			//manageZoo();

		}
		if(e.getSource()==clearAll)
		{
			clearAll();
			
		}
		if(e.getSource()==info)
		{
			new Info(frame);
			
			
		}
		if(e.getSource()==exit)
		{
			System.exit(0);
		}
		if(e.getSource()==food)
		{
			this.foodType= new Food(frame,this);
			food.setEnabled(false);
		}
		if(e.getSource()==sleep)
		{
			for(Animal animal :getAnimalList())
			{
				animal.setThreadSuspended(true);
			}
		}
		if(e.getSource()==wakeUp)
		{
			for(Animal animal :getAnimalList())
			{
				
				animal.setResumed();

			}
			
		}
	}
	
	/*
	 * designs the buttons
	 */
	private void designButton(JButton button) {
		button.setSize(250,250);
		button.setFont(new Font(Font.MONOSPACED,Font.BOLD,25));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.DARK_GRAY));
		
	}
	/**
	 * appends an animal to the animals list
	 * @param animal- Object type animal to add to the list
	 */
	public static void  setAnimalList(Animal animal)
	{
			animals.add( animal);
	}
	/**
	 * returns the animals list
	 * @return-List of type animal
	 */
	public static  List<Animal> getAnimalList()
	{	

		return animals;
	}
	/**
	 * overriding JPanel paintComponent method in order to customize panel drawings.
	 */
	@Override
	public void paintComponent(Graphics g) {
		 super.paintComponent(g) ;
		 Graphics2D g2d=(Graphics2D) g;
		 if(img!=null)
			 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			 g.drawImage(img,0,0,mainPanelWidth,mainPanelHeight, this);
			 if(getAnimalList().size()>0)
			 { 
				 for(int i=0;i<getAnimalList().size();++i)
				 {
					 // CHECK EXECPTON: "Cannot invoke "animals.Animal.getColor()" because the return value of "java.util.List.get(int)" is null"
					
					 getAnimalList().get(i).loadImages(getAnimalList().get(i).getColor());
					 getAnimalList().get(i).drawObject(g2d);
					 getAnimalList().get(i).move(getAnimalList().get(i).getLocation());
					 getAnimalList().get(i).setZooFrame(frame);
					 

				 }

		 }
			 if(foodType!=null)
			 {
				 for (int i=0;i<AllFoodTypes.getFood().size();++i)
				 {
					 AllFoodTypes.getFood().get(i).loadImages(foodType.getFoodName());
					 AllFoodTypes.getFood().get(i).drawObject(g2d);
					 foodType.getSelectedFood().setZooFrame(frame);

					 
					 
				 }
			 }
			 timer.start();
			
		}
	
	/**
	 * loads an image 
	 * @param path-String , the picture's name.
	 */
	public  void loadImage(String path)
	{
		try {
			this.img=ImageIO.read(new File(PICTURE_PATH+path));
		} catch (IOException e) {
			
			System.out.println("Cant Load Image.");
		}
		
		
	}
	/**
	 * checks eating conditions and returns true if they returns true.
	 * @return Boolean-true if condition is valid.
	 */
	public synchronized boolean eatPlants()
	{
		for(Animal animal:getAnimalList())
		{
			for(AllFoodTypes food:AllFoodTypes.getFood())
			{
				if(animal.getDiet().canEat(food.getFoodtype())
						&& animal.getLocation().getX()>=550 && animal.getLocation().getX()<=food.getLocation().getX())
				{
					try {
						animal.eat(food);
						Thread.sleep(70);
						JOptionPane.showMessageDialog(frame, animal.getClass().getSimpleName()+" Ate: "+food.getClass().getSimpleName(),"Eating Alert",JOptionPane.INFORMATION_MESSAGE);
					
						AllFoodTypes.getFood().remove(food);
						System.out.println(animal.getClass().getSimpleName()+ " ate :"+food.getClass().getSimpleName());
					} catch (Exception e) {
					}
					return true;
				}
				
			}
		}
		return false;
	}
	
	/**
	 * checks if animal can eat another animal in the zoo according to certain criterias.
	 * @return Boolean- true if conditions are valid.
	 */
	private synchronized boolean tryEatAnotherAnimal()
	{
		for(Animal predator:getAnimalList())
		{
			for(Animal prey:getAnimalList())
			{
				if((predator.getDiet().canEat(prey.getFoodtype())&&predator.getWeight()>=2*prey.getWeight() &&
						predator.calcDistance(prey.getLocation())<prey.getSize())) 
					 {
						predator.eat(prey);
						prey.setThreadSuspended(true);
						getAnimalList().remove(prey);
						JOptionPane.showMessageDialog(frame, predator.getClass().getSimpleName()+" Ate: "+prey.getClass().getSimpleName(),"Eating Alert",JOptionPane.INFORMATION_MESSAGE);
						return true;
						
					 }
			}
		}
		return false;

	}
	/**
	 * controller of the program responsible of repainting the zoo frame in any moment.
	 */
	
	/**
	 * returns current panel
	 * @return
	 */
	public static JPanel getP() {
		return p;
	}
	/**
	 * sets the move buttons state
	 * @param state- boolean-if state is true ,user can click on the move button,else the oppisite.
	 */
	public void setMoveAnimalButton(boolean state)
	{
		this.moveAnimal.setEnabled(state);
	}
	public JPanel getButtonsPanel()
	{
			return this.buttonsPanel;
	}

	public Thread getThread()
	{
		return controller;
	}
	@Override
	public void run() {
		
//		repaint();

		while (true)
		{
			
				eatPlants();
				repaint();
			
			
			if(tryEatAnotherAnimal())
			{
				 repaint();	
			}
		}
		
		
		
	}
	
	public void setResizeable(int x)
	{
		this.frame.resize(new Dimension(2200,800+x));
	}
}