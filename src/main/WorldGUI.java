package main;
import javax.swing.*;

import main.World.FillMethod;

import java.awt.*;

public class WorldGUI{
	
	public static int WINDOWWIDTH = 1000;
	public static int WINDOWHEIGHT = 1000; 
	
	public WorldGUI(){
		
		//World world=new World();
		
		JFrame frame=new JFrame("Let's split up alpha 0.0");
		frame.setMinimumSize(new Dimension(WINDOWWIDTH,WINDOWHEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);


		final JPanel cellPanel = (JPanel) frame.getGlassPane();
		cellPanel.setPreferredSize(new Dimension(WINDOWWIDTH,WINDOWHEIGHT));
		cellPanel.setLayout(new BorderLayout());
		
		
		int width = World.WIDTH;
		int height = World.HEIGHT;
		
		World world=new World(width/2, height/2, FillMethod.GRADIENT);
		
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

		cellPanel.setVisible(true);
		
		frame.setLayout(gdl);
		frame.setVisible(true);
		
		
		
		
		for(int time =0; time < 200; time++) {
			try {
				cellPanel.removeAll();
				world.updateCells();
				world.updateBricks();
				for(int m = 0; m < width; m++) {
					for(int n = 0; n < height; n++) {
						//System.out.println("fitness " + m + ", " +n +" : " + (int)world.getBrick(m, n).getFitness());
						if (world.getBrick(m, n).getFitness() <= 50)  {panelHolder[m][n].setBackground(new Color(75-(int)(world.getBrick(m, n).getFitness()*1), 0, 0));}
						else {
							panelHolder[m][n].setBackground(new Color(0, (int)(world.getBrick(m, n).getFitness()*1), 0));
						}	   
					}
				}
				System.out.println(world.cells.size());
				for(int cell = 0; cell < world.cells.size(); cell++) {
					cellPanel.add(world.cells.get(cell));
				}
				cellPanel.setVisible(true);
				frame.repaint();

				Thread.sleep(900);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new WorldGUI();
	}
}


