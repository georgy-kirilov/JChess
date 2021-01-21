package tests;

import java.awt.Rectangle;

import core.GameEngine;

import views.gui.BoardView;
import views.gui.GameFrame;
import views.gui.drawers.TextPieceDrawer;

import models.boards.Board;
import models.boards.StandardBoard;

public class Startup
{
	public static void main(String[] args) 
	{	
		Board board = new StandardBoard();
		BoardView boardView = new BoardView(board, new TextPieceDrawer());
		
		new GameEngine(new GameFrame(new Rectangle(0, 0, 640, 640), boardView));
	}
}
