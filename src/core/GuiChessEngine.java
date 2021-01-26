package core;

import java.awt.Rectangle;

import views.gui.GameFrame;

public class GuiChessEngine
{
	private final Rectangle GAME_FRAME_BOUNDS = new Rectangle(0, 0, 640, 640);
	
	private GameFrame gameFrame;
	
	public GuiChessEngine(GuiIoProvider guiIoProvider)
	{
		gameFrame = new GameFrame(GAME_FRAME_BOUNDS, guiIoProvider);
		gameFrame.setVisible(true);
	}
} 
