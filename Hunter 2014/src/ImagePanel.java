import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * A class to hold an image on a Jpanel
 */
	@SuppressWarnings("serial")
	class ImagePanel extends JPanel {
		private Image img;
		
		/*
		 * Constructs the ImagePanel 
		 * @param img the name of an image you want drawn to the panel
		 */
		public ImagePanel(String img){
			this(new ImageIcon(img).getImage());
		}
		
		/*
		 * Constructs the ImagePanel 
		 * @param img the image you want to be drawn on the panel
		 */
		public ImagePanel(Image img){
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));  
    			setPreferredSize(size);  
   		 	setMinimumSize(size);  
    			setMaximumSize(size);  
    			setSize(size);  
    			setLayout(null);  	
  		}  
  
		/*
		 * Paints the image to the graphics ojbect
		 * @ param g the graphics object
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
  		public void paintComponent(Graphics g) {  
    		g.drawImage(img, x, y, null);  
  		}  
  		
  		//Declare variables
  		private int x;
  		private int y;
	}