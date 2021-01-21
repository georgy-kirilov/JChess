package core;

import common.Helper;
import views.gui.GameFrame;

public class GameEngine 
{
	private GameFrame gameFrame;
	
	public GameEngine(GameFrame gameFrame)
	{
		Helper.throwIfNull(gameFrame);
		this.gameFrame = gameFrame;
	}
}
