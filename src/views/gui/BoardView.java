package views.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import common.Position;
import models.boards.Board;

public class BoardView extends JPanel
{
	private Board board;
	private CellView[][] cells;
	private final int CELL_WH = 60;
	
	public BoardView(Board board)
	{
		this.board = board;
		cells = new CellView[board.getHeight()][board.getWidth()];
	}
	
	public void paintComponent(Graphics graphics)
	{	
		super.paintComponent(graphics);
		
		for(int i = 0; i < board.getHeight(); i++)
		{
			int y = i * CELL_WH;
			
			for(int j = 0; j < board.getWidth(); j++)
			{
				int x = j * CELL_WH;
				boolean isCellDark = i % 2 == 0 && j % 2 == 0 || i % 2 != 0 && j % 2 != 0;
				
				cells[i][j] = new CellView(new Rectangle(x, y, CELL_WH, CELL_WH),
						board.getAt(new Position(i, j)), isCellDark);
				
				this.add(cells[i][j]);
			}
		}
	}
}
