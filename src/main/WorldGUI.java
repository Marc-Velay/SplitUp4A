package main;
import javax.swing.*;

import main.World.FillMethod;

import java.awt.*;

public class WorldGUI{
	public WorldGUI(){
		
		//World world=new World();
		
		JFrame frame=new JFrame("Let's split up alpha 0.0");
		frame.setMinimumSize(new Dimension(1000,1000));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		int width = World.WIDTH;
		int height = World.HEIGHT;
		
		World world=new World(width/2, height/2, FillMethod.SMOOTH_GRADIENT);
		
		JPanel[][] panelHolder = new JPanel[width][height];
		
		GridLayout gdl=new GridLayout(width,height);
		
		frame.setLayout(gdl);
		
		for(int m = 0; m < width; m++) {
		   for(int n = 0; n < height; n++) {
		      panelHolder[m][n] = new JPanel();
		      panelHolder[m][n].setBackground(Color.white);
		      frame.getContentPane().add(panelHolder[m][n]);
		   }
		}

		for(int m = 0; m < width; m++) {
		   for(int n = 0; n < height; n++) {
			   if (world.getBrick(m, n).getFood() <0) world.getBrick(m, n).setFood(0);
			   //System.out.println("food " + m + ", " +n +" : " + (int)world.getBrick(m, n).getFood());
			   panelHolder[m][n].setBackground(new Color(50, (int)world.getBrick(m, n).getFood(), (int)world.getBrick(m, n).getFood()/25));
		   }
		}
		
		final JPanel cell = (JPanel) frame.getGlassPane();
		cell.setPreferredSize(new Dimension(1000,1000));
		cell.setLayout(new BorderLayout());
		cell.add(new CellGUI());
		cell.setVisible(true);
		
		frame.setLayout(gdl);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new WorldGUI();
	}
}


