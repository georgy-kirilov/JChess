package core;

import java.awt.Rectangle;

import javax.swing.JFrame;

import common.Position;
import enums.GameStatus;
import enums.PieceColor;
import models.boards.Board;
import views.gui.BoardView;
import views.gui.drawers.PieceDrawer;

public class GameListener
{
	private final Rectangle BOUNDS = new Rectangle(0, 0, 618, 641);
	
	private final JFrame gameFrame;
	private final BoardView boardView;	
	private final GameManager gameManager;
	
	public GameListener(Board board, PieceDrawer pieceDrawer, PieceColor startPlayerColor) 
	{	
		gameManager = new GameManager(board, startPlayerColor);
		
		boardView = new BoardView(
				new Rectangle(1, 1, 600, 600),
				gameManager.getBoard(),
				pieceDrawer,
				this);
		
		gameFrame = new JFrame();
		gameFrame.setBounds(BOUNDS);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.setLayout(null);
		gameFrame.setVisible(true);
		gameFrame.add(boardView);
	}
	
	public Iterable<Position> onFromPositionSelected(Position from)
	{
		return gameManager.getReachablePositions(from);
	}

	public void onToPositionSelected(Position from, Position to)
	{
		GameStatus status = gameManager.makeMove(from, to);
		boardView.redraw();
		
		if (status == GameStatus.CHECKMATE)
		{
			boardView.announceGameOver(gameManager.getWinnerColor());
		}
		else if (status == GameStatus.CHECK)
		{
			boardView.announceCheck();
		}
	}
}
