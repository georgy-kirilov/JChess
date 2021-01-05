package views.gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import common.Position;
import models.boards.Board;

public class BoardView extends JPanel
{
	private Board board;
	private CellView[][] cells;
	private final int CELL_SIDES = 60;
	
	public BoardView(Board board)
	{
		this.setBounds(10, 10, 490, 490);
		this.setVisible(true);
		this.board = board;
		cells = new CellView[board.getWidth()][board.getHeight()];
	}
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		
		for(int row = 0; row < board.getWidth(); row++)
		{
			int y = row * CELL_SIDES;
			
			for(int col = 0; col < board.getHeight(); col++)
			{
				int x = col * CELL_SIDES;
				boolean isCellDark = row % 2 == 0 && col % 2 == 0 ||
						row % 2 != 0 && col % 2 != 0;
				
				cells[row][col] = new CellView(new Rectangle(x, y, CELL_SIDES, CELL_SIDES),
						board.getAt(new Position(row, col)), isCellDark);
						
				this.add(cells[row][col]);
			}
		}
	}
}
