package tests;

import java.awt.Font;

import javax.swing.JFrame;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import models.boards.StandardBoard;
import models.pieces.Pawn;
import models.pieces.Queen;
import views.gui.CellView;

public class TestDraw {

	public static void main(String[] args) {
		Board board = new StandardBoard();
		Queen queen = new Queen(PieceColor.WHITE);	
		Position position1 = new Position(0, 2);
		Position position2 = new Position(1, 4);
		Pawn pawn = new Pawn (PieceColor.BLACK);
		board.setAt(position1, pawn);
		board.setAt(position2, queen);
		CellView cv[] = new CellView[2];
		cv[0] = new CellView(position1.getRow(),position1.getColumn(),pawn);
		cv[1] = new CellView(position2.getRow(),position2.getColumn(),queen);
		JFrame  f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100,100,900,500);
		f.setVisible(true);
		f.setLayout(null);
		
		cv[0].setBounds(20,20,300,300);
		f.add(cv[0]);
		cv[1].setBounds(320,20,300,300);
		f.add(cv[1]);
		
		
		System.out.println("ok");
	}

}
