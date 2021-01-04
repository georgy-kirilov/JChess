package views.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

import models.pieces.Piece;

public class CellView extends JPanel
{
	private Color backround;
	private Piece p;
	
	
	
	
	public CellView(Rectangle bounds, Piece p, boolean isCellDark) 
	{
		this.setBounds(bounds);
		
		
		if(isCellDark) 
		{
			backround = Color.cyan;
		}
		else backround = Color.LIGHT_GRAY;
		
		this.p=p;
		
	}
	
	@Override
	public void paintComponent(Graphics graphics) 
	{
		super.paintComponent(graphics);
		Graphics2D g =  (Graphics2D) graphics;
		
		
		
		this.setBackground(backround);
		
		TextPieceDrawer tpd = new TextPieceDrawer();
		tpd.drawPiece(g, p, this.getBounds());
	}
	
}
