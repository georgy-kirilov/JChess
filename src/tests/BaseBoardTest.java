package tests;


import common.Position;
import models.boards.BaseBoard;
import models.boards.StandardBoard;

public class BaseBoardTest 
{
	public static void main(String[] args) 
	{
		BaseBoard board = new StandardBoard();
		System.out.println(board.isEmptyAt(new Position(0, 0)));
		System.out.println(board.isPositionInside(new Position(0, 0)));
	}
}
