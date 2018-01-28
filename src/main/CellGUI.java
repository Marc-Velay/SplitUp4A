package main;
import javax.swing.*;
import java.awt.*;

public class CellGUI extends JPanel{
	public void paintComponent(Graphics g){
		this.setOpaque(true);
		int k=(int)(128+Math.random()*(255-128)); 
	    g.setColor(new Color(255-k,255-k,255));
	    g.fillOval(20, 20, 75, 75);
	    
	  }       
		
}
