package views.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;


import enums.PieceColor;
import models.pieces.Bishop;
import models.pieces.King;
import models.pieces.Knight;
import models.pieces.Pawn;
import models.pieces.Piece;
import models.pieces.Queen;
import models.pieces.Rook;

public class TextPieceDrawer implements PieceDrawer 
{

	@Override
	public void drawPiece(Graphics2D g, Piece piece, Rectangle cellDimensions) 
	{
		Font s = new Font ("Serif",Font.PLAIN,35);
		g.setFont(s);
		if(piece.getClass()==Pawn.class) 
		{
			if(piece.getColor()==PieceColor.BLACK) 
			{
				g.setColor(Color.black);
				g.drawString("♟", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
			else 
			{
				g.setColor(Color.white);
				g.drawString("♙", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
		
			}
		}
		else if(piece.getClass()==Bishop.class) 
		{
			if(piece.getColor()==PieceColor.BLACK) 
			{
				g.setColor(Color.black);
				g.drawString("♝", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
			else 
			{
				g.setColor(Color.white);
				g.drawString("♗", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
		}
		else if(piece.getClass()==Knight.class) 
		{
			
			if(piece.getColor()==PieceColor.BLACK) 
			{
				g.setColor(Color.black);
				g.drawString("♞", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
			else 
			{
				g.setColor(Color.white);
				g.drawString("♘", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
		}
		else if(piece.getClass()==Rook.class) 
		{
			
			if(piece.getColor()==PieceColor.BLACK) 
			{
				g.setColor(Color.black);
				g.drawString("♜", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
			else 
			{
				g.setColor(Color.white);
				g.drawString("♖", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
		}
		
		else if(piece.getClass()==Queen.class) 
		{
			if(piece.getColor()==PieceColor.BLACK) 
			{
				g.setColor(Color.black);
				g.drawString("♛", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
			else 
			{
				g.setColor(Color.white);
				g.drawString("♕", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
		}
		
		else if(piece.getClass()==King.class) 
		{
			if(piece.getColor()==PieceColor.BLACK) 
			{
				g.setColor(Color.black);
				g.drawString("♚", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
			else 
			{
				g.setColor(Color.white);
				g.drawString("♔", (int)cellDimensions.getX(), (int)cellDimensions.getY()+35);
			}
		}
		
		
	}

}
