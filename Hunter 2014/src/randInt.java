import java.util.Random;

/*
 * Class to make a random integer based on a range
 */
	public class randInt {
		/*
		 * Constructs the randInt class
		 */
		public randInt(){
			
		}
		
		/*
		 * a function on a randInt object that returns a random number in the range
		 * @param min the minimum number in the range
		 * @param max the maximum number in the range
		 * @return the random number
		 */
		public int randomInt(final int min, final int max){
	    
	    Random rand = new Random();
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
		}
	}