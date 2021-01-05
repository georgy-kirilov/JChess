package views.gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import jthrow.JThrower;
import models.boards.Board;

@SuppressWarnings("serial")
public class BoardView extends JPanel
{
	private final Board board;
	private final CellView[][] cells;
	private final PieceDrawer drawer;
	private final int cellWidth;
	private final int cellHeight;
	
	public BoardView(Rectangle bounds, Board board, PieceDrawer drawer)
	{
		this.cellWidth = bounds.width / board.getWidth();
		this.cellHeight = bounds.height / board.getHeight();
		
		this.setBounds(bounds);
		
		JThrower.throwIf(drawer).isNull();
		this.drawer = drawer;
		
		JThrower.throwIf(board).isNull();
		this.board = board;
		
		this.cells = new CellView[board.getWidth()][board.getHeight()];
	}
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		
		for (int row = 0; row < board.getHeight(); row++)
		{
			int y = row * this.cellHeight;
			
			for (int col = 0; col < board.getWidth(); col++)
			{
				int x = col * this.cellWidth;
				boolean isCellDark = row % 2 == 0 && col % 2 == 0 ||
						row % 2 != 0 && col % 2 != 0;
				
				this.cells[row][col] = new CellView(new Rectangle(x, y, 
						this.cellWidth, this.cellHeight),
						board.getAt(row, col), 
						isCellDark, this.drawer);
						
				this.add(this.cells[row][col]);
			}
		}
	}
}
