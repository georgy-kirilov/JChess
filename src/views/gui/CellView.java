package views.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import common.Position;
import jthrow.JThrower;
import models.pieces.Piece;
import views.gui.drawers.PieceDrawer;

@SuppressWarnings("serial")
public class CellView extends JPanel
{
	private static final Color DARK_BACKGROUND = new Color(122,122,122);
	private static final Color LIGHT_BACKGROUND = new Color(192,192,192);
	private static final Color DARK_HIGHLIGHT = new Color(0, 174, 116);
	private static final Color LIGHT_HIGHLIGHT = Color.GREEN;
	
	private Piece piece;
	private boolean highlighted;
	
	private final PieceDrawer drawer;
	private final CellViewListener listener;
	private final Position position;
	private final boolean isBackgroundDark;
	
	public CellView(
			Rectangle bounds, 
			Piece piece, 
			boolean isBackgroundDark, 
			PieceDrawer drawer, 
			CellViewListener listener, 
			Position position)
	{
		this.setBounds(bounds);
		this.setPiece(piece);
		this.setHighlighted(false);
		
		this.isBackgroundDark = isBackgroundDark;

		JThrower.throwIf(drawer).isNull();
		this.drawer = drawer;
		
		JThrower.throwIf(listener).isNull();
		this.listener = listener;
		
		JThrower.throwIf(position).isNull();
		this.position = position;
		
		this.attachClickListener(this);
	}
	
	public Piece getPiece() { return this.piece; }
	
	public void setPiece(Piece piece) { this.piece = piece; }

	public boolean isHighlighted() { return this.highlighted; }
	
	public void setHighlighted(boolean highlighted) { this.highlighted = highlighted; }
	
	public Position getPosition() { return this.position; }
	
	@Override
	public void paintComponent(Graphics graphics) 
	{
		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D)graphics;
		Color color = this.isBackgroundDark ? DARK_BACKGROUND : LIGHT_BACKGROUND;
		
		if (this.isHighlighted())
		{
			color = this.isBackgroundDark ? DARK_HIGHLIGHT : LIGHT_HIGHLIGHT;
		}
		
		this.setBackground(color);
		this.drawer.drawPiece(g, piece, this.getBounds());
	}
	
	private void attachClickListener(CellView cell)
	{
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (!cell.isHighlighted())
				{
					cell.listener.onInitialClick(cell);
				}
				else
				{
					cell.listener.onConfirmationClick(cell);
				}
			}
		});
	}
}
