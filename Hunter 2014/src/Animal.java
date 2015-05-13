import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/*
 * A class that makes a Moveable animal shape
 */
public class Animal implements MoveableShape {
	
	/*Constructs the animal shape that can be drawn on a graphics2S object
	 * @param type that determines which animal shape should be made
	 * @param x to note the x position the shape should be drawn at
	 * @param y to note the y position the shape should be drawn at
	 * @param width determines the width of the shape to be drawn
	 * @param height determines the height of the shape to be drawn
	 * */
	public Animal(int type, int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		
		//Sets the health of each animal based on which type it is
		if(type == 1){
			this.health = 30;
		}
		if(type == 2){
			this.health = 60;
		}
		if(type == 3){
			this.health = 15;
		}
	}
	
	/*
	 * Allows the MoveableShape to be moved directly to an x,y position
	 * @param xx is the desired x location to move to
	 * @param yy is the desired y location to move to
	 * @see MoveableShape#moveTo(int, int)
	 */
	public void moveTo(int xx, int yy){
		this.x = xx;
		this.y = yy;
	}
	
	/*
	 * Moves the shape a random amount up and down to simulate movement 
	 * @param g2 the graphics that the updated shape should be drawn to
	 */
	public void update(Graphics2D g2){
		
		//Changes the animals movement based on the type of animal it is
		if(type == 1){
			int ranx = randInt(0,2);
			this.x = x+ranx;
			int rany = randInt(-3,3);
			this.y = y+rany;
			}
		if(type == 2){
			int ranx = randInt(0,1);
			this.x = x + ranx;
			int rany = randInt(-2,2);
			this.y = y+rany;
			}
		if(type == 3){
			int ranx = randInt(-3,3);
			this.x = x + ranx;
			this.y = this.y-1;
		}
	}
	
	/*
	 * A method to move the shape
	 * @param dx to represent the change in the x value
	 * @param dy to represent the change in the y value
	 * @see MoveableShape#move(int, int)
	 */
	public void move(int dx, int dy){
		x += dx;
		y+= dy;
	}
	
	/*
	 * A method to set the x value to a certain position
	 * @param fx the value to set x to
	 */
	public void setX(int fx){
		x = fx;
	}
	
	/*
	 * A method to set the y value to a certain position
	 * @param fy the value to set y to
	 */
	public void setY(int fy){
		y = fy;
	}
	
	/*
	 * Returns the x position of the shape
	 * @return the x position
	 */
	public int getX(){
		return this.x;
	}
	
	/*
	 * Returns the y position of the shape
	 * @return the y position
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Returns the width of the shape
	 * @return the width of the shape
	 */
	public int getWidth(){
		return this.width;
	}
	
	/*
	 * Returns the height of the shape
	 * @returns the height of the shape
	 */
	public int getHeight(){
		return this.height;
	}
	
	/*
	 * Draws the shape to a graphics2d object
	 * @param g2 the graphics2D object to be drawn to
	 * @see MoveableShape#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g2){
		if(this.type == 1){
		Image img = Toolkit.getDefaultToolkit().getImage("src/smalldeer.png");
		g2.drawImage(img,x,y,null);
		}
		if(this.type == 2){
		Image img2 = Toolkit.getDefaultToolkit().getImage("src/smalllion.png");
		g2.drawImage(img2, x, y,null);
		}
		if(this.type == 3){
			Image img2 = Toolkit.getDefaultToolkit().getImage("src/pheas.gif");
			g2.drawImage(img2, x, y,null);
		}
	}
	
	/*
	 * Draws a shape of the animal killed to a graphics2d object
	 * @param g2 the graphics2d object to be drawn to
	 */
	public void drawKilled(Graphics g2){
		if(this.type == 1){
		Image img = Toolkit.getDefaultToolkit().getImage("src/dead.png");
		g2.drawImage(img,x,y,null);
		}
		if(this.type==2){
			Image img2 = Toolkit.getDefaultToolkit().getImage("src/deadlion.png");
			g2.drawImage(img2,x,y,null);
		}
		if(this.type == 3){
			Image img2 = Toolkit.getDefaultToolkit().getImage("src/rsz_deadpheas.png");
			g2.drawImage(img2, x, y,null);
		}
	}
	
	/*
	 * Creates a collider to tell if the shape is hitting another shape
	 * @return a Rectangle2D object to use to identify collisions
	 * @see MoveableShape#collider()
	 */
	public Rectangle2D collider(){
		Rectangle2D.Double collider = new Rectangle2D.Double(this.x,this.y,this.width,this.height);
		return collider;
	}
	
	/*
	 * Returns the current health of the animal
	 * @return health of animal
	 */
	public int health(){
		return this.health;
	}
	
	/*
	 * Subtracts the amount of damage from the health of the animal
	 * @param damage is the amount of damage the bullet will do to the animal
	 */
	public void hit(int damage){
		this.health= this.health - damage;
	}
	
	/*
	 * Creates a random integer in a specified range and returns it
	 * @return a random number in the range
	 * @param min the minimum number to be chosen at random
	 * @param max the maximum number to be chosen at random
	 */
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	//Declares all the variables to be used
	private int x;
	private int y;
	private int width;
	private int height;
	private int health;
	private int type;
}

