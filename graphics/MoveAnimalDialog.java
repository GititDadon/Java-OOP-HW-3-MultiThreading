package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import animals.Animal;
import mobility.Point;

/**
 * Opens a window of choosing a coordinate
 *  and moving an animal  according to the user's selection.
 * 
 * @author  Gitit Dadon-212280911, Michal Tenenboim-326045763 Ashdod Campus
 *
 */
public class MoveAnimalDialog extends JDialog {

	private Info info=new Info("");
	private  JTable jt=info.getTable();
	private ZooPanel zooPanel;
	private static final JLabel label=new JLabel("Please Fill The Following Data\n");
	private static final JLabel label2=new JLabel("And Select Animal From Table");
	private JOptionPane pane;
	private JTextField x=new JTextField();
	private JTextField y=new JTextField();
	private JPanel plate=new JPanel();
	private JButton moveAnimal=new JButton("Choose Point");
	private JPanel displayButton=new JPanel();
	private int pX,pY;
	private String getX,getY;
	private Animal animal;
	private JPanel displayLabel=new JPanel();
	private String colorImage;
	private static ZooFrame frame;
	
	/**
	 * designs a label
	 * @param label-JLabel
	 */
	private void setLabel(JLabel label)
	{
		label.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
		label.setForeground(Color.darkGray);
	}
	/**
	 * constructor that opens up a window for the user with Point selection options.
	 * @param f-Zoo Frame- using its functionality
	 */
	public MoveAnimalDialog(ZooFrame f) {
		frame=f;
		this.zooPanel=new ZooPanel(frame);
		this.setLabel(label);
		this.setLabel(label2);
		displayLabel.setSize(200,200);
		this.displayLabel.add(label,BorderLayout.WEST);
		this.displayLabel.add(label2,BorderLayout.CENTER);
		this.add(displayLabel,BorderLayout.WEST);
		insertPoint();
        
        
        jt.setSelectionBackground(Color.GREEN);
       
       
	     
		this.add(jt,BorderLayout.AFTER_LAST_LINE);
		
		
		
		displayButton.add(moveAnimal,BorderLayout.SOUTH);
		this.add(displayButton,BorderLayout.SOUTH);


		JScrollPane js=new JScrollPane(jt);
		this.add(js);
		
		
			
			
		
	
	
		
		
		moveAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				getX=x.getText();
				getY=y.getText();
				try {
					pX=Integer.parseInt(getX);
					pY=Integer.parseInt(getY);
					Point p=new Point(0,0);
					if(p.checkBoundries(new Point(pX,pY))!=true)
					{
						pane.showMessageDialog(x, "Point Values Out Of Borders!","Out Of Border Error ",pane.ERROR_MESSAGE);
						return;
					}
					else
					{
						jt.setEnabled(true);
						pane.showConfirmDialog(x, "You Selected: "+"("+pX+","+pY+")");
						
						jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

							@Override
							public void valueChanged(ListSelectionEvent e) {
								for(int i=0;i<jt.getRowCount();++i)
								{
									if(jt.getSelectedRow()==i)
										
									{
										
										
										pane.showMessageDialog(jt,"You Chose :"+jt.getValueAt(i, 0));
										Object value=jt.getValueAt(i, 0);
										if(value.equals("Lion"))
										{
											// send pX pY
											ZooPanel.getAnimalList().get(i).move(new Point(pX,pY));
											System.out.println(ZooPanel.getAnimalList().get(i).toString2());
											System.out.println("Point: "+pX+" "+pY);
											frame.resize(new Dimension(2200,780));

										}
										if(value.equals("Bear"))
										{
											// send pX pY
											ZooPanel.getAnimalList().get(i).move(new Point(pX,pY));
											System.out.println(ZooPanel.getAnimalList().get(i).toString2());

											frame.resize(new Dimension(2200,784));

										}
										if(value.equals("Giraffe"))
										{
											// send pX pY
											ZooPanel.getAnimalList().get(i).move(new Point(pX,pY));
											System.out.println(ZooPanel.getAnimalList().get(i).toString2());
											frame.resize(new Dimension(2200,785));

											
										}
										if(value.equals("Elephant"))
										{
											// send pX pY
											ZooPanel.getAnimalList().get(i).move(new Point(pX,pY));
											System.out.println(ZooPanel.getAnimalList().get(i).toString2());
											frame.resize(new Dimension(2200,786));

										}
										if(value.equals("Turtle"))
										{
											// send pX pY
											ZooPanel.getAnimalList().get(i).move(new Point(pX,pY));
											System.out.println(ZooPanel.getAnimalList().get(i).toString2());
											frame.resize(new Dimension(2200,778));

											
										}
										
										else if(!(value.equals("Total")))
												{
													ZooPanel.getAnimalList().get(i).setChanges(true);
													x.setText(null);
													y.setText(null);
													frame.resize(new Dimension(2200,780));

													ZooPanel.getAnimalList().get(i).setLocation(new Point(pX,pY));
													//zooPanel.getThread().start();
													
													
												}

									}
								}
								

							}
							
						});
						

					}
				}catch(Exception e1)
				{
					System.out.println("Invalid Value.");
					pane.showMessageDialog(displayButton, "Invalid Point Values!","Input Error",pane.ERROR_MESSAGE);
					jt.setEnabled(false);
					x.setText(null);
					y.setText(null);
					return;
				}
				
				
			}
			
		});
		jt.setPreferredScrollableViewportSize(new Dimension(450,63));
		jt.setFillsViewportHeight(true);
		zooPanel=new ZooPanel(this.frame);
		this.setBounds(500, 500, 500, 500);
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		this.setVisible(true);
	}
	
	/**
	 * designing the text field of point insertion
	 */
	private void insertPoint()
	{
		plate.setSize(200,300);
		plate.add(x);
		plate.add(y);
		//plate.setLayout();
		displayButton.setSize(250,250);
		x.setPreferredSize(new Dimension(250,40));
		y.setPreferredSize(new Dimension(250,40));
		this.designTextField(x);
		this.designTextField(y);
		this.add(plate);
		this.designButton(moveAnimal);
		
	}
	

	/**
	 * recieves a jbutton ad designs it
	 * @param button-JButton
	 */
	private void designButton(JButton button) {
		button.setSize(250,250);
		button.setFont(new Font(Font.MONOSPACED,Font.BOLD,25));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.DARK_GRAY));
		
	}
	/**
	 * desgns the text fields of the point
	 * @param text-JTextField
	 */
	private void designTextField(JTextField text)
	{
		text.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
		text.setForeground(Color.BLACK);
		text.setBorder(BorderFactory.createEtchedBorder(Color.GREEN, Color.DARK_GRAY));
		text.setSize(200,50);
		text.setBackground(Color.LIGHT_GRAY);
	}
	
	
}
