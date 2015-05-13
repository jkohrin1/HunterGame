import java.awt.*;
import java.awt.geom.*;
/*
 * Creates a shape of a gun 
 */
public class RifShape implements MoveableShape {
	
	/*
	 * Constructs a RifShape object 
	 * @param type the type of rifle to be drawn
	 * @param x the x position of where the rifle will be drawn
	 * @param y the y position of where the rifle will be drawn
	 * @param width the width of the rifshape
	 * @param height the height of the rifshape
	 */
	public RifShape(int type,int x, int y, int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}
	
	/*
	 * Moves the rifshape to a specific x and y
	 * @param xx the x location to be moved to 
	 * @param yy the y location to be moved to
	 * @see MoveableShape#moveTo(int, int)
	 */
	public void moveTo(int xx, int yy){
		x = xx;
		y = yy;
	}
	
	/*
	 * Moves the rifshape 
	 * @param dx the change in x value 
	 * @param dy the change in y value
	 * @see MoveableShape#move(int, int)
	 */
	public void move(int dx, int dy){
		x += dx;
		y += dy;
	}
	
	/*
	 * Gets the x position of the rifshape
	 * @return the x position
	 */
	public int getX(){
		return this.x;
	}
	
	/*
	 * Gets the y position of the rifshape
	 * @return the y position
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Draws the rifshape to a graphics2d object based on which type of gun should be drawn
	 * @param g2 the graphics2d object to be drawn to
	 * @see MoveableShape#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g2){
		if(type == 1){
			//draws a rifle
			g2.setColor(Color.black);
			Rectangle2D.Double stock = new Rectangle2D.Double(x-(width/2),y+(height/2),width*2,height/2);
			g2.setColor(new Color(122,75,13));
			g2.fill(stock);
			g2.draw(stock);
			Rectangle2D.Double barrel = new Rectangle2D.Double(x,y,width,height);
			g2.setColor(Color.black);
			g2.fill(barrel);
			g2.draw(barrel);
		}
		if(type == 2){
			//draws a shotgun
			g2.setColor(Color.black);
			Rectangle2D.Double stock = new Rectangle2D.Double(x-(width/2),y+(height/2),width*2,height/2);
			g2.setColor(new Color(122,75,13));
			g2.fill(stock);
			g2.draw(stock);
			Rectangle2D.Double barrel = new Rectangle2D.Double(x,y,width/2,height);
			Rectangle2D.Double barrelR = new Rectangle2D.Double(x+5,y,width/2,height);
			g2.setColor(Color.black);
			g2.fill(barrel);
			g2.fill(barrelR);
			g2.draw(barrelR);
			g2.draw(barrel);
			
		}
	}
	
	/*
	 * Creates a collider to be used to tell if the rifshape hits other shapes
	 * @return Rectangle2D object used to detect collisions
	 * @see MoveableShape#collider()
	 */
	public Rectangle2D collider(){
		Rectangle2D.Double collider = new Rectangle2D.Double(this.x,this.y,this.width, this.height);
		return collider;
	}
	
	//Declares variables
	private int x;
	private int y;
	private int width;
	private int height;
	private int type;
}
