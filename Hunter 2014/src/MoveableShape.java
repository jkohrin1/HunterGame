import java.awt.*;
import java.awt.geom.*;

/*
 * A interface that represents a drawable and moveable shape 
 */
public interface MoveableShape {
	
	/*
	 * Draws the moveable shape to the graphics2D object
	 * @param g2 the graphics2d object to be drawn to
	 */
	void draw(Graphics2D g2);
	
	/*
	 * Moves the moveable shape to an x and y location
	 * @param x the x location to be moved to
	 * @param y the y position to be moved to
	 */
	void moveTo(int x, int y);
	
	/*
	 * Changes the location of the moveableshape
	 * @param dx the change in x
	 * @param dy the change in y
	 */
	void move(int dx, int dy);
	
	/*
	 * Returns a collider to determine if a moveable shape has collided with another
	 * moveable shape
	 * @return Rectangle2D object 
	 */
	Rectangle2D collider();
	
}
