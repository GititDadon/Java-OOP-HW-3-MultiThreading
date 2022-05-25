package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import animals.Animal;

public class Info extends ZooPanel{

	private JTable jt;
	//private static ZooPanel zooPanel=new ZooPanel();
	//private List<Animal> animals=zooPanel.getAnimalList();
	DefaultTableModel tableModel=new DefaultTableModel();
	private int total=0;

	
	public Info(ZooFrame frame) 
	{
		super(frame);
		this.initTable();

		JPanel panel=new JPanel();
		panel.setSize(500,500);
		panel.setBackground(Color.BLACK);
		 jt.setFocusable(false);
	     
		panel.add(jt);
		
        jt.setRowSelectionAllowed(false);

		JDialog d=new JDialog();
		d.setSize(500,500);
		d.add(panel);
		d.setVisible(true);
//		this.add(jt);
		this.setSize(new Dimension(500,500));
		JScrollPane js=new JScrollPane(jt);
		d.add(js);
		this.setVisible(true);
		
	}
	public Info(String s)
	{
		super(null);
		this.initTable();
	}
	public  JTable getTable()
	{
		return jt;
	}
	public void initTable()
	{
		jt=new JTable(tableModel);
		jt.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
	     jt.setEnabled(false);
	     jt.setGridColor(Color.black);
	     jt.setRowHeight(50);
	    jt.setBorder(BorderFactory.createEtchedBorder(Color.GREEN, Color.DARK_GRAY));
	    jt.setBackground(Color.LIGHT_GRAY);
	    jt.getTableHeader().setBackground(Color.green);
	    jt.getTableHeader().setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		tableModel.addColumn("Animal");
		tableModel.addColumn("Color");
		tableModel.addColumn("Weight");
		tableModel.addColumn("Hor Speed");
		tableModel.addColumn("Ver SPeed");
		tableModel.addColumn("Eat Count");
		
		
		for(int i=0;i<this.getAnimalList().size();++i)
		{
			tableModel.addRow(new Object[] {this.getAnimalList().get(i).getClass().getSimpleName(),
					this.getAnimalList().get(i).getColor(),this.getAnimalList().get(i).getWeight(),"HW 3 :)","HW 3 :)",this.getAnimalList().get(i).getEatCount()});
		
			total+=this.getAnimalList().get(i).getEatCount();
		}
		tableModel.addRow(new Object[] {"Total","","","","",total});

		jt.setFillsViewportHeight(true);
		jt.setPreferredScrollableViewportSize(new Dimension(450,63));

	}

}
