package main;
import javax.swing.*;
import java.awt.*;

public class WorldGUI {
	public WorldGUI(){
		World world=new World();
		JFrame frame=new JFrame("Let's split up alpha 0.0");
		frame.setMinimumSize(new Dimension(1000,1000));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int i = 40;
		int j = 40;
		JPanel[][] panelHolder = new JPanel[i][j];
		GridLayout gdl=new GridLayout(i,j);
		frame.setLayout(gdl);
		for(int m = 0; m < i; m++) {
		   for(int n = 0; n < j; n++) {
		      panelHolder[m][n] = new JPanel();
		      panelHolder[m][n].setBackground(Color.white);
		      frame.getContentPane().add(panelHolder[m][n]);
		   }
		}
		gdl.setHgap(2); 
		gdl.setVgap(2);
		for(int m = 0; m < i; m++) {
			   for(int n = 0; n < j; n++) {
			      panelHolder[m][n].add(new JLabel("a"));
			   }
			}
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new WorldGUI();
	}
}


