package views.gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		this.cellWidth = bounds.width / board.getWidth();
		this.cellHeight = bounds.height / board.getHeight();
		
		this.setBounds(bounds);
		this.setLayout(null);
		
		JThrower.throwIf(board).isNull();
		this.board = board;
		
		JThrower.throwIf(drawer).isNull();
		this.drawer = drawer;
		
		JThrower.throwIf(listener).isNull();
		this.listener = listener;
		
		this.cells = new CellView[board.getWidth()][board.getHeight()];
		this.initialize();
	}
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
	
		for (int row = 0; row < board.getHeight(); row++)
		{	
			for (int col = 0; col < board.getWidth(); col++)
			{
				this.cells[row][col].setPiece(this.board.getAt(row, col));
				this.add(this.cells[row][col]);
			}
		}
	}
	
	@Override
	public void onInitialClick(CellView cell)
	{
		this.unhighlightAllCells();
		
		Iterable<Position> reachablePositions = 
				this.listener.onFromPositionSelected(cell.getPosition());
		
		for (Position position : reachablePositions)
		{
			this.getAt(position).setHighlighted(true);
		}
		
		this.lastSelectedPosition = cell.getPosition(); 
		this.repaint();
	}

	@Override
	public void onConfirmationClick(CellView cell)
	{
		GameStatus status = this.listener.onToPositionSelected(this.lastSelectedPosition, cell.getPosition());
		this.unhighlightAllCells();
		this.repaint();
		
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
			int y = row * this.cellHeight;
			
			for (int col = 0; col < board.getWidth(); col++)
			{
				int x = col * this.cellWidth;
				
				Rectangle bounds = new Rectangle(x, y, this.cellWidth, this.cellHeight);
				
				Piece piece = this.board.getAt(row, col);
				
				boolean isCellDark = !(row % 2 == 0 && col % 2 == 0 || row % 2 != 0 && col % 2 != 0);
				
				this.cells[row][col] = new CellView(
						bounds, piece, isCellDark, 
						this.drawer, this, new Position(row, col));
			}
		}
	}
	
	private void unhighlightAllCells()
	{
		for (CellView[] cellRow : this.cells)
		{
			for (CellView cell : cellRow)
			{
				cell.setHighlighted(false);
			}
		}
	}
	
	private CellView getAt(Position position)
	{
		return this.cells[position.getRow()][position.getColumn()];
	}
}
