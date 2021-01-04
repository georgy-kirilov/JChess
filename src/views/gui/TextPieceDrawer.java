package views.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

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
	private HashMap<Class<?>, String> piecesAndSymbols = new HashMap<Class<?>, String>();
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
		if(piece != null)
		{
			symbol = this.piecesAndSymbols.get(piece.getClass());
		}
		Color forGroundColor ;
		if(piece!=null&&piece.getColor() == PieceColor.WHITE) 
		{
			forGroundColor=Color.WHITE;
		}
		else
		{
			forGroundColor=Color.black;
		}
		
		
		int size = (int) (cellDimensions.getWidth()/4*3);
		System.out.println((cellDimensions.getWidth()/2));
		Font s = new Font ("Serif",Font.PLAIN, size);
		g.setColor(forGroundColor);
		g.setFont(s);
		
		g.drawString(symbol, (int)cellDimensions.getWidth()/2-size/2, (int)cellDimensions.getHeight()-size/3);
	}

}
