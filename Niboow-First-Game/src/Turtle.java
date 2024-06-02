import java.awt.Graphics;
import java.awt.Graphics2D;

public class Turtle extends Background {
	
	public Turtle(String filename, int x, int y, int vx, int vy, double d, double e) {
		super(filename, x, y, vx, vy, d, e);
		// TODO Auto-generated constructor stub
	}
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		

		//adds velocities to x and y of turtle
		x+=vx;
		y+=vy;
		//since the turtle has a velocity and moves, this says that if the turtle
		//is out of the screen frame (the character didn't collide with the turtle)
		//then it will add a score
		if(this.x<-50) {
			this.x = 1100; 
			this.vx-=2; 
			currentScore++; //score variable in Background class
		//	System.out.println(currentScore);
			
			double scale = Math.random()*3+1; //randomizes the next turtle's size
			this.scaleWidth = scale; //sets the randomized double scale to be 
			this.scaleHeight = scale; //the turtle object's width and height scales as well
			this.y = 750-75-29*(int)scale; //new y 
		}
		
		
		
			

		init(x,y);
		g2.drawImage(img, tx, null);

	}

}
