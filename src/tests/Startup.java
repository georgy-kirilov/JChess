package tests;

import core.GuiChessEngine;

import models.boards.Board;
import models.boards.StandardBoard;
import views.gui.BoardGuiView;
import views.gui.MainMenuFrame;
import views.gui.drawers.PieceDrawer;
import views.gui.drawers.TextPieceDrawer;

import java.awt.*;

public class Startup
{	
	public static void main(String[] args) 
	{
		PieceDrawer pieceDrawer = new TextPieceDrawer();
		Board gameboard = new StandardBoard();
		//MainMenuFrame mainMenu = new MainMenuFrame(new Rectangle(0, 0, 640, 640));
		new GuiChessEngine(new BoardGuiView(gameboard, pieceDrawer));
	}
}
