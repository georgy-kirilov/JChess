package views.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import common.Helper;
import common.Position;

import models.pieces.Piece;
import views.gui.drawers.PieceDrawer;

@SuppressWarnings("serial")
public class CellView extends JPanel
{
	private final Color DARK_BACKGROUND = new Color(122,122,122);
	private final Color LIGHT_BACKGROUND = new Color(192,192,192);
	private final Color DARK_HIGHLIGHT = new Color(0, 174, 116);
	private final Color LIGHT_HIGHLIGHT = new Color(0, 225, 142);
	private final Color CASTLE_HIGHLIGHT = Color.YELLOW;
	
	private Piece piece;
	private boolean highlighted;
	private boolean castlable;
	
	private final Position position;
	private final PieceDrawer pieceDrawer;
	private final CellViewListener cellViewListener;
	private final boolean backgroundDark;
	
	public CellView(
			Piece piece, 
			boolean backgroundDark, 
			PieceDrawer pieceDrawer, 
			CellViewListener cellViewListener, 
			Position position)
	{
		setPiece(piece);
		setHighlighted(false);
		
		this.backgroundDark = backgroundDark;

		Helper.throwIfNull(pieceDrawer);
		this.pieceDrawer = pieceDrawer;
		
		Helper.throwIfNull(cellViewListener);
		this.cellViewListener = cellViewListener;
		
		Helper.throwIfNull(position);
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
	
	public boolean isCastlable()
	{
		return castlable;
	}
	
	public void setCastlable(boolean castlable)
	{
		this.castlable = castlable;
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
		Color color = backgroundDark ? DARK_BACKGROUND : LIGHT_BACKGROUND;
		
		if (isHighlighted())
		{			
			color = backgroundDark ? DARK_HIGHLIGHT : LIGHT_HIGHLIGHT;
			
			if (isCastlable())
			{
				color = CASTLE_HIGHLIGHT;				
			}
		}
		
		setBackground(color);
		pieceDrawer.drawPiece(g, piece, getWidth(), getHeight());
	}
	
	private void attachClickListener(CellView cell)
	{
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (!cell.isHighlighted())
				{
					cell.cellViewListener.onInitialClick(cell);										
				}
				else
				{
					cell.cellViewListener.onConfirmationClick(cell);					
				}
			}
		});
	}
}
