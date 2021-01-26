package tests;

import core.GuiChessEngine;

import views.gui.BoardGuiView;
import views.gui.drawers.TextPieceDrawer;

import models.boards.StandardBoard;

public class Startup
{	
	public static void main(String[] args) 
	{
		new GuiChessEngine(new BoardGuiView(new StandardBoard(), new TextPieceDrawer()));
	}
}
