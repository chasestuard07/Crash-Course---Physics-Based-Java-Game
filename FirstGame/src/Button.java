import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Button {
	public Button (int x, int y, int level) {
		this.x = x;
		this.y = y;
		this.level = level;
	}
	
	//location and size variables
	int x;
	int y;
	static int width = 220;
	static int height = 100;
	
	int level; //level the button leads to 
	boolean isHovering; //used to change appearance when hovered over by mouse

// ================= Logical =======================
	
	public void ClickDetection(int mouseX, int mouseY) {
		if (mouseX >= this.x && mouseX <= this.x + width && mouseY >= this.y && mouseY <= this.y + height) {
			if (GameLogic.level == 5) {
				GameLogic.reset();
			}
			
			GameLogic.level = this.level;
			
		}
	}
	
	public void HoverDetection(int mouseX, int mouseY)  {
		if (mouseX >= this.x && mouseX <= this.x + width && mouseY >= this.y && mouseY <= this.y + height) {
			isHovering = true;
		}
		else
			isHovering = false;
	}
	
// ================== Visual =======================
	public void Render(Graphics2D g) {
		g.setStroke(new BasicStroke(5f));
		g.setColor(new Color(190, 180, 125));
		g.drawRect(this.x + 4, this.y + 4, width, height);
		g.setColor(new Color(240, 230, 175));
		g.drawRect(this.x, this.y, width, height);
		if (isHovering)
			g.fillRect(this.x, this.y, width, height);

	}
}
