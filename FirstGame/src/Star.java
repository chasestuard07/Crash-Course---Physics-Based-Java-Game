import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.Math;


public class Star {
	public Star(int radius, double angle) {
		this.radius = radius; 
		this.angle = angle;
	}
	
	//position and speed variables
	int radius;
	double angle;
	static double speed = 0.0005;

// ======================= Logical =========================
	
	public void Update() {
		this.angle += speed; //change angle by angular speed
		
		//if the angle is greater than 2*PI, reduce by 2*PI (very small optimization + reduces floating point errors)
		if(this.angle >= 2*Math.PI) {
			this.angle -= 2*Math.PI;
		}
	}
	
// ======================= Visual ============================
	
	public void Draw(Graphics2D g) {
		//define the visual Cartesian coordinates from the polar coordinates
		int x = (int) (this.radius * Math.cos(angle) + 400);
		int y = (int) (this.radius * Math.sin(angle) + 400);
		
		//draw the star
		g.setColor(new Color(250, 230, 175));
		g.fillOval(x, y, 2, 2);
	}
}
