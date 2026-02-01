import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Planet {
	public Planet(int radius, double angle, int size, Color color, double ambientSpeed) {
		this.radius = radius;
		this.angle = angle;
		this.size = size;
		this.color = color;
		this.ambientSpeed = ambientSpeed;
		this.speed = ambientSpeed;
	}
	
	int radius;
	double angle;
	int x; 
	int y;
	int size;
	Color color;
	double speed;
	double ambientSpeed;
	static double acceleration = 0.0001;
	static int mouseBuffer = 10;
	boolean active = true;
	int health = 3;
	
// ======================== Logical ===============================
	
	public void Update() {
		if (!active) return;
		//change angle based on speed
		this.angle += this.speed;
		
		//keeps angle <= 2PI (small optimization and reduces floating point error)
		if(this.angle >= 2*Math.PI) {
			this.angle -= 2*Math.PI;
		}
		
		//redefine Cartesian coordinates based on polar coordinates
		x = (int) (this.radius * Math.cos(angle) + 400);
		y = (int) (this.radius * Math.sin(angle) + 400);
		
		mouseDetection();
		
		//adjust speed to approach the ambient speed of the planet
		double speedDif = speed - ambientSpeed;
		if (speedDif > 0) {
			speed -= acceleration;
		}
		if (speedDif < 0) {
			speed += acceleration;
		}
		
	}
	
	private void mouseDetection() {
		double mouseDistance = Math.hypot(PlayerInput.MouseX - this.x,  PlayerInput.MouseY - this.y);

		//if the player is holding onto a planet, the speed is 0
		if(PlayerInput.isHeld && mouseDistance <= this.size + mouseBuffer) {
			this.speed = 0;
		}
		
		//if the player throws the planet, angular speed changes according to vector of the throw
		mouseDistance = Math.hypot(PlayerInput.dragX - this.x,  PlayerInput.dragY - this.y); 
		if(PlayerInput.justReleased && mouseDistance <= this.size + mouseBuffer) {
			double tangentAngle = angle + Math.PI/2;
			this.speed += Math.cos(PlayerInput.throwAngle - tangentAngle) * PlayerInput.throwMagnitude * 0.01;
		}
	}
	
// ===================== Visual ==========================
	
	public void Draw(Graphics2D g) {
		if (!active) return;
		//draw the orbit 
		float[] dashPattern = {2f, 10f};
		g.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10f, dashPattern, 0f));
		g.setColor(new Color(250, 230, 175));
		g.drawOval(400- this.radius, 400- this.radius, this.radius*2, this.radius*2);
		
		//draw the planet
		Color shadedColor = new Color (color.getRed() - 50, color.getGreen() - 50, color.getBlue() - 50);
		g.setColor(shadedColor);
		g.fillOval(x-size/2 + 3, y-size/2 + 3, size, size);
		g.setColor(color);
		g.fillOval(x-size/2, y-size/2, size, size);
	}
}
