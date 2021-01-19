package tests;

import views.gui.GameWindow;
import models.boards.StandardBoard;

public class Startup
{
	public static void main(String[] args) 
	{
		new GameWindow(new StandardBoard());
	}
}
