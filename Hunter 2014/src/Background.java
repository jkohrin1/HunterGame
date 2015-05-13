import java.awt.*;

import javax.swing.*;

/*
 * A class to select a background and draw it to a graphics2D object
 */
@SuppressWarnings("serial")
public class Background extends JPanel{
		
		/*
		 * Constructs a Background object 
		 * @param x determines the type of background to be drawn
		 */
		public Background(int x){
			this.bgSel = x;
		}
	
	/*
	 * Overrides the JPanels original paint to draw a custom background
	 * @param g the graphics2D to draw the background to
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g)
	{
		if(bgSel==1){
			//draws a forest
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(73,97,6));
			g2.fillRect(0, 0, 800,600);
			g2.setColor(new Color(222,222,222));
			g2.fillRect(0, 0, 800, 100);
			Image imgfor = Toolkit.getDefaultToolkit().getImage("src/forback.jpg");
			g2.drawImage(imgfor, 0, 0,null);
		}
		
		else if(bgSel==2){
			//draws a mountain
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(222,209,209));
			g2.fillRect(0, 0, 800,600);
			Image imgmount = Toolkit.getDefaultToolkit().getImage("src/backmount.png");
			g2.drawImage(imgmount, 0, 0,null);
		}
		
		else if(bgSel==3){
			//draws a prairie
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(135,121,31));
			g2.fillRect(0, 0, 800,600);
			g2.setColor(new Color(210,249,252));
			g2.fillRect(0,0,800,100);
			g2.setColor(Color.yellow);
			g2.fillOval(20,20,40,40);
			g2.setColor(Color.white);
			g2.fillOval(50,40,100,10);
			Image imgmount = Toolkit.getDefaultToolkit().getImage("src/prairieback.jpg");
			g2.drawImage(imgmount, 0, 0,null);
		}
		
	}
	
	//Declares variable used
	private int bgSel;
}
