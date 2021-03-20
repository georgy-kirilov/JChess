package tests;

import core.GuiChessEngine;

import enums.PieceColor;
import models.boards.StandardBoard;
import views.gui.BoardGuiView;
import views.gui.drawers.TextPieceDrawer;

public class Startup
{	
	public static void main(String[] args) 
	{
		new GuiChessEngine(
				new BoardGuiView(
						new StandardBoard(),
						new TextPieceDrawer(),
						PieceColor.WHITE));
	}
}
