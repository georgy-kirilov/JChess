package views.gui;

import java.awt.Rectangle;
import javax.swing.JFrame;
import models.boards.Board;
import views.gui.drawers.TextPieceDrawer;

@SuppressWarnings("serial")
public class GameWindow extends JFrame
{
	private final Rectangle BOUNDS = new Rectangle(0, 0, 618, 641);
	
	public GameWindow(Board board)
	{
		setBounds(BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		
		BoardView boardView = new BoardView(
						new Rectangle(1, 1, 600, 600), 
						board, 
						new TextPieceDrawer(), 
						new StandardGameListener(board));
		
		add(boardView);
	}
}
