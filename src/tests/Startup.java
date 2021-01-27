package tests;

import core.GuiChessEngine;

import models.boards.*;
import views.gui.BoardGuiView;
import views.gui.drawers.PieceDrawer;
import views.gui.drawers.TextPieceDrawer;

public class Startup
{	
	public static void main(String[] args) 
	{
		PieceDrawer pieceDrawer = new TextPieceDrawer();
		Board gameboard = new StandardBoard();
		
		new GuiChessEngine(new BoardGuiView(gameboard, pieceDrawer));
	}
}
