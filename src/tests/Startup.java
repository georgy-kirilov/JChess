package tests;

import enums.PieceColor;
import core.GameListener;
import models.boards.StandardBoard;
import views.gui.drawers.TextPieceDrawer;

public class Startup
{
	public static void main(String[] args) 
	{
		new GameListener(
				new StandardBoard(), 
				new TextPieceDrawer(), 
				PieceColor.WHITE);
	}
}
