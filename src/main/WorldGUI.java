package main;
import javax.swing.*;

import main.World.FillMethod;

import java.awt.*;

public class WorldGUI {
	public WorldGUI(){
		JFrame frame=new JFrame("Let's split up alpha 0.0");
		frame.setMinimumSize(new Dimension(1000,1000));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		int width = World.WIDTH;
		int height = World.HEIGHT;
		World world=new World(width/2, height/2, FillMethod.RANDOM);
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
				  int k=(int)(Math.random()*255); 
			      panelHolder[m][n].setBackground(new Color(255,255-k,255-k));
			   }
			}
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new WorldGUI();
	}
}


