package models.pieces;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import common.MovementOffsetPair;

public class King extends BasePiece
{
	public King(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board)
	{
		MovementOffsetPair[] offsetPairs = new MovementOffsetPair[]
		{
			MovementOffsetPair.UP,
			MovementOffsetPair.DOWN,
			MovementOffsetPair.RIGHT,
			MovementOffsetPair.LEFT,
			MovementOffsetPair.TOP_RIGHT,
			MovementOffsetPair.TOP_LEFT,
			MovementOffsetPair.BOTTOM_RIGHT,
			MovementOffsetPair.BOTTOM_LEFT,
		};
		
		return this.getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
	
	
	public boolean isKingInCheck(Position currentPosition, Board board, PieceColor color) 
	{
		
		
		//checking for a Rook or Queen above the King with the opposite of his color
		for (int i = currentPosition.getRow(); i < 8; i++)
		{
			boolean breakOnNonEmpty = false;
			
			//if boards position is empty the loop can continue
			if (board.isEmptyAt(i, currentPosition.getColumn()))
			{
				
				breakOnNonEmpty = false;

				
			}
			else
			{
				
				//Checking if there's Rook or Queen on the position
				if (board.getAt(i, currentPosition.getColumn()).getClass()==Rook.class ||
						board.getAt(i, currentPosition.getColumn()).getClass()==Queen.class)
				{
					
					//Checking if the color of the Rook/Queen is the opposite of the Kings
					if ((board.getAt(i, currentPosition.getColumn()).getColor())!=this.getColor())
					{
						
						
						//if it's a Rook or a Queen with the opposite color of the King's then we are in Check
						//returning which means that the King is in Check
						return true;
						
						
						//if the Piece is not the opposite of the King's color then we are breaking the loop
					}
					else
					{
						breakOnNonEmpty = true;
						
					}
					
					
					// if the Piece is not a Rook or a Queen then we are breaking the loop
				}
				else
				{
					breakOnNonEmpty = true;
					
				}
				
				
				
				
			}
			
			//If variable breakOnNonEmpty equals true then we break the loop
			if (breakOnNonEmpty == true) 
			{
				break;
			}
			
		}

		// checking for a Rook or Queen below the King with the opposite of his color
		for (int i = currentPosition.getRow(); i>0; i--)
		{
			boolean breakOnNonEmpty = false;

			// if boards position is empty the loop can continue
			if (board.isEmptyAt(i, currentPosition.getColumn()))
			{

				breakOnNonEmpty = false;

			} 
			else
			{

				// Checking if there's Rook or Queen on the position
				if (board.getAt(i, currentPosition.getColumn()).getClass() == Rook.class
						|| board.getAt(i, currentPosition.getColumn()).getClass() == Queen.class) 
				{

					// Checking if the color of the Rook/Queen is the opposite of the Kings
					if ((board.getAt(i, currentPosition.getColumn()).getColor()) != this.getColor()) 
					{

						// if it's a Rook or a Queen with the opposite color of the King's then we are
						// in Check
						// returning which means that the King is in Check
						return true;

						// if the Piece is not the opposite of the King's color then we are breaking the
						// loop
					} 
					else 
					{
						breakOnNonEmpty = true;

					}

					// if the Piece is not a Rook or a Queen then we are breaking the loop
				} 
				else 
				{
					breakOnNonEmpty = true;

				}

			}

			// If variable breakOnNonEmpty equals true then we break the loop
			if (breakOnNonEmpty == true) 
			{
				break;
			}

		}
				
				
		
		
		// checking for a Rook or Queen on the right of the King with the opposite of
		// his color
		for (int i = currentPosition.getColumn(); i < 8; i++)
		{
			boolean breakOnNonEmpty = false;

			// if boards position is empty the loop can continue
			if (board.isEmptyAt(currentPosition.getRow(), i)) 
			{

				breakOnNonEmpty = false;

			} 
			else 
			{

				// Checking if there's Rook or Queen on the position
				if (board.getAt(currentPosition.getRow(), i).getClass() == Rook.class
						|| board.getAt(currentPosition.getRow(), i).getClass() == Queen.class)
				{

					// Checking if the color of the Rook/Queen is the opposite of the Kings
					if ((board.getAt(currentPosition.getRow(), i).getColor()) != this.getColor()) 
					{

						// if it's a Rook or a Queen with the opposite color of the King's then we are
						// in Check
						// returning which means that the King is in Check
						return true;

						// if the Piece is not the opposite of the King's color then we are breaking the
						// loop
					}
					else 
					{
						breakOnNonEmpty = true;

					}

					// if the Piece is not a Rook or a Queen then we are breaking the loop
				} 
				else 
				{
					breakOnNonEmpty = true;

				}

			}

			// If variable breakOnNonEmpty equals true then we break the loop
			if (breakOnNonEmpty == true)
			{
				break;
			}

		}

		// checking for a Rook or Queen on the left of the King with the opposite of his
		// color
		for (int i = currentPosition.getColumn(); i > 0; i--) 
		{
			boolean breakOnNonEmpty = false;

			// if boards position is empty the loop can continue
			if (board.isEmptyAt(currentPosition.getRow(), i))
			{

				breakOnNonEmpty = false;

			}
			else 
			{

				// Checking if there's Rook or Queen on the position
				if (board.getAt(currentPosition.getRow(), i).getClass() == Rook.class
						|| board.getAt(currentPosition.getRow(), i).getClass() == Queen.class)
				{

					// Checking if the color of the Rook/Queen is the opposite of the Kings
					if ((board.getAt(currentPosition.getRow(), i).getColor()) != this.getColor()) 
					{

						// if it's a Rook or a Queen with the opposite color of the King's then we are
						// in Check
						// returning which means that the King is in Check
						return true;

						// if the Piece is not the opposite of the King's color then we are breaking the
						// loop
					} 
					else 
					{
						breakOnNonEmpty = true;

					}

					// if the Piece is not a Rook or a Queen then we are breaking the loop
				} 
				else
{
					breakOnNonEmpty = true;

				}

			}

			// If variable breakOnNonEmpty equals true then we break the loop
			if (breakOnNonEmpty == true)
			{
				break;
			}

		}
		
		// the number of times we are going to check UP RIGHT
		int timesToMoveRightUp;
		
		if (8-currentPosition.getColumn()< 8-currentPosition.getRow())
		{
			timesToMoveRightUp = 8-currentPosition.getColumn();
		}
		else
		{
			timesToMoveRightUp = 8-currentPosition.getRow();
		}
		
		
		for (int i = 0; i < timesToMoveRightUp; i++)
		{
			
			
			boolean breakOnNonEmpty = false;

			// if boards position is empty the loop can continue
			if (board.isEmptyAt(currentPosition.getColumn()+i, currentPosition.getRow()+i))
			{

				breakOnNonEmpty = false;

			}
			else 
			{

				// Checking if there's Bishop or Queen on the position
				if (board.getAt(currentPosition.getColumn()+i, currentPosition.getRow()+i).getClass() == Bishop.class
						|| board.getAt(currentPosition.getColumn()+i, currentPosition.getRow()+i).getClass() == Queen.class)
				{

					// Checking if the color of the Bishop/Queen is the opposite of the Kings
					if ((board.getAt(currentPosition.getColumn()+i, currentPosition.getRow()+i).getColor()) != this.getColor()) 
					{

						// if it's a Bishop or a Queen with the opposite color of the King's then we are
						// in Check
						// returning true which means that the King is in Check
						return true;

						// if the Piece is not the opposite of the King's color then we are breaking the
						// loop
					} 
					else 
					{
						breakOnNonEmpty = true;

					}

					// if the Piece is not a Bishop or a Queen then we are breaking the loop
				} 
				else
{
					breakOnNonEmpty = true;

				}

			}

			// If variable breakOnNonEmpty equals true then we break the loop
			if (breakOnNonEmpty == true)
			{
				break;
			}

			
			
			
			
			
		}
		
		
		int timesToMoveRightDown;
		
		if (8-currentPosition.getColumn()< currentPosition.getRow())
		{
			timesToMoveRightDown = 8-currentPosition.getColumn();
		}
		else
		{
			timesToMoveRightDown = 8-currentPosition.getRow();
		}
		
		
		for (int i = 0; i < timesToMoveRightDown; i++)
		{
			
			
			boolean breakOnNonEmpty = false;

			// if boards position is empty the loop can continue
			if (board.isEmptyAt(currentPosition.getColumn()+i, currentPosition.getRow()-i))
			{

				breakOnNonEmpty = false;

			}
			else 
			{

				// Checking if there's Bishop or Queen on the position
				if (board.getAt(currentPosition.getColumn()+i, currentPosition.getRow()-i).getClass() == Bishop.class
						|| board.getAt(currentPosition.getColumn()+i, currentPosition.getRow()-i).getClass() == Queen.class)
				{

					// Checking if the color of the Bishop/Queen is the opposite of the Kings
					if ((board.getAt(currentPosition.getColumn()+i, currentPosition.getRow()-i).getColor()) != this.getColor()) 
					{

						// if it's a Bishop or a Queen with the opposite color of the King's then we are
						// in Check
						// returning true which means that the King is in Check
						return true;

						// if the Piece is not the opposite of the King's color then we are breaking the
						// loop
					} 
					else 
					{
						breakOnNonEmpty = true;

					}

					// if the Piece is not a Bishop or a Queen then we are breaking the loop
				} 
				else
{
					breakOnNonEmpty = true;

				}

			}

			// If variable breakOnNonEmpty equals true then we break the loop
			if (breakOnNonEmpty == true)
			{
				break;
			}

			
			
			
			
			
		}
		
		
		
		
		return false;
	}
}
