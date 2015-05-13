import java.awt.*;
import java.awt.geom.*;

/*
 * Constructs a red circle to be drawn to a graphics2d object representing blood
 */
public class Blood implements MoveableShape {
	/*
	 * Constructs the Blood object 
	 * @param x the x position of the blood to be drawn at
	 * @param y the y position of the blood to be drawn at
	 * @param width the width of the blood
	 */
	public Blood(int x, int y, int width){
		this.x = x;
		this.y = y;
		this.width = width;
	}
	
	/*
	 * A method to move the blood to a location
	 * @param xx the x position to move the blood to
	 * @param yy the y position to move the blood to
	 * @see MoveableShape#moveTo(int, int)
	 */
	public void moveTo(int xx, int yy){
		x = xx;
		y = yy;
	}
	
	/*
	 * A method to move the blood a certain amount
	 * @param dx the change in x to be moved
	 * @param dy the change in y to be moved
	 * @see MoveableShape#move(int, int)
	 */
	public void move(int dx, int dy){
		this.x += dx;
		this.y += dy;
	}
	
	/*
	 * Draws the blood to a graphics2d object
	 * @param g2 the graphics2d object to be drawn to
	 * @see MoveableShape#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.red);
		Ellipse2D blood = new Ellipse2D.Double(x,y,width, width/2);
		g2.fill(blood);
		g2.draw(blood);
	}
	
	/*
	 * Returns the y value of where the blood is located
	 * @return the y value of the blood
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Collider is not used for blood but is implemented in the MoveableShape abstract class
	 * so it must be included here
	 * @return null
	 * @see MoveableShape#collider()
	 */
	public Rectangle2D collider(){
		return null;
	}
	
	//Declare the variables used
	private int x;
	private int y;
	private int width;
}