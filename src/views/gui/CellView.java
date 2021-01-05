package views.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import jthrow.JThrower;
import models.pieces.Piece;

@SuppressWarnings("serial")
public class CellView extends JPanel
{
	private static final Color DARK_BACKGROUND = new Color(122,122,122);
	private static final Color LIGHT_BACKGROUND = new Color(192,192,192);
	
	private Piece piece;
	private final PieceDrawer drawer;
	private final Color backroundColor;
	
	public CellView(Rectangle bounds, Piece piece, boolean isCellDark, PieceDrawer drawer) 
	{
		this.setBounds(bounds);
		this.setPiece(piece);
		
		this.backroundColor = isCellDark ? DARK_BACKGROUND : LIGHT_BACKGROUND;
		this.setBackground(this.backroundColor);

		JThrower.throwIf(drawer).isNull();
		this.drawer = drawer;
	}
	
	public Piece getPiece()
	{
		return this.piece;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}

	@Override
	public void paintComponent(Graphics graphics) 
	{
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics;
		
		this.drawer.drawPiece(g, piece, this.getBounds());
	}
}
