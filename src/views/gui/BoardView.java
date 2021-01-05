package views.gui;

import java.awt.Color;

import javax.swing.JPanel;

import common.Position;
import models.boards.Board;

public class BoardView extends JPanel
{
	private Board board;
	private CellView[][] cells;
	
	public BoardView(Board board)
	{
		this.board = board;
		cells = new CellView[board.getHeight()][board.getWidth()];
	}
	
	private void paint()
	{		
		for(int i = 0; i < board.getHeight(); i++)
		{
			for(int j = 0; j < board.getWidth(); j++)
			{
				cells[i][j] = new CellView(null, board.getAt(new Position(i, j)), false);
				
				if(i % 2 == 0 && j % 2 == 0)
				{
					cells[i][j].setBackground(new Color(92, 92, 92));
				}
				else cells[i][j].setBackground(new Color(251, 249, 198));
			}
		}
		
	}
}
