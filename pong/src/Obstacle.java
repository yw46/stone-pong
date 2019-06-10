import processing.core.PApplet;

public class Obstacle {
	
	private float x, y, diameter, speed;
	private String direction;
	private Boolean inflate = true;
	int color[] = new int[3];
	
	public Obstacle (float x,float y,float diameter,double d, int color[]) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.speed = (float) d;
		this.color = color;
		this.direction = "down";
	}

	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getDiameter() {
		return this.diameter;
	}
	
	public void draw(PApplet p) {
		if (this.inflate) {
			this.diameter += 1.0f;
		} else {
			this.diameter -= 1.0f;
		}
		
		if (this.diameter >= 50.0f) {
			this.inflate = false;
		} else if (this.diameter <= 20.0f) {
			this.inflate = true;
		}
		
		if (this.direction.equals("down")) {
			if (this.y < PongMain.SCR_H - speed) {
				this.y += speed;
			} else {
				this.y = PongMain.SCR_H - speed;
				this.direction = "up";
			}
		} else {
			if (this.y > speed) {
				this.y -= speed;
			} else {
				this.y = 0;
				this.direction = "down";
			}
		}
		p.noStroke();
		p.fill(this.color[0],this.color[1],this.color[2]);
		p.ellipse(this.x, this.y, this.diameter, this.diameter);
	}
	
	public void update() {
		
	}

}
