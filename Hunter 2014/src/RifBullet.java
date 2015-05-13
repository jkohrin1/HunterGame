import java.awt.*;
import java.awt.geom.*;

/*
 * A class to make a bullet and draw it to the screen
 */
public class RifBullet implements MoveableShape {
	/*
	 * Constructs the bullet 
	 * @param type the type of bullet to be made
	 * @param x the x location of the bullet
	 * @param y the y location of the bullet
	 * @param width the width of the bullet
	 */
	public RifBullet(int type, int x, int y, int width){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = width;
		this.type = type;			//The type of bullet
		this.bulSpeed = 6 - type;	//The speed the bullet will move
	}
	
	/*
	 *A method to move the bullet to a specific x y location
	 *@param xx the x position to be moved to
	 *@param yy the y position to be moved to
	 * @see MoveableShape#moveTo(int, int)
	 */
	public void moveTo(int xx, int yy){
		x = xx;
		y = yy;
	}
	
	/*
	 * Updates the position of the bullet based on its speed
	 * @param g2 the graphics2d object to draw the updated bullet on
	 */
	public void update(Graphics2D g2){
		y -= bulSpeed;
		if(this.getY() > 0){
			this.draw(g2);
		}
		if(this.getY() < 0){
			this.y = 600;
		}
	}
	
	/*
	 * Moves the bullet 
	 * @param dx change in x location
	 * @param dy change in y location
	 * @see MoveableShape#move(int, int)
	 */
	public void move(int dx, int dy){
		x += dx;
		y+= dy;
	}
	
	/*
	 * sets the X position of the bullet
	 * @param fx the x position to be moved to
	 */
	public void setX(int fx){
		x = fx;
	}
	
	/*
	 * sets the Y position of the bullet
	 * @param fy the y position to be moved to
	 */
	public void setY(int fy){
		y = fy;
	}
	
	/*
	 * Returns the x position of the bullet
	 * @return x position of bullet
	 */
	public int getX(){
		return this.x;
	}
	
	/*
	 * Returns the y position of the bullet
	 * @return y position of bullet
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Draws the bullet type to a graphics2d object
	 * @param g2 the graphics2d object to draw the bullet to
	 * @see MoveableShape#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g2){
		if(type == 1){
			//Draws a rifle
			g2.setColor(Color.black);
			Ellipse2D.Double bullet = new Ellipse2D.Double(x,y+width/2,width,width);
			g2.setColor(Color.yellow);
			g2.fill(bullet);
			g2.draw(bullet);
			this.damage = 30;
		}
		if(type == 2){
			//Draws a shotgun
			g2.setColor(Color.black);
			Ellipse2D.Double bullet1 = new Ellipse2D.Double(x-7,y+width/2,width,width);
			Ellipse2D.Double bullet2 = new Ellipse2D.Double(x,y+width/2,width,width);
			Ellipse2D.Double bullet3 = new Ellipse2D.Double(x+7,y+width/2,width,width);
			g2.setColor(Color.yellow);
			g2.fill(bullet1);
			g2.fill(bullet2);
			g2.fill(bullet3);
			g2.draw(bullet1);
			g2.draw(bullet2);
			g2.draw(bullet3);
			this.damage = 20;
		}
	}
	
	/*
	 * Gets the damage the bullet does
	 * @return damage
	 */
	public int getDamage(){
		return damage;
	}
	
	/*
	 *Creates a collider to use to see if the bullet intersects other shapes
	 *@return Rectangle2D object to be used as a collider
	 * @see MoveableShape#collider()
	 */
	public Rectangle2D collider(){
		if(type == 1){
		Rectangle2D.Double collider = new Rectangle2D.Double(this.x,this.y,this.width,this.height);
		return collider;
		}
		else{
		Rectangle2D.Double collider = new Rectangle2D.Double(this.x,this.y,this.width+14,this.height);
		return collider;
		}
	}
	
	
	//Declares variables
	private int x;
	private int y;
	private int width;
	private int height;
	private int damage;
	private int type;
	private int bulSpeed;
}