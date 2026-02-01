import java.awt.Color;

public class GameLogic {
	
	static Planet[] planets = 
		{
			new Planet(60, Math.PI/2, 22, new Color(176, 102, 250), 0.02),
			new Planet(110, 0, 28, new Color(230, 90, 125), 0.014),
			new Planet(150, Math.PI*0.25, 32, new Color(160, 205, 250), 0.01),
			new Planet(210, Math.PI*1.25, 36, new Color(134, 247, 147), 0.008),
			new Planet(270, Math.PI*1.75, 40, new Color(250,125,231), 0.005)
		};
		
	static Star[] stars = new Star[100];
	static Meteor[] meteors = new Meteor[10];
	static Button[] startButtons = 
		{
			new Button(140,290,1),
			new Button(440,290,2),
			new Button(140,470,3),
			new Button(440,470,4)
		};
	static Button[] loseButtons =
		{
			new Button(60, 620, 0),
			new Button(500, 620, 0)
		};
	
	
	public static int level = 5;
	/* Level Index 
	 * 0: Main Menu
	 * 1: 2 Planet Mode
	 * 2: 3 Planet Mode
	 * 3: 4 Planet Mode
	 * 4: 5 Planet Mode
	 * 5: Game Over
	 * 6: Simulation Mode (?)
	*/
	
	static int meteorTickMax = 200; //max amount of ticks until a new meteor is spawned
	static int meteorTick = 0; //current amount of ticks since last meteor spawn
	
	static int score = 0;
	
	public static int initLevel() {
		//generate stars
		for(int i = 0; i < stars.length; i++) {
			stars[i] = new Star((int) (Math.random()*800), Math.random()*2*Math.PI);
		}
		//generates inactive meteors
		for (int i = 0; i < meteors.length; i++) {
			meteors[i] = new Meteor(500, 0f);
		}

		//set up the game based on what level is active
		switch (level) {
			case 0: 
				break;
			case 1:
				planets[0].active = false;
				planets[2].active = false;
				planets[4].active = false;
				break;
			case 2:
				planets[1].active = false;
				planets[3].active = false;
				break;
			case 3:
				planets[4].active = false;
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
		}
		return level;
	}
	
	public static void meteorSpawn() {
		meteorTick++; 
		if (meteorTick >= meteorTickMax) {
			meteorTick = 0;
			Meteor.Create(meteors); 
			//reduces the ticks required for the next spawn
			meteorTickMax -= meteorTickMax * 0.02;
			meteorTickMax = Math.max(meteorTickMax, 40);
		}
	}
	
	public static void loseCondition() {
		for (Planet p : planets) {
			if (p.health <= 0) {
				//TODO: create a death scene before switching levels
				p.active = false;
				loseButtons[0].level = level;
				level = 5;
				initLevel();
			}
		}
	}
	
	public static void reset() {
		for (Planet p: planets) {
			p.health = 3;
			p.active = true;
			p.speed = 0.005;
		}
		
		score = 0;
		meteorTickMax = 200;
		meteorTick = 0;
	}
	
	public static void updateLogic() {
		for(Star s : stars)
			s.Update();
		
		if (level > 0) {
			for(Planet p : planets) 
				p.Update();
		}
		
		if (level > 0 && level < 5) {
			for(Meteor m : meteors)
				m.Update();
			
			meteorSpawn();
			loseCondition();
			System.out.println(score);
		}
		
	}
}
