import processing.core.PApplet;

public class Ball {
	private float x, y, diameter, speed;
	private float direction;
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
	
	public float getY() {
		 return y;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getSpeed() {
		return this.speed;
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
		    	return;
		 }
		 
		 if(x + diameter/2 >= PongMain.SCR_W) {
			 rPad.increScore();
		    	reset();
		    	return;
		 }
		 
		 if (y <= diameter/2 || y+diameter/2 >= PongMain.SCR_H)
		    direction = -direction;
		    
		if(x <PongMain.SCR_W/2) {
			if(x-diameter/2 <= lPad.getX() + PongMain.PAD_W/2) {
				if(y<=lPad.getY()+ PongMain.PAD_H/2 && y>=lPad.getY()-PongMain.PAD_H/2) {
					x = lPad.getX() + PongMain.PAD_W/2 + diameter/2;
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
	
	public void collideObstacle(Obstacle obstacle) {
		float distance = (float) Math.sqrt(Math.pow(this.x - obstacle.getX(), 2) + Math.pow(this.y - obstacle.getY(), 2));
		if (distance < (this.diameter + obstacle.getDiameter())) {
			this.direction = (float) (PApplet.PI - Math.atan2(obstacle.getY() - this.y, obstacle.getX() - this.x));
		}
	}
}
