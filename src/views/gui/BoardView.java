package views.gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import common.Position;
import enums.GameStatus;
import jthrow.JThrower;
import models.boards.Board;
import models.pieces.Piece;
import views.gui.drawers.PieceDrawer;

@SuppressWarnings("serial")
public class BoardView extends JPanel implements CellViewListener
{
	private final int cellWidth;
	private final int cellHeight;
	
	private final Board board;
	private final CellView[][] cells;
	private final PieceDrawer drawer;
	private final GameListener listener;
	private Position lastSelectedPosition;
	
	public BoardView(Rectangle bounds, Board board, PieceDrawer drawer, GameListener listener)
	{
		cellWidth = bounds.width / board.getWidth();
		cellHeight = bounds.height / board.getHeight();
		
		setBounds(bounds);
		setLayout(null);
		
		JThrower.throwIf(board).isNull();
		this.board = board;
		
		JThrower.throwIf(drawer).isNull();
		this.drawer = drawer;
		
		JThrower.throwIf(listener).isNull();
		this.listener = listener;
		
		cells = new CellView[board.getWidth()][board.getHeight()];
		initialize();
	}
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
	
		for (int i = 0; i < board.getHeight(); i++)
		{	
			for (int j = 0; j < board.getWidth(); j++)
			{
				cells[i][j].setPiece(board.getAt(i, j));
				add(cells[i][j]);
			}
		}
	}
	
	@Override
	public void onInitialClick(CellView cell)
	{
		unhighlightAllCells();
		
		Iterable<Position> reachablePositions = listener.onFromPositionSelected(cell.getPosition());
		
		for (Position position : reachablePositions)
		{
			getAt(position).setHighlighted(true);
		}
		
		lastSelectedPosition = cell.getPosition(); 
		repaint();
	}

	@Override
	public void onConfirmationClick(CellView cell)
	{
		GameStatus status = listener.onToPositionSelected(lastSelectedPosition, cell.getPosition());
		unhighlightAllCells();
		repaint();
		
		if (status == GameStatus.CHECKMATE)
		{
			JOptionPane.showMessageDialog(this, "CHECKMATE! YOU LOST!");
			System.exit(0);
		}
		else if (status == GameStatus.CHECK)
		{
			JOptionPane.showMessageDialog(this, "CHECK");
		}
	}
	
	private void initialize()
	{
		for (int row = 0; row < board.getHeight(); row++)
		{
			int y = row * cellHeight;
			
			for (int col = 0; col < board.getWidth(); col++)
			{
				int x = col * cellWidth;
				
				Rectangle bounds = new Rectangle(x, y, cellWidth, cellHeight);
				
				Piece piece = board.getAt(row, col);
				
				boolean isCellDark = row % 2 == 0 && col % 2 == 0 || row % 2 != 0 && col % 2 != 0;
				
				cells[row][col] = new CellView(
						bounds, piece, isCellDark, 
						drawer, this, new Position(row, col));
			}
		}
	}
	
	private void unhighlightAllCells()
	{
		for (CellView[] cellRow : cells)
		{
			for (CellView cell : cellRow)
			{
				cell.setHighlighted(false);
			}
		}
	}
	
	private CellView getAt(Position position)
	{
		return cells[position.getRow()][position.getColumn()];
	}
}
