package core;

import java.awt.Rectangle;
import javax.swing.JFrame;

import models.boards.Board;
import views.gui.BoardView;
import views.gui.drawers.PieceDrawer;

public class GameEngine 
{
	private final JFrame gameWindow;
	private final BoardView boardView;
	
	public GameEngine(Board board, PieceDrawer pieceDrawer)
	{
		boardView = new BoardView(new Rectangle(1, 1, 640, 640), board, pieceDrawer);
		
		gameWindow = new JFrame();
		gameWindow.setBounds(new Rectangle(0, 0, boardView.getWidth() + 19, boardView.getHeight() + 41));
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false);
		gameWindow.setLayout(null);
		gameWindow.add(boardView);
		gameWindow.setVisible(true);
	}
}
