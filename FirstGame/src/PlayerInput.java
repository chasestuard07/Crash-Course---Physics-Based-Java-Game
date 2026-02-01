import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.Math;

public class PlayerInput implements MouseListener, MouseMotionListener {

	public static int MouseX, MouseY; //current mouse position
	public static int prevMouseX, prevMouseY; //mouse position the last frame
	public static int dragX, dragY; //mouse position at the start of a mouse drag 
	
	public static boolean isHeld; //state of mouse button
	public static boolean justReleased; //true if the user let go of the mouse the prior frame
	
	public static double dX, dY; //change in x and y position of the mouse between frames
	public static double throwAngle; //determined angle of the drag as it applies to the target planet
	public static double throwMagnitude; //determined magnitude of the drag as it applies to the target planet 
	
	
	public static void update() {
		justReleased = false; //assume the mouse is not just released
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		//set the previous position to the old current position
		prevMouseX = MouseX; 
		prevMouseY = MouseY;
		//update the current position
		MouseX = e.getX();
		MouseY = e.getY();
		
		//calculate change in X and Y
		dX = MouseX - prevMouseX;
		dY = MouseY - prevMouseY;
		
		//derive angle and magnitude from dX and dY
		throwAngle = Math.atan2(dY, dX);
		throwMagnitude = Math.hypot(dX, dY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//use click detection for the main menu to determine button presses 
		if (GameLogic.level == 0) {
			for (Button b : GameLogic.startButtons)
				b.ClickDetection(e.getX(), e.getY());
		}
		//TODO: add button functionality to death screen
		else if (GameLogic.level == 5) {
			for (Button b : GameLogic.loseButtons)
				b.ClickDetection(e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isHeld = true;
		dragX = e.getX(); //determines the starting x of a drag
		dragY = e.getY(); //determines the starting y of a drag
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isHeld = false;
		justReleased = true; //only condition when justReleased is true
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
		for (Button b : GameLogic.startButtons)
			b.HoverDetection(MouseX, MouseY);
		for (Button b : GameLogic.loseButtons)
			b.HoverDetection(MouseX, MouseY);
		
	}
	
	//unused functions 
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	

}
