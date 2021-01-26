package tests;

import java.awt.Rectangle;

import core.IOProvider;
import core.GameEngine;

import views.gui.BoardGuiView;
import views.gui.GameFrame;
import views.gui.drawers.TextPieceDrawer;

import models.boards.Board;
import models.boards.StandardBoard;

public class Startup
{
	static final Rectangle GAME_FRAME_BOUNDS = new Rectangle(0, 0, 640, 640);
	
	public static void main(String[] args) 
	{	
		Board board = new StandardBoard();
		
		IOProvider gameAnnouncer = new BoardGuiView(board, new TextPieceDrawer());
		
		GameEngine engine = new GameEngine(new GameFrame(GAME_FRAME_BOUNDS, gameAnnouncer));
	}
}
