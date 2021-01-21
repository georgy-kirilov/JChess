package tests;

import core.GameEngine;
import models.boards.StandardBoard;
import views.gui.drawers.TextPieceDrawer;

public class Startup
{
	public static void main(String[] args) 
	{	
		new GameEngine(
				new StandardBoard(), 
				new TextPieceDrawer());
	}
}
