package views.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import common.Position;
import models.pieces.Piece;
import validation.ThrowHelper;
import views.gui.drawers.PieceDrawer;

@SuppressWarnings("serial")
public class CellView extends JPanel
{
	private final Color DARK_BACKGROUND = new Color(122,122,122);
	private final Color LIGHT_BACKGROUND = new Color(192,192,192);
	private final Color DARK_HIGHLIGHT = new Color(0, 174, 116);
	private final Color LIGHT_HIGHLIGHT = new Color(0, 225, 142);
	
	private Piece piece;
	private boolean highlighted;
	
	private final Position position;
	private final PieceDrawer drawer;
	private final CellViewListener listener;
	private final boolean isBackgroundDark;
	
	public CellView(
			Rectangle bounds, 
			Piece piece, 
			boolean isBackgroundDark, 
			PieceDrawer drawer, 
			CellViewListener listener, 
			Position position)
	{
		setBounds(bounds);
		setPiece(piece);
		setHighlighted(false);
		
		this.isBackgroundDark = isBackgroundDark;

		ThrowHelper.throwIfNull(drawer);
		this.drawer = drawer;
		
		ThrowHelper.throwIfNull(listener);
		this.listener = listener;
		
		ThrowHelper.throwIfNull(position);
		this.position = position;
		
		attachClickListener(this);
	}
	
	public Piece getPiece()
	{ 
		return piece; 
	}
	
	public void setPiece(Piece piece) 
	{ 
		this.piece = piece; 
	}

	public boolean isHighlighted() 
	{ 
		return highlighted; 
	}
	
	public void setHighlighted(boolean highlighted) 
	{ 
		this.highlighted = highlighted; 
	}
	
	public Position getPosition()
	{ 
		return position; 
	}
	
	@Override
	public void paintComponent(Graphics graphics) 
	{
		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D)graphics;
		Color color = isBackgroundDark ? DARK_BACKGROUND : LIGHT_BACKGROUND;
		
		if (isHighlighted())
			color = isBackgroundDark ? DARK_HIGHLIGHT : LIGHT_HIGHLIGHT;
		
		setBackground(color);
		drawer.drawPiece(g, piece, getWidth(), getHeight());
	}
	
	private void attachClickListener(CellView cell)
	{
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (!cell.isHighlighted())
					cell.listener.onInitialClick(cell);
				else
					cell.listener.onConfirmationClick(cell);
			}
		});
	}
}
