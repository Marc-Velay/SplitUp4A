package main;
import javax.swing.*;
import java.awt.*;

public class WorldGUI{
	public WorldGUI(){
		
		World world=new World();
		
		JFrame frame=new JFrame("Let's split up alpha 0.0");
		frame.setMinimumSize(new Dimension(1000,1000));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		int i = 40;
		int j = 40;
		GridLayout gdl=new GridLayout(i,j);
		JPanel[][] panelHolder = new JPanel[i][j];
		
		for(int m = 0; m < i; m++) {
		   for(int n = 0; n < j; n++) {
		      panelHolder[m][n] = new JPanel();
		      panelHolder[m][n].setBackground(Color.white);
		      frame.getContentPane().add(panelHolder[m][n]);
		   }
		}
		for(int m = 0; m < i; m++) {
		   for(int n = 0; n < j; n++) {
			  int k=(int)(Math.random()*255); 
		      panelHolder[m][n].setBackground(new Color(255,255-k,255-k));
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


