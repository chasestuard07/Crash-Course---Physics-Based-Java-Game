public class Main {
	public static PlayerInput input; 
	
	public static void main(String[] args) {
		input = new PlayerInput(); //create the playerInput object
		Renderer.initRenderer(); //initialize the renderer 
		GameLoop loop = new GameLoop(); //create the gameLoop object
		loop.Start(); //start the game loop
	}
}
