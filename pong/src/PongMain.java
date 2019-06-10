
import processing.core.PApplet;
import processing.core.PFont;

public class PongMain extends PApplet {
	final static public int SCR_W = 1000;
	final static public int SCR_H = 600;
	final static public int frameRate = 50;
	public static final int PAD_H = 150;
	public static final int PAD_W = 20;
	public static final int PADDING = 30;
	public static final int PAD_Speed = 15;
	public static final int Ball_Speed = 10;
	public static final int Winningscore = 5;
	
	private float tmpSpeed = 10.0f;
	
	Ball theBall;
	Paddle lPad;
	Paddle rPad;
	Obstacle obstacle0, obstacle1, obstacle2, obstacle3;
	Obstacle[] obstacles = new Obstacle[4];
	
	public static void main(String[] args) {
		PApplet.main("PongMain");
		/**PongMain run = new PongMain();
		Thread thread = new Thread(run);
		thread.start();**/
	}

	public void settings(){
		size(SCR_W, SCR_H);
    }

    public void setup(){
    	frameRate(frameRate);
    	theBall = new Ball(SCR_W/2, SCR_H/2,30,Ball_Speed,PI/6,255,45,60);
    	PFont f= createFont("Verdana", 16, true);
    	lPad = new Paddle(PADDING, SCR_H/2,PAD_W, PAD_H, PAD_Speed,'w','s',255,43,80,0,f);
    	rPad = new Paddle(SCR_W-PADDING,SCR_H/2, PAD_W, PAD_H, PAD_Speed,'i','k',50,79,80,0,f);
    	
    	int[] color = {255, 45, 60};
    	obstacles[0] = new Obstacle(SCR_W/6.0f, SCR_H/5.0f, 35.0f, Math.random() * 10.0f, color);
    	obstacles[1] = new Obstacle(2.0f * SCR_W/6.0f, 2.0f * SCR_H/5.0f, 35.0f, Math.random() * 10.0f, color);
    	obstacles[2] = new Obstacle(4.0f * SCR_W/6.0f, 3.0f * SCR_H/5.0f, 35.0f, Math.random() * 10.0f, color);
    	obstacles[3] = new Obstacle(5.0f * SCR_W/6.0f, 4.0f * SCR_H/5.0f, 35.0f, Math.random() * 10.0f, color);
    }
   
    /* Run once for every frame */
    public void draw(){
    	if (lPad.getScore() < Winningscore && rPad.getScore() < Winningscore) {
    		lPad.update();
	    	rPad.update();
	    	background(0);
	    	theBall.draw(this);
	    	theBall.update();
	    	theBall.checkCollision(lPad, rPad);
	    	lPad.draw(this);
	    	rPad.draw(this);
	    	for (Obstacle o : obstacles) {
	    		theBall.collideObstacle(o);
	    		o.draw(this);
	    	}
    	} else {
    		PFont f= createFont("Verdana", 16, true);
    		this.textFont(f, 46);
    		this.fill(255);
    		this.text("Game is ended! Try again?", SCR_W/2-300, SCR_H/2);
    		this.text("Press R to restart!", SCR_W/2-200, SCR_H/2+100);
    	}
    	rPad.bot(theBall);
    }
    
    //The keyPressed() function is called once every time a key is pressed. 
    //The key that was pressed is stored in the key variable. 
    public void keyPressed() {
    	lPad.handlePress(key);
    	rPad.handlePress(key);
    	if(key == 'r') {
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		this.setup();
    	}
    	
    	if (key == ' ') {
    		if (theBall.getSpeed() != 0.0f) {
    			this.tmpSpeed = theBall.getSpeed();
    			theBall.setSpeed(0.0f);
    		} else {
    			theBall.setSpeed(this.tmpSpeed);
    		}
    	}
    }
    
    public void keyReleased() {
    	lPad.handleRelease(key);
    	rPad.handleRelease(key);
    }

	/**@Override
	public void run() {
		rPad.bot(theBall);
		// TODO Auto-generated method stub
		
	}**/
}