import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Frame extends JFrame implements ActionListener, KeyListener{
	
	Timer t = new Timer(100, this);
	
	int width = 1000;
	int height = 750;
	private int p = 0;
	private boolean change = true; //indicates whether the character's frames should change or not
	public boolean dead = false; //stores whether the game is over or not
	int activeScore; 
	
	//object declarations
	Background b = new Background("Background.png",0,0,0,0,1,1); 
	Platform main = new Platform("Platform.png",0,750-75,0,0,2,1); //log png #1
	Platform main2 = new Platform("Platform.png", 1000, 750-75,0,0,2,1); //log png #2
	Character firstFrame = new Character("animation1.png", 0, 750-75-150,0,0,1.25,1.25); //first frame of character 
	Character secondFrame = new Character("animation2.png", -90, 750-75-150,0,0,1.25,1.25); //second frame of character
	Turtle turt = new Turtle("turtle.png", 400, 750-75-29, -5, 0,1,1); //turtle!
	
	
	
	
	
	
	//paint the components on to screen
	public void paint(Graphics g) {
		super.paintComponents(g);
		b.paint(g);
		main.paint(g);	
		main2.paint(g);
		turt.paint(g);
		
		
		firstFrame.paint(g); 
		secondFrame.paint(g);
		//testing to see rectangle target boxes for the turtle and character frames
//		g.drawRect(turt.x, turt.y, (int) (40*turt.scaleWidth), (int) ((int)29*turt.scaleHeight));
//		g.drawRect(firstFrame.x, firstFrame.y, 44, 150);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		//score display
		g.drawString("score:" + turt.currentScore, 20, 230);
		
		
		//Rectangle target boxes for turtle and two character frames
		
		Rectangle a = new Rectangle(turt.x, turt.y, (int) (40*turt.scaleWidth), (int) ((int)29*turt.scaleHeight)); 
		 
		 Rectangle z = new Rectangle(firstFrame.x+5, firstFrame.y, 37, 150); 
		 Rectangle c = new Rectangle(secondFrame.x+5, secondFrame.y, 37, 150); 
		 
		 if(a.intersects(z) || a.intersects(c)) { //checks if the rectangles have intersected/touched
			 System.out.println("nice1"); // console display for debugging
			 
			 dead = true; 
			 
			 //changes to end screen when intersection is detected
			 
			 b.changePicture("End Screen.png");
			 
			 //moves turtle off the screen and the chracter frames off the screen
			 
			 turt.vx = 0; 
			 turt.y = 9000; 
			 main.y = -2000; 
			 main2.y = -2000; 
			 firstFrame.x = 40000; 
			 secondFrame.x = 400000; 

		 }
		 
		 
		

	}

	
	public Frame(){
		setTitle("turtle run"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(width, height));
		this.addKeyListener(this);
		setLocationRelativeTo(null);
		t.start();
		setVisible(true);
		
		
		
		
		 
	
	}
	
	
	public static void main(String[] args) {
		Frame f = new Frame();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//recalls paint to update characters' positions, recalls the score for active updates
		repaint();
		activeScore = b.getScore(); 
				
	}
	

	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//RIGHT arrow key press check
		 if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		    {
			 
				//Rectangle target boxes for turtle and two character frames

				
				Rectangle a = new Rectangle(turt.x, turt.y, (int) (40*turt.scaleWidth), (int) ((int)29*turt.scaleHeight)); 
				 
				 Rectangle z = new Rectangle(firstFrame.x+5, firstFrame.y, 37, 150); 
				 Rectangle c = new Rectangle(secondFrame.x+5, secondFrame.y, 37, 150); 
				 
				 if(!dead) {//if alive
				 
			 
			 if(a.intersects(z) || a.intersects(c)) {//if alive and the boxes (character and turtle intersects)
			//	 System.out.println("nice2");
				 
				 dead = true; //dead!
				
				 b.changePicture("End Screen.png"); //changes to end screen
				 
				 turt.vx = 0; //turn off velocity
				 turt.y = 9000; 
				 main.y = -2000; 
				 main2.y = -2000; 
				 firstFrame.y = 40000; 
				 secondFrame.y = 400000; 
				 firstFrame.setPlace = 10000; //move them off the screen
				 secondFrame.setPlace = 2000; 
				 
				 }else {
			 
					//	System.out.println("hi");
				         main.x-=5; //if it's not a collision, then pressing on the right arrow key will simply move the
				        main2.x-=5; //platforms (main and main2) a little to the left. This simulates that the character is moving to the right
				        turt.x-=5; //turtle's x is also moving to the left in order to match physics laws 
				        			//if player is moving to the right, then the turtle should be approaching faster
				     
			
				    //if either of the platforms (main or main2) is off the screen to the left, 
				        //then it will move to the right at 1000 so it can cycle through 
				        //again when the right arrow key is pressed. 
			 if(main.x<=-999) {
		        	 main.x = 1000; 
		         }
		         if(main2.x<=-999) {
		        	 main2.x = 1000; 
		         }
		         
		         
		         
		         if(change) {//if change is true, then the character's firstFrame needs to change 
		        	 firstFrame.x = -100; // true change indicates that firstFrame is currently being used, so we will move 
		        	  secondFrame.x = 50; //firstFrame off the screen and then replace it with secondFrame. 
		        	
		        	
		        	 change = false; //now change the change to false!
		         }else {
		        	 secondFrame.x = -100; //since change is false, this means that secondFrame is currently being shown within the window
		        	 firstFrame.x = 50; //move secondFrame off the screen and firstFrame back on screen
		        	 change = true; //change boolean so it can change again when you press the right arrow key
		         }
		         
		        
				 }
			 //check if the character is in the air. if true, then allow character to move right 40 pixels
			 if(firstFrame.y< 750-75-150|| secondFrame.y< 750-75-150) {
				 	firstFrame.x+=40; 
				 	secondFrame.x+=40; 
				 }
			 
		    }
		    }
		 
		 //if up arrow key is pressed, then it will move the firstFrame of secondFrame to 100. This triggers Character class's y position check
		 //so vy is now activated
		 if(e.getKeyCode()==KeyEvent.VK_UP) {
			 
		
			 
			 
			 
		
				 System.out.println("Yay");
				 firstFrame.y = 100;
				 secondFrame.y = 100; 
				 
		 
		
		 
		 }
		 
		 
		 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
