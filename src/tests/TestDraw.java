package tests;

import java.awt.Rectangle;

import javax.swing.JFrame;

import models.boards.Board;
import views.gui.BoardView;
import views.gui.TextPieceDrawer;
import models.boards.StandardBoard;

public class TestDraw
{
	public static void main(String[] args) 
	{
		JFrame  f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 700, 540);
		f.setResizable(false);
		f.setVisible(true);
		f.setLayout(null);
		
		Board b = new StandardBoard();
		BoardView board = new BoardView(new Rectangle(10, 10, 480, 480), b, new TextPieceDrawer());
		
		f.add(board);
	}
}
