import java.awt.*;
import java.awt.geom.*;

/*
 * Creates a circle to represent a tree
 */
public class Tree implements MoveableShape {
	/*
	 * Constructs the tree object
	 * @param type the type of tree to be drawn
	 * @param x the x position of the tree
	 * @param y the y position of the tree
	 * @param width the width of the tree
	 * @param height the height of the tree
	 */
	public Tree(int type,int x, int y, int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}
	
	/*
	 * Moves the tree to an x an y location only included because it is a MoveableShape
	 * @param xx the x location to move to
	 * @param yy the y position to move to
	 * @see MoveableShape#moveTo(int, int)
	 */
	public void moveTo(int xx, int yy){
		x = xx;
		y = yy;
	}
	
	/*
	 * Moves the tree
	 * @param dx the change in the x positioning
	 * @param dy the change in the y positioning
	 * @see MoveableShape#move(int, int)
	 */
	public void move(int dx, int dy){
		x += dx;
		y += dy;
	}
	
	/*
	 * Returns the x position of the tree
	 * @return x position
	 */
	public int getX(){
		return this.x;
	}
	
	/*
	 * Returns the y position of the tree
	 * @return y position
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Draws the tree to a graphics2d object
	 * @param g2 the graphics2d object to be drawn to
	 * @see MoveableShape#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g2){
		if(this.type== 1){
			//draws a tree
		g2.setColor(new Color(40,158,11));
		}
		if(this.type==2){
			//draws a rock
			g2.setColor(new Color(144,153,158));
		}
		Ellipse2D tree = new Ellipse2D.Double(x,y,width, width);
		g2.fill(tree);
		g2.draw(tree);
	}
	
	/*
	 * Creates a collider to detect if things hit the tree
	 * @return a rectangle2d collider
	 * @see MoveableShape#collider()
	 */
	public Rectangle2D collider(){
		Rectangle2D.Double collider = new Rectangle2D.Double(this.x,this.y,this.width, this.height);
		return collider;
	}
	
	//Declares variables used
	private int x;
	private int y;
	private int width;
	private int height;
	private int type;
}
