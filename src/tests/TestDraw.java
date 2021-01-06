package tests;

import views.gui.GameWindow;
import models.boards.StandardBoard;

public class TestDraw
{
	public static void main(String[] args) 
	{
		GameWindow window = new GameWindow(new StandardBoard());
	}
}
