import processing.core.PApplet;

public class Ball {
	float x, y, diameter, speed;
	float direction;
	int color[] = new int [3];
	
	public Ball(float x,float y,float diameter,float speed,float direction, int red, int green, int blue) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.speed = speed;
		this.direction = direction;
		color[0] = red;
		color[1] = green;
		color[2] = blue;
	}
	
	public void draw(PApplet p) {
		p.noStroke();
		p.fill(color[0],color[1],color[2]);
		p.ellipse(x, y, diameter, diameter);
	}
	
	public void update() {
		x+= speed * PApplet.cos(direction);
	    y+= speed * PApplet.sin(direction);
		speed += 0.01;
	}
	
	private void reset() {
		y = PongMain.SCR_H/2;
		x = PongMain.SCR_W/2;
		
		int n = (int)(Math.random() * 4);
		if (n == 0) {
			direction = ((float)Math.random()*70 +100);
		}else if (n == 1) {
			direction = (float)Math.random()*70 +100;
		}else if (n == 2) {
			direction = (float)Math.random()*70 +190;
		}else if (n == 3) {
			direction = (float)Math.random()*70 +280;
		}
	}
	
	public void checkCollision(Paddle lPad, Paddle rPad) {
		//if Bleft to the left of LpadRight
			//if  Bally <ytop >ybottom
		 if(x <= diameter/2 ) {
			 lPad.increScore();
		    	reset();
		 }
		 
		 if(x + diameter/2 >= PongMain.SCR_W) {
			 rPad.increScore();
		    	reset();
		 }
		 
		 if (y <= diameter/2 || y+diameter/2 >= PongMain.SCR_H)
		    direction = -direction;
		    
		if(x <PongMain.SCR_W/2) {
			if(x-diameter/2 <= lPad.getX() + PongMain.PAD_W/2) {
				if(y<=lPad.getY()+ PongMain.PAD_H/2 && y>=lPad.getY()-PongMain.PAD_H/2) {
					direction = PApplet.PI - direction;
				}
			}
		} else {
			if(x+diameter/2 >= rPad.getX() - PongMain.PAD_W/2) {
				if(y<=rPad.getY()+ PongMain.PAD_H/2 && y>=rPad.getY()-PongMain.PAD_H/2) {
					direction = PApplet.PI - direction;
				}
			}
		}
	}
}