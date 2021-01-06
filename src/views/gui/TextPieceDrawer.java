package views.gui;

import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import enums.PieceColor;
import models.pieces.*;

public class TextPieceDrawer implements PieceDrawer 
{
	private HashMap<Class<?>, String> piecesAndSymbols = new HashMap<>();
	
	public TextPieceDrawer() 
	{
		this.piecesAndSymbols.put(Pawn.class,"♟" );
		this.piecesAndSymbols.put(Bishop.class, "♝");
		this.piecesAndSymbols.put(Knight.class, "♞");
		this.piecesAndSymbols.put(Rook.class, "♜");
		this.piecesAndSymbols.put(Queen.class, "♛");
		this.piecesAndSymbols.put(King.class, "♚");
	}
	
	@Override
	public void drawPiece(Graphics2D g, Piece piece, Rectangle cellDimensions) 
	{
		String symbol = "";
		
		if (piece != null)
		{
			symbol = this.piecesAndSymbols.get(piece.getClass());
		}
		
		Color foregroundColor = Color.BLACK;
		
		if (piece != null && piece.getColor() == PieceColor.WHITE) 
		{
			foregroundColor = Color.WHITE;
		}
		
		int size = cellDimensions.width / 4 * 3;
	
		Font s = new Font("Serif", Font.PLAIN, size);
		g.setColor(foregroundColor);
		g.setFont(s);
		
		g.drawString(symbol, cellDimensions.width / 2 - size / 2, cellDimensions.height - size / 3);
	}
}
