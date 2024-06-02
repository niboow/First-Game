import java.awt.Graphics;
import java.awt.Graphics2D;

public class Character extends Background{
	
	int vy = 0; 
	int setPlace = 750-75-150; 

	public Character(String filename, int x, int y, int vx, int vy, double d, double e) {
		super(filename, x, y, vx, vy, d, e);
		// TODO Auto-generated constructor stub
		
	
		
		
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		

		
		x+=vx;
		y+=vy;
		
		//checks if character is in the air or not
		
		if(this.y>=750-75-170) {//only actually triggered when KeyEvent.VK_UP is pressed since it changes the y of character
			
			//if not, set velocity of y to 0 and place character on the platform
			this.vy = 0;
			this.y = setPlace;
			
		}else {
			
			//if yes velocity is set to 30 
			this.vy = 30; 
			
		}
		
		if(this.x>50 && this.y == setPlace) { //if x is character is on the ground but further to the right from starting position
			this.vx = -1; //move it back using velocity
			
		}else {
			this.vx = 0; //else it stays put
		}
		
		
			

		init(x,y);
		g2.drawImage(img, tx, null);

	}
	
	
	
	
	
	
	
}
