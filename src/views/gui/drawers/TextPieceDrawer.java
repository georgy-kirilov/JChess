package views.gui.drawers;

import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Graphics2D;

import models.pieces.*;
import enums.PieceColor;

public class TextPieceDrawer implements PieceDrawer 
{
	private final HashMap<Class<?>, String> piecesAndSymbols = new HashMap<>();
	
	public TextPieceDrawer() 
	{
		piecesAndSymbols.put(Pawn.class, "♟");
		piecesAndSymbols.put(Bishop.class, "♝");
		piecesAndSymbols.put(Knight.class, "♞");
		piecesAndSymbols.put(Rook.class, "♜");
		piecesAndSymbols.put(Queen.class, "♛");
		piecesAndSymbols.put(King.class, "♚");
	}
	
	@Override
	public void drawPiece(Graphics2D g, Piece piece, int cellWidth, int cellHeight) 
	{
		String symbol = "";
		
		if (piece != null)
			symbol = piecesAndSymbols.get(piece.getClass());
		
		Color foregroundColor = Color.BLACK;
		
		if (piece != null && piece.getColor() == PieceColor.WHITE) 
			foregroundColor = Color.WHITE;
		
		int size = cellWidth / 4 * 3;
	
		g.setFont(new Font("Serif", Font.PLAIN, size));
		g.setColor(foregroundColor);
		g.drawString(symbol, cellWidth / 2 - size / 2, cellHeight - size / 3);
	}
}
