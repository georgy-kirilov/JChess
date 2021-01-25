package views.gui.drawers;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;

import java.util.HashMap;

import models.pieces.*;
import models.boards.Board;

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
		piecesAndSymbols.put(null, "");
	}
	
	@Override
	public void drawPiece(Graphics2D g, Piece piece, int cellWidth, int cellHeight) 
	{
		Class<?> type = piece != Board.EMPTY_CELL ? piece.getClass() : null;
		String symbol = piecesAndSymbols.get(type);		
		
		Color foregroundColor = piece != Board.EMPTY_CELL && piece.getColor() == PieceColor.WHITE 
				? Color.WHITE : Color.BLACK;
		
		int horizontalSize = cellWidth / 4 * 3;
		int verticalSize = cellHeight / 5;
	
		g.setFont(new Font("Serif", Font.PLAIN, horizontalSize));
		g.setColor(foregroundColor);
		g.drawString(symbol, cellWidth / 2 - horizontalSize / 2, cellHeight - verticalSize);
	}
}
