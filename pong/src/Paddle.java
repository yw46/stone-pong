import processing.core.PApplet;
import processing.core.PFont;

public class Paddle {
	private char upKey, downKey;
	private boolean upDown, downDown;
	private float x,y,width,height,speed;
	private int r,g,b; //colors
	private int score;
	private PFont scoreFont;
	
	public Paddle(float x, float y, float w, float h, float s, char up, 
			char down, int r, int g, int b, int score, PFont f) {
		scoreFont = f;
		this.x = x;
		this.y = y;
		upKey = up;
		downKey = down;
		width = w;
		height = h;
		speed = s;
		this.r = r;
		this.g = g;
		this.b = b;
		score = 0;
	}
	
	public void draw(PApplet p) {
		p.rectMode(PApplet.CENTER);
		p.noStroke();
		p.fill(r, g, b);
		p.rect(x, y, width, height);
		
		p.textFont(scoreFont, 46);
		p.fill(255);
		p.text(score, PongMain.SCR_W-x-15, 50);
	}
	
	public void handlePress(char key) {
		if(key == upKey)
			upDown = true;
		else if (key == downKey) 
			downDown = true;
	}
	
	public void handleRelease(char key) {
		if(key == upKey) {
			upDown = false;
			speed = 15;
		}
		else if (key == downKey) {
			downDown = false;
			speed = 15;
		}
	}
	
	public void update() {
    	if(upDown) {
    		y = (float) (y - speed * 1.1);
    	}else if(downDown) {
    		y = (float) (y + speed * 1.1);
    	}
    	if(this.y >= PongMain.SCR_H - this.height/2) {
    		this.y = PongMain.SCR_H - this.height/2;
    	}
    	if(this.y <= 0 + this.height/2) {
    		this.y = 0 + this.height/2;
    	}
    }
	
	public void bot(Ball theBall){
		if( theBall.getY() < y){
	        y = y - speed;
	    }else{
	        y = y + speed;
	    }
	}
	
	public float getX() {
		 return x;
	}

	public float getY() {
		 return y;
	}
	
	public void increScore() {
		 score++;
	}
	
	public int getScore() {
		 return score;
	}
}
