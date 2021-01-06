package views.gui;

import java.awt.Rectangle;
import javax.swing.JFrame;
import models.boards.Board;
import views.gui.drawers.TextPieceDrawer;

@SuppressWarnings("serial")
public class GameWindow extends JFrame
{
	private static final Rectangle BOUNDS = new Rectangle(0, 0, 618, 641);
	
	public GameWindow(Board board)
	{
		this.setBounds(BOUNDS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		
		Rectangle boardBounds = new Rectangle(1, 1, 600, 600);
		
		this.add(new BoardView(boardBounds, board, new TextPieceDrawer(), new StandardGameListener(board)));
	}
}
