package views.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import models.pieces.Piece;

public class CellView extends JComponent
{
	
	private int x;
	private int y;
	private static final int WIDTH = 40;
	private static final int HEIGHT = 40;
	private Color backround;
	Piece p;
	//add piece
	
	
	
	public CellView(int x,int y, Piece p) 
	{
		if(x>=0&&x<=7)
		this.x=x*WIDTH;
		if(y>=0&&y<=7)		
		this.y=y*HEIGHT;
		if(x%2==0&&y%2==0||x%2==1&&y%2==1) {
			backround = Color.cyan;
		}
		else backround = Color.LIGHT_GRAY;	
		if(p!=null) {
			this.p=p;
		}
	}
	
	@Override
	public void paintComponent(Graphics graphics) 
	{
		super.paintComponent(graphics);
		Graphics2D g =  (Graphics2D) graphics;
		
		g.setColor(Color.white);
		g.fill(new Rectangle2D.Double(0,0,900,900));
		g.draw(new Rectangle2D.Double(0,0, 700, 700));
		
		g.setColor(backround);
		g.fill(new Rectangle2D.Double(this.x, this.y, CellView.WIDTH, CellView.HEIGHT));
		
		TextPieceDrawer tpd = new TextPieceDrawer();
		tpd.drawPiece(g, p, new Rectangle(this.x, this.y, CellView.WIDTH, CellView.HEIGHT));
	}
	
}
