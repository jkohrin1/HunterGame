import java.awt.*;
import java.awt.geom.*;

/*
 * Constructs a shape of a hunter 
 */
public class HunterShape implements MoveableShape {
	/*
	 * Constructs a hunter shape
	 * @param x the x position of the hunter
	 * @param y the y position of the hunter
	 * @param width the width of the hunter shape
	 */
	public HunterShape(int x, int y, int width){
		this.x = x;
		this.y = y;
		this.width = width;
	}
	
	/*
	 * Moves the huntershape to a specific x and y location
	 * @param xx the x position to be moved to 
	 * @param yy the y position to be moved to
	 * @see MoveableShape#moveTo(int, int)
	 */
	public void moveTo(int xx, int yy){
		x = xx;
		y = yy;
	}
	
	/*
	 * Moves the hunter shape 
	 * @param dx the change in x value
	 * @param dy the change in y value
	 * @see MoveableShape#move(int, int)
	 */
	public void move(int dx, int dy){
		x += dx;
		y+= dy;
	}
	
	/*
	 * Returns the x position of the hunter shape
	 * @return x value of huntershape
	 */
	public int getX(){
		return this.x;
	}
	
	/*
	 * Returns the y position of the hunter shape
	 * @return y value of huntershape
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Draws the huntershape to a graphics2D object
	 * @param g2 the graphics2d object to be drawn to
	 * @see MoveableShape#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.black);
		Ellipse2D.Double head = new Ellipse2D.Double(x,y+width/2,width,width);
		Line2D.Double body = new Line2D.Double(new Point(x+width/2,y+width+width/2),new Point(x+width/2,y+width+60));
		Line2D.Double rightArm = new Line2D.Double(new Point(x+width/2,y+width+30), new Point(x+width/2+30,y+width+30));
		Line2D.Double leftArm = new Line2D.Double(new Point(x+width/2,y+width+30), new Point(x+width/2+30,y+width+15));
		g2.fill(head);
		g2.draw(head);
		g2.setColor(Color.orange);
		g2.fill(body);
		g2.draw(body);
		g2.setColor(Color.black);
		g2.draw(rightArm);
		g2.draw(leftArm);
	}
	
	/*
	 * Huntershape doesnt need a collider but it is included because moveable shape
	 * abstract class has one. 
	 * @return null
	 * @see MoveableShape#collider()
	 */
	public Rectangle2D collider(){
		return null;
	}
	
	//declares variables
	private int x;
	private int y;
	private int width;
}
