import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
	public Image img; 	
	public AffineTransform tx;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1;		 //change to scale image
	double scaleHeight = 1; //change to scale image
	
	int currentScore; //stores score of the game

	//constructor
	public Background(String filename, int x, int y, int vx, int vy, double d, double e) {
		img = getImage(filename); //load the image
		this.x = x; 
		this.y = y; 
		
		this.vx = vx; 
		this.vy = vy; 
		
		scaleWidth = d; //initialize variables
		scaleHeight = e;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				
		
		
		
		
	}
	
	//method that returns the score for Frame
	public int getScore() {
		return currentScore; 
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName); // load the (new) image
		init(0, 0);
	}
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		

		
		x+=vx;
		y+=vy;
		
	

		init(x,y);
		g2.drawImage(img, tx, null);

	}
	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
