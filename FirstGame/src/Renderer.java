import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

public class Renderer {
	
	static BufferStrategy bs; 
	
	public static BufferStrategy initRenderer() {
		//create the window
		JFrame window = new JFrame("Attraction");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		//create the canvas 
		Canvas canvas = new Canvas();
		canvas.setSize(800, 800);
		window.add(canvas);
		canvas.requestFocus();
		
		//add listeners to the canvas for PlayerInput
		canvas.addMouseListener(Main.input);
		canvas.addMouseMotionListener(Main.input);
		
		//create the buffer
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		return bs;
	}
	
	public static void draw() {
		//create the graphics object 
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		//draw the background
		g.setColor(new Color(23,21,43));
		g.fillRect(0, 0, 800, 800);
		
		//draw the stars
		for (Star s : GameLogic.stars) 
			s.Draw(g);
		
		//draw the sun 
		for (int i = 0; i <= 50; i += 10) {
		    Color baseColor = new Color(200, 180, 125);
		    Color color = new Color(
		        baseColor.getRed() + Math.min(2*i, 55),
		        baseColor.getGreen() + Math.min(2*i, 75),
		        baseColor.getBlue() + 2*i
		    );

		    g.setColor(color);
		    g.fillOval(400 - (50 - i)/2, 400 - (50 - i)/2, 50 - i, 50 - i);
		}

		
		//draw the planets
		for (Planet p : GameLogic.planets)
			p.Draw(g);
		
		//draw the meteors
		for (Meteor m : GameLogic.meteors) 
			m.Draw(g);
		
		renderUI(g);
		//dispose the graphics object and show the rendered buffer
		g.dispose();
		bs.show();
	}
	
	public static void renderUI(Graphics2D g) {
		if (GameLogic.level > 0 && GameLogic.level < 5) {
			g.setColor(new Color(250, 230, 175));
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
			g.drawString("Score: " + GameLogic.score, 40, 60);
		}
		else if (GameLogic.level == 5) {
			g.setColor(new Color(250, 230, 175));
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
			g.drawString("Score: " + GameLogic.score, 280, 80);
			g.drawString("Again!", 85, 685);
			g.drawString("Menu", 550, 685);
		}
	}
	
	public static void renderStartMenu() {
		//create the graphics object 
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		//draw the background
		g.setColor(new Color(23,21,43));
		g.fillRect(0, 0, 800, 800);
		
		//draw the stars
		for (Star s : GameLogic.stars) 
			s.Draw(g);
		
		//render visuals for buttons
		drawShadedCircle(180, 315, 50, new Color(240, 250, 175), g);
		drawShadedCircle(270, 315, 50, new Color(240, 250, 175), g);
		
		drawShadedCircle(470, 320, 45, new Color(240, 250, 175), g);
		drawShadedCircle(530, 320, 45, new Color(240, 250, 175), g);
		drawShadedCircle(590, 320, 45, new Color(240, 250, 175), g);
		
		drawShadedCircle(160, 500, 40, new Color(240, 250, 175), g);
		drawShadedCircle(207, 500, 40, new Color(240, 250, 175), g);
		drawShadedCircle(254, 500, 40, new Color(240, 250, 175), g);
		drawShadedCircle(300, 500, 40, new Color(240, 250, 175), g);
		
		drawShadedCircle(450, 505, 35, new Color(240, 250, 175), g);
		drawShadedCircle(491, 505, 35, new Color(240, 250, 175), g);
		drawShadedCircle(532, 505, 35, new Color(240, 250, 175), g);
		drawShadedCircle(573, 505, 35, new Color(240, 250, 175), g);
		drawShadedCircle(615, 505, 35, new Color(240, 250, 175), g);
		

		for (Button b : GameLogic.startButtons)
			b.Render(g);
		
		//render logo, planets from left to right, other text
		drawShadedCircle(96, 43, 135, new Color(176, 102, 250), g, 5);
		drawShadedCircle(196, 40, 165, new Color(230, 90, 125), g, 8);
		drawShadedCircle(485, 76, 115, new Color(160, 205, 250), g, 5);
		drawShadedCircle(380, 44, 135, new Color(134, 247, 147), g, 7);
		drawShadedCircle(555, 30, 165, new Color(250, 125, 231), g, 10);
		
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 80));
		g.setColor(new Color(23,21,43));
		g.drawString("CRASH COURSE", 115, 145);
		g.setColor(new Color(250, 230, 175));
		g.drawString("CRASH COURSE", 108, 140);
		
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
		g.drawString("Choose Level:", 260, 260);
		
		//dispose the graphics object and show the rendered buffer
		g.dispose();
		bs.show();
	}
	
	public static void renderLoseMenu() {
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		//draw the background
		g.setColor(new Color(23,21,43));
		g.fillRect(0, 0, 800, 800);
		
		//draw the stars
		for (Star s : GameLogic.stars) 
			s.Draw(g);
		
		//draw the sun 
		for (int i = 0; i <= 50; i += 10) {
		    Color baseColor = new Color(200, 180, 125);
		    Color color = new Color(
		        baseColor.getRed() + Math.min(2*i, 55),
		        baseColor.getGreen() + Math.min(2*i, 75),
		        baseColor.getBlue() + 2*i
		    );

		    g.setColor(color);
		    g.fillOval(400 - (50 - i)/2, 400 - (50 - i)/2, 50 - i, 50 - i);
		}

		//draw the planets
		for (Planet p : GameLogic.planets)
			p.Draw(g);
		
		for (Button b : GameLogic.loseButtons)
			b.Render(g);
		renderUI(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void drawShadedCircle(int x, int y, int radius, Color color, Graphics2D g) {
		Color shadedColor = new Color (color.getRed() - 50, color.getGreen() - 50, color.getBlue() - 50);
		g.setColor(shadedColor);
		g.fillOval(x+2, y+2, radius, radius);
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}
	
	public static void drawShadedCircle(int x, int y, int radius, Color color, Graphics2D g, int shadowOffset) {
		Color shadedColor = new Color (color.getRed() - 50, color.getGreen() - 50, color.getBlue() - 50);
		g.setColor(shadedColor);
		g.fillOval(x+shadowOffset, y+shadowOffset, radius, radius);
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}
}
