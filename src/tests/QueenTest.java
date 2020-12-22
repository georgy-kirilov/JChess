package tests;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import models.boards.StandardBoard;
import models.pieces.Queen;


public class QueenTest {

	public static void main(String[] args)
	{
		Board board = new StandardBoard();
		Queen queen = new Queen(PieceColor.BLACK);	
		Position position = new Position(5, 4);
		board.setAt(position, queen);
		Iterable<Position> positions = board.getAt(position).getAllReachablePositions(position, board);
		for(Position q: positions)
		{
			System.out.println(q);
		}
	}

}
