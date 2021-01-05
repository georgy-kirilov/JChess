package views.gui;

import java.awt.Color;
import java.awt.Rectangle;

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
		for(int i = 0; i <= board.getHeight(); i++)
		{
			for(int j = 0; j <= board.getWidth(); j++)
			{
				boolean check = true;
				cells[i][j] = new CellView(new Rectangle(0, 0, board.getHeight(), board.getWidth()),
						board.getAt(new Position(i, j)), check);
				
				if(i == 0 || i == 2 || i == 4 || i == 6)
				{
					if(i % 2 == 0 && j % 2 == 0)
					{
						check = true;
					}
					else check = false;
				}
				
				if(i == 1 || i == 3 || i == 5 || i == 7)
				{
					if(i % 2 != 0 && j % 2 != 0)
					{
						check = true;
					}
					else check = false;
				}		
			}
		}
	}
}
