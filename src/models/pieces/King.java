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
		for (int i = currentPosition.getRow(); i <= 8; i++)
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
		for (int i = currentPosition.getColumn(); i <= 8; i++)
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
		
		
		for (int i = 1; i <= timesToMoveRightUp; i++)
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
		
		
		int timesToMoveLeftUp;
		
		if (currentPosition.getColumn()< 8-currentPosition.getRow())
		{
			timesToMoveLeftUp = currentPosition.getColumn();
		}
		else
		{
			timesToMoveLeftUp = 8-currentPosition.getRow();
		}
		
		
		for (int i = 1; i <= timesToMoveLeftUp; i++)
		{
			
			
			boolean breakOnNonEmpty = false;

			// if boards position is empty the loop can continue
			if (board.isEmptyAt(currentPosition.getColumn()-i, currentPosition.getRow()+i))
			{

				breakOnNonEmpty = false;

			}
			else 
			{

				// Checking if there's Bishop or Queen on the position
				if (board.getAt(currentPosition.getColumn()-i, currentPosition.getRow()+i).getClass() == Bishop.class
						|| board.getAt(currentPosition.getColumn()-i, currentPosition.getRow()+i).getClass() == Queen.class)
				{

					// Checking if the color of the Bishop/Queen is the opposite of the Kings
					if ((board.getAt(currentPosition.getColumn()-i, currentPosition.getRow()+i).getColor()) != this.getColor()) 
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
		
		
		
		int timesToMoveLeftDown;
		
		if (currentPosition.getColumn()< currentPosition.getRow())
		{
			timesToMoveLeftDown = currentPosition.getColumn();
		}
		else
		{
			timesToMoveLeftDown = currentPosition.getRow();
		}
		
		
		for (int i = 1; i <= timesToMoveLeftDown; i++)
		{
			
			
			boolean breakOnNonEmpty = false;

			// if boards position is empty the loop can continue
			if (board.isEmptyAt(currentPosition.getColumn()-i, currentPosition.getRow()-i))
			{

				breakOnNonEmpty = false;

			}
			else 
			{

				// Checking if there's Bishop or Queen on the position
				if (board.getAt(currentPosition.getColumn()-i, currentPosition.getRow()-i).getClass() == Bishop.class
						|| board.getAt(currentPosition.getColumn()-i, currentPosition.getRow()-i).getClass() == Queen.class)
				{

					// Checking if the color of the Bishop/Queen is the opposite of the Kings
					if ((board.getAt(currentPosition.getColumn()-i, currentPosition.getRow()-i).getColor()) != this.getColor()) 
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
		
		
		
		
		
		
		
		
		//checking one field up and right
		Position checkingPositionRightUp = new Position(currentPosition.getRow()+1, currentPosition.getColumn()+1);
		if (board.isPositionInside(checkingPositionRightUp)
				&& !board.isEmptyAt(currentPosition.getRow()+1, currentPosition.getColumn()+1)
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()+1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()+1).getClass()!= Rook.class
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()+1).getClass()!= Knight.class)
		{
			return true;
		}
		
		//checking one field up and left 
		Position checkingPositionLeftUp = new Position(currentPosition.getRow()+1, currentPosition.getColumn()-1);
		if (board.isPositionInside(checkingPositionLeftUp)
				&& !board.isEmptyAt(currentPosition.getRow()+1, currentPosition.getColumn()-1)
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()-1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()-1).getClass()!= Rook.class
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()-1).getClass()!= Knight.class)
		{
			return true;
		}
		
		//checking one field up
		Position checkingPositionUp = new Position(currentPosition.getRow()+1, currentPosition.getColumn());
		if (board.isPositionInside(checkingPositionUp)
				&& !board.isEmptyAt(currentPosition.getRow()+1, currentPosition.getColumn())
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()).getClass()!= Bishop.class
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()).getClass()!= Knight.class
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()).getClass()!= Pawn.class)
		{
			return true;
		}
		
		
		//checking one field down
		Position checkingPositionDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn());
		if (board.isPositionInside(checkingPositionDown)
				&& !board.isEmptyAt(currentPosition.getRow()-1, currentPosition.getColumn())
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()).getClass()!= Bishop.class
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()).getClass()!= Knight.class
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()).getClass()!= Pawn.class)
		{
			return true;
		}
		
		//checking one field left
		Position checkingPositionLeft = new Position(currentPosition.getRow(), currentPosition.getColumn()+1);
		if (board.isPositionInside(checkingPositionLeft)
				&& !board.isEmptyAt(currentPosition.getRow(), currentPosition.getColumn()+1)
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()+1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()+1).getClass()!= Bishop.class
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()+1).getClass()!= Knight.class
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()+1).getClass()!= Pawn.class)
		{
			return true;
		}
		
		//checking one field right
		Position checkingPositionRight = new Position(currentPosition.getRow(), currentPosition.getColumn()-1);
		if (board.isPositionInside(checkingPositionRight)
				&& !board.isEmptyAt(currentPosition.getRow(), currentPosition.getColumn()-1)
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()-1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()-1).getClass()!= Bishop.class
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()-1).getClass()!= Knight.class
				&& board.getAt(currentPosition.getRow(), currentPosition.getColumn()-1).getClass()!= Pawn.class)
		{
			return true;
		}
		
		
		// checking one field down and right
		Position checkingPositionRightDown = new Position(currentPosition.getRow() + 1, currentPosition.getColumn() - 1);
		if (board.isPositionInside(checkingPositionRightDown)
				&& !board.isEmptyAt(currentPosition.getRow() + 1, currentPosition.getColumn() - 1)
				&& board.getAt(currentPosition.getRow() + 1, currentPosition.getColumn() - 1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow() + 1, currentPosition.getColumn() - 1).getClass() != Rook.class
				&& board.getAt(currentPosition.getRow() + 1, currentPosition.getColumn() - 1).getClass() != Knight.class)
		{
			return true;
		}
		// checking one field down and left
		Position checkingPositionLeftDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn()-1);
		if (board.isPositionInside(checkingPositionLeftDown)
				&& !board.isEmptyAt(currentPosition.getRow()-1, currentPosition.getColumn()-1)
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()-1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()-1).getClass() != Rook.class
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()-1).getClass() != Knight.class)
		{
			return true;
		}
		
		
		
		//checking for knight attacks on the Up Right
		Position checkingPositionUpRight = new Position(currentPosition.getRow()+2, currentPosition.getColumn()+1);
		if (board.isPositionInside(checkingPositionUpRight)
				&& !board.isEmptyAt(currentPosition.getRow()+2, currentPosition.getColumn()+1)
				&& board.getAt(currentPosition.getRow()+2, currentPosition.getColumn()+1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()+2, currentPosition.getColumn()+1).getClass() == Knight.class)
		{
			return true;
		}
		
		
		
		//checking for knight attacks on the Down Right
		Position checkingPositionDownRight = new Position(currentPosition.getRow()-2, currentPosition.getColumn()+1);
		if (board.isPositionInside(checkingPositionDownRight)
				&& !board.isEmptyAt(currentPosition.getRow()-2, currentPosition.getColumn()+1)
				&& board.getAt(currentPosition.getRow()-2, currentPosition.getColumn()+1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()-2, currentPosition.getColumn()+1).getClass() == Knight.class)
		{
			return true;
		}	
		
		//checking for knight attacks on the Down Left
		Position checkingPositionDownLeft = new Position(currentPosition.getRow()-2, currentPosition.getColumn()-1);
		if (board.isPositionInside(checkingPositionDownLeft) &&
				!board.isEmptyAt(currentPosition.getRow()-2, currentPosition.getColumn()-1)
				&& board.getAt(currentPosition.getRow()-2, currentPosition.getColumn()-1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()-2, currentPosition.getColumn()-1).getClass() == Knight.class)
		{
			return true;
		}	
		
		
		//checking for knight attacks on the Up Left
		Position checkingPositionUpLeft = new Position(currentPosition.getRow()+2, currentPosition.getColumn()-1);
		if (board.isPositionInside(checkingPositionUpLeft)
				&& !board.isEmptyAt(currentPosition.getRow()+2, currentPosition.getColumn()-1)
				&& board.getAt(currentPosition.getRow()+2, currentPosition.getColumn()-1).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()+2, currentPosition.getColumn()-1).getClass() == Knight.class)
		{
			return true;
		}
		
		

		// checking for the Knight attacks on the sides 

		
		//checking for knight attacks on the Up Right side
		Position checkingPositionSideUpRight = new Position(currentPosition.getRow()+1, currentPosition.getColumn()+2);
		if (board.isPositionInside(checkingPositionSideUpRight)
				&& !board.isEmptyAt(currentPosition.getRow()+1, currentPosition.getColumn()+2)
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()+2).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()+2).getClass() == Knight.class)
		{
			return true;
		}
		
		
		
		//checking for knight attacks on the Down Right side
		Position checkingPositionSideDownRight = new Position(currentPosition.getRow()-1, currentPosition.getColumn()+2);
		if (board.isPositionInside(checkingPositionSideDownRight)
				&& !board.isEmptyAt(currentPosition.getRow()-1, currentPosition.getColumn()+2)
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()+2).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()+2).getClass() == Knight.class)
		{
			return true;
		}	
		
		//checking for knight attacks on the Down Left side
		Position checkingPositionSideDownLeft = new Position(currentPosition.getRow()-1, currentPosition.getColumn()-2);
		if (board.isPositionInside(checkingPositionSideDownLeft) &&
				!board.isEmptyAt(currentPosition.getRow()-1, currentPosition.getColumn()-2)
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()-2).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()-1, currentPosition.getColumn()-2).getClass() == Knight.class)
		{
			return true;
		}	
		
		
		//checking for knight attacks on the Up Left
		Position checkingPositionSideUpLeft = new Position(currentPosition.getRow()+1, currentPosition.getColumn()-2);
		if (board.isPositionInside(checkingPositionSideUpLeft)
				&& !board.isEmptyAt(currentPosition.getRow()+1, currentPosition.getColumn()-2)
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()-2).getColor() != this.getColor()
				&& board.getAt(currentPosition.getRow()+1, currentPosition.getColumn()-2).getClass() == Knight.class)
		{
			return true;
		}
		
		
		
		return false;
	}
}
