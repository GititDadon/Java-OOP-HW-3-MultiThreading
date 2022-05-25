package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;

import java.util.List;


/**
 * Opens a window of choosing an animal and instancing it according to the user's selection.
 * 
 * @author  Gitit Dadon-212280911, Michal Tenenboim-326045763 Ashdod Campus
 *
 */

public class AddAnimalDialog extends JDialog implements ActionListener{

	
	private static Animal animal;
	private String type;
	private JTextField txt1=new JTextField();
	private static final String[] colors= {"Red","Blue","Natural"};
	private  final JCheckBox [] AnimalColor=new JCheckBox[colors.length];
	private static final String[] Animals= {"Lion","Bear","Giraffe","Turtle","Elephant"};
	private  final JCheckBox [] TypeAnimal=new JCheckBox[Animals.length];
	private JButton submit=new JButton("Submit");
	private int siz2;
	String sizeText;
	JLabel msg=new JLabel("Please Select Type Of Animal:");
	JLabel msg1=new JLabel("Please Select Animal's Color:");
	private JPanel ColorPanel=new JPanel();
	private JPanel AnimalPanel=new JPanel();
	private JPanel TextPanel=new JPanel();
	private JButton done=new JButton("Add Animal");
	JPanel DoneButton=new JPanel();
	private final ButtonGroup Colors=new ButtonGroup();
	private ZooPanel zooPanel;
	private  List<Animal>animals;
	private ZooFrame zFrame;
	private JButton moveButton;
	private JSlider jHorSpeed=new JSlider(1,10);
	private JSlider jVerSpeed=new JSlider(1,10);
	private JPanel displaySLiders=new JPanel();
	private JLabel horSpeed=new JLabel();
	private JLabel verSpeed=new JLabel();
	private int horSpeedValue=1;
	private int verSpeedValue=1;

	
	
/**
 * constructor the responsible of openning a new window that display the options of selecting
 * different animals .
 * @param zf- Zoo Frame object in order to use its functionality.
 * @param moveButton- JButton to enable using after adding an animal to the zoo.
 */
	
	public AddAnimalDialog(ZooFrame zf,JButton moveButton)
	{
		this.zFrame=zf;
		this.moveButton=moveButton;
		zooPanel=new ZooPanel(zFrame);
		this.setBounds(500, 500, 500, 650);
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		done.setEnabled(false);
		txt1.setPreferredSize(new Dimension(250,40));
		txt1.setFont(new Font("Consolas",Font.BOLD,18));
		displaySLiders.setLayout(new BoxLayout(displaySLiders, BoxLayout.PAGE_AXIS));
		displaySLiders.setSize(500, 200);
		displaySLiders.add(jHorSpeed);
		displaySLiders.add(jVerSpeed);
//		this.add(jHorSpeed);
//		this.add(jVerSpeed);

		this.getFieldText(txt1);
		this.add(ColorPanel,BorderLayout.PAGE_END);
		
		
		ColorPanel.add(msg1,BorderLayout.NORTH);
		
		msg1.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		msg1.setForeground(Color.DARK_GRAY);
		
		
		// Adding CheckBoxes to The ColorPanel.
		for(int i=0;i<colors.length;++i)
		{	
			
			AnimalColor[i]=new JCheckBox(colors[i]);
			AnimalColor[i].setFocusable(false);
			AnimalColor[i].setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
			AnimalColor[i].setBackground(Color.LIGHT_GRAY);
			AnimalColor[i].setForeground(Color.DARK_GRAY);
			this.AnimalColor[i].addActionListener(this);

			this.ColorPanel.setLayout(new BoxLayout(ColorPanel,BoxLayout.PAGE_AXIS));
			this.ColorPanel.add(AnimalColor[i]);
		}
		ColorPanel.setBackground(Color.LIGHT_GRAY);
		// Adding Label Above CheckBoxes
		this.add(msg,BorderLayout.NORTH);

		this.AnimalPanel.add(msg,BorderLayout.WEST);
		msg.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		msg.setForeground(Color.DARK_GRAY);
		
		
		//Adding Animal Types As CheckBoxes.
		for (int i=0;i<Animals.length;++i)
		{
			
			
			TypeAnimal[i]=new JCheckBox(Animals[i]);
			TypeAnimal[i].setFocusable(false);
			TypeAnimal[i].setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
			TypeAnimal[i].setForeground(Color.DARK_GRAY);
			TypeAnimal[i].setBackground(Color.LIGHT_GRAY);
			TypeAnimal[i].addActionListener(this);
			
			this.AnimalPanel.setLayout(new BoxLayout(AnimalPanel,BoxLayout.PAGE_AXIS));
			this.AnimalPanel.add(TypeAnimal[i]);
			AnimalPanel.setBackground(Color.LIGHT_GRAY);
			this.add(AnimalPanel);
		}
		TextPanel.add(txt1,BorderLayout.SOUTH);
		TextPanel.add(submit,BorderLayout.AFTER_LAST_LINE);
		TextPanel.setBackground(Color.LIGHT_GRAY);
		
		this.add(TextPanel,BoxLayout.LINE_AXIS);
		this.DoneButton.add(done);
		this.setSliderDesign(jHorSpeed);
		this.setSliderDesign(jVerSpeed);
		
		jHorSpeed.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				horSpeed.setText("Horizotianl Speed= "+jHorSpeed.getValue());
				horSpeedValue=jHorSpeed.getValue();
				
			}
		});
		jVerSpeed.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				verSpeed.setText("Vertical Speed= "+jVerSpeed.getValue());
				verSpeedValue=jVerSpeed.getValue();
				
			}
		});
		displaySLiders.add(horSpeed);
		displaySLiders.add(verSpeed);
		add(displaySLiders);
		displaySLiders.setBackground(Color.LIGHT_GRAY);
		this.add(DoneButton);
		this.doneButton();
		done.addActionListener(this);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		//this.pack();
		this.setVisible(true);
	}
	private void setSliderDesign(JSlider slider)
	{
		slider.setBackground(Color.LIGHT_GRAY);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(2);
		horSpeed.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
		verSpeed.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));

		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(4);
		slider.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
		slider.setPaintLabels(true);
		slider.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.DARK_GRAY));
		

	}
	private String getFieldText(JTextField text)
	{

		this.submit.setBorder(BorderFactory.createEtchedBorder(Color.red, Color.blue));
		this.submit.setForeground(Color.black);
		this.submit.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		this.submit.setBackground(Color.LIGHT_GRAY);
		this.submit.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.DARK_GRAY));
		this.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sizeText=txt1.getText();
				
				try {
					siz2=Integer.parseInt(sizeText);
					done.setEnabled(true);
					if(siz2<50 || siz2>300)
					{
						JOptionPane.showMessageDialog(text, "Invalid Size,Please Select A Number Between 50-300.","Size Error",JOptionPane.ERROR_MESSAGE);
						
						
					}
				}catch(Exception x) {JOptionPane.showMessageDialog(text, "Please Enter Integer Number.","Input Error",JOptionPane.ERROR_MESSAGE);
;};
				
			}
			
		});
		return sizeText;
	}
	
	/**
	 * set the state of the icons.
	 */
	private void setCheckIcons()
	{
		TypeAnimal[0].setEnabled(true);
		TypeAnimal[1].setEnabled(true);
		TypeAnimal[2].setEnabled(true);
		TypeAnimal[3].setEnabled(true);
		TypeAnimal[4].setEnabled(true);
		
		AnimalColor[0].setEnabled(true);
		AnimalColor[1].setEnabled(true);
		AnimalColor[2].setEnabled(true);
		
		AnimalColor[0].setSelected(false);
		AnimalColor[1].setSelected(false);
		AnimalColor[2].setSelected(false);
		
		TypeAnimal[0].setSelected(false);
		TypeAnimal[1].setSelected(false);
		TypeAnimal[2].setSelected(false);
		TypeAnimal[3].setSelected(false);
		TypeAnimal[4].setSelected(false);
		
		this.moveButton.setEnabled(true);
		

		
		txt1.setText(null);
		done.setEnabled(false);
		zooPanel.repaint();

		
	}
	/**
	 * getting the necsery information after user's selection.
	 */
	private void doneButton()
	{
		this.done.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.DARK_GRAY));
		this.done.setPreferredSize(new Dimension(250,70));
		this.done.setForeground(Color.DARK_GRAY);
		this.done.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		this.done.setBackground(Color.LIGHT_GRAY);
		this.DoneButton.setBackground(Color.LIGHT_GRAY);

		
	}
	/**
	 * overriding ActionLisener method in order to add events to the buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		String color = null;
		
		if(AnimalColor[0].isSelected()==false && AnimalColor[1].isSelected()==false && AnimalColor[2].isSelected()==false)
		{
			JOptionPane.showMessageDialog(this, "Must Select 1 Color!","No Color Choosen",JOptionPane.ERROR_MESSAGE);
		}
		
		
		if(TypeAnimal[0].isSelected()==false && TypeAnimal[1].isSelected()==false && TypeAnimal[2].isSelected()==false && TypeAnimal[3].isSelected()==false && TypeAnimal[4].isSelected()==false )
		{
			JOptionPane.showMessageDialog(this, "Must Select 1 Animal Type!","No Animal Was Choosen",JOptionPane.ERROR_MESSAGE);
		}
		
		if(this.zooPanel.getAnimalList().size()>10)
		{
			JOptionPane.showMessageDialog(this, "Cannot Add More Than 10 this.zooPanel.getAnimalList().","OverFlow",JOptionPane.ERROR_MESSAGE);
			this.zooPanel.getAnimalList().remove(10);
		}
		
		
		for (int i=0; i<this.AnimalColor.length;++i)
		{
			if(AnimalColor[i].isSelected())
			{
				if(AnimalColor[i].isEnabled());
				{
					
					AnimalColor[0].setEnabled(false);
					AnimalColor[1].setEnabled(false);
					AnimalColor[2].setEnabled(false);
					

				}
				
				switch(AnimalColor[i].getText())
				{	
					

				case "Red":
					{
						color="red";
						
						break;
					}
				case "Blue":color="blue";break;
				case "Natural":color="natural";break;
				}
			}
			
		}
		
		
			for(int i=0;i<TypeAnimal.length;++i)
			{
				if(e.getSource()==TypeAnimal[i] && TypeAnimal[i].isSelected() && TypeAnimal[i].isEnabled())
					
				{
					TypeAnimal[0].setEnabled(false);
					TypeAnimal[1].setEnabled(false);
					TypeAnimal[2].setEnabled(false);
					TypeAnimal[3].setEnabled(false);
					TypeAnimal[4].setEnabled(false);
					type=TypeAnimal[i].getText();
					if(e.getSource()==done)
					TypeAnimal[0].setEnabled(false);
					TypeAnimal[1].setEnabled(false);
					TypeAnimal[2].setEnabled(false);
					TypeAnimal[3].setEnabled(false);
					TypeAnimal[4].setEnabled(false);
				}
				
					
			}
			
			
		if(e.getSource()==done)
			{
			if(type!=null)
			{
				
			
				switch(type)
				{
				case "Lion":
				{
					
					System.out.println("Case Lion ");
					animal=new Lion(siz2,color,this.zooPanel,horSpeedValue,verSpeedValue);
					this.zooPanel.setAnimalList(animal);
					
					this.setCheckIcons();
					zFrame.resize(new Dimension(2200,760));
					System.out.println("hor "+horSpeedValue+"  ver:"+verSpeedValue);
					
					break;

				}
				
				case "Bear":
				{
					System.out.println("Case Bear ");
					animal=new Bear(siz2,color,zooPanel,horSpeedValue,verSpeedValue);
					this.zooPanel.setAnimalList(animal);
					zFrame.resize(new Dimension(2201,771));

					this.setCheckIcons();

					


					break;
				}
				
				case "Giraffe":
				{
					animal=new Giraffe(siz2,color,zooPanel,horSpeedValue,verSpeedValue);
					this.zooPanel.setAnimalList(animal);
					zFrame.resize(new Dimension(2198,772));

					this.setCheckIcons();



					
					break;
				}
				
				case "Elephant":
				{
					animal=new Elephant(siz2,color,zooPanel,horSpeedValue,verSpeedValue);
					this.zooPanel.setAnimalList(animal);
					zFrame.resize(new Dimension(2203,773));

					this.setCheckIcons();


					
					break;
				}
				
				case "Turtle":
				{
					animal=new Turtle(siz2,color,zooPanel,horSpeedValue,verSpeedValue);
					this.zooPanel.setAnimalList(animal);
					zFrame.resize(new Dimension(2204,774));

					this.setCheckIcons();

					
					break;
					
				}
				
				}

				
			}
				}
				

		}
	
	public static Animal getAnimal()
	{
		return animal;
	}
	
	
	
}

			
			
			
			



