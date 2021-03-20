package core;

import java.awt.Rectangle;

import views.gui.GameFrame;
import views.gui.MainMenuFrame;

public class GuiChessEngine
{
	private MainMenuFrame mainMenuFrame;
	
	public GuiChessEngine(GuiIoProvider guiIoProvider)
	{
		mainMenuFrame = new MainMenuFrame(guiIoProvider);
	}
} 
