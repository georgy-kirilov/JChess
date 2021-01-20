package tests;

import core.GameListener;
import enums.PieceColor;
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
