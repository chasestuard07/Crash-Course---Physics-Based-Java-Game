import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameLoop implements ActionListener {
	
	private Timer timer;
	private int level;

	public void Start() {
		//start the frame rate timer, set the level to the main menu, and initialize the level
		timer = new Timer(16, this);
		timer.start();
		level = 0; 
		GameLogic.initLevel();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(level == GameLogic.level) {
			//update the game and draw the frame every frame (~16 ms)
			GameLogic.updateLogic();
			PlayerInput.update();
			
			//if on main menu, render the menu, otherwise render the game 
			//TODO: add the lose screen
			if (GameLogic.level == 0)
				Renderer.renderStartMenu();
			else if (GameLogic.level == 5) 
				Renderer.renderLoseMenu();
			else 	
				Renderer.draw();
		}
		else //if the level has changed in GameLogic, update for this script as well
			level = GameLogic.initLevel();
	}
}
