package tests;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import models.boards.StandardBoard;
import models.pieces.Rook;

public class RookTest 
{

	public static void main(String[] args) 
	{
		Board board = new StandardBoard();
		Rook rook = new Rook(PieceColor.WHITE);	
		Position position = new Position(5, 4);
		board.setAt(position, rook);
		Iterable<Position> positions = board.getAt(position).getAllReachablePositions(position, board);
		for(Position p: positions)
		{
			System.out.println(p);
		}
	}

}