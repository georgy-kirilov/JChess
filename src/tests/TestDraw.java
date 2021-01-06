package tests;

import views.gui.GameWindow;
import models.boards.StandardBoard;

public class TestDraw
{
	public static void main(String[] args) 
	{
		StandardBoard board = new StandardBoard ();
		board.flipBoard();
		GameWindow window = new GameWindow(board);
		
	}
}
