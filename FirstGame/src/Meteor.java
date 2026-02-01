import java.awt.Color;
import java.awt.Graphics2D;

public class Meteor {
	public Meteor(int radius, double angle) {
		this.radius = radius; 
		this.angle = angle;
	}
	
	int radius;
	double angle;
	double speed = 0.0005;
	double angularSpeed;
	int x;
	int y;
	public boolean alive = false;
	public boolean good;
	static Color goodColor = new Color(160, 205, 250);
	static Color badColor = new Color(110, 110, 110);

	
// =========================== Logical ===============================
	
	public void Update() {
		if (!alive) return; //don't update inactive meteors
		
		//change polar coordinates based on speed, then update Cartesian coordinates
		this.radius -= speed;
		this.angle += angularSpeed;
		x = (int) (this.radius * Math.cos(angle) + 400);
		y = (int) (this.radius * Math.sin(angle) + 400);
		
		//check for collisions
		this.Collide();
	}
	
	public void Collide()
	{
		/*for all planets, check if the meteor is within the planet's radius. 
		if the meteor is good, add health, and vice versa */
		for (Planet p : GameLogic.planets) {
			if(!p.active) continue;
			if(Math.hypot(p.x - this.x, p.y - this.y) <= p.size/2 && this.alive) {
				alive = false;
				GameLogic.score++;
				if (this.good)
					p.health++;
				else
					p.health--;
				break;
			}
		}
		//if the meteor hits the sun, deactivate
		if (this.radius <= 25 && this.alive) {
			this.alive = false;
			GameLogic.score++;
		}
	}
	
	
	public static void Create(Meteor[] meteors) {
		/*iterate through the list of meteors, and find an inactive meteor slot
		 Create a meteor in this slot with semi-random traits*/
		for (Meteor m : meteors) {
			if(!m.alive) {
				m.radius = 500;
				m.speed = Math.random()*0.01;
				m.angle = Math.random()*2*Math.PI;
				m.angularSpeed = Math.random()*0.01;
				m.x = 1000;
				m.y = 1000;
			
				if (Math.random() >= 0.9)
					m.good = true;
				else
					m.good = false;
				
				m.alive = true;
				break;
			}
		}
	}
	
// =============================== Visual =============================
	
	public void Draw(Graphics2D g) {
		if (!this.alive) return; // don't draw inactive meteors
		
		// change color based on if good or bad meteor
		if (this.good)
			g.setColor(goodColor);
		else 
			g.setColor(badColor);
		
		g.fillOval(x - 5, y - 5, 10, 10);
	}
}
