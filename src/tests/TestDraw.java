package tests;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;

import common.Position;
import enums.PieceColor;
import models.boards.BaseBoard;
import models.boards.Board;
import models.boards.StandardBoard;
import models.pieces.Pawn;
import models.pieces.Knight;
import views.gui.BoardView;
import views.gui.CellView;

public class TestDraw {

	public static void main(String[] args) {
		Knight Knight = new Knight(PieceColor.WHITE);
		
		Pawn pawn = new Pawn (PieceColor.BLACK);
		
		CellView cv[] = new CellView[2];
		
		Rectangle r = new Rectangle(10,20,30,30);
		
		cv[0] = new CellView(r,pawn,true);
		
		cv[1] = new CellView(r,Knight,false);
		
		JFrame  f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100,100,900,500);
		f.setVisible(true);
		f.setLayout(null);
		
		cv[0].setBounds(20,20,80,80);
		f.add(cv[0]);
		
		cv[1].setBounds(270,20,50,50);
		f.add(cv[1]);
		
		System.out.println("ok");
		/*JFrame  f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100,100,900,500);
		f.setVisible(true);
		f.setLayout(null);
		
		Board b = new StandardBoard();
		BoardView board = new BoardView(b);
		board.setBounds(10, 10, 500, 500);
		f.add(board);*/
	}

}
