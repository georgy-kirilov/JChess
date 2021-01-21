package views.gui;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import common.Helper;
import common.Position;
import enums.PieceColor;
import core.GameListener;
import models.boards.Board;
import models.pieces.Bishop;
import models.pieces.Knight;
import models.pieces.Piece;
import models.pieces.Queen;
import models.pieces.Rook;
import views.gui.drawers.PieceDrawer;

@SuppressWarnings("serial")
public class BoardView extends JPanel implements CellViewListener
{	
	private final Board board;
	private final CellView[][] cells;
	private final PieceDrawer drawer;
	private final GameListener listener;
	private Position lastSelectedPosition;
	
	public BoardView(Board board, PieceDrawer drawer)
	{
		setLayout(new GridLayout(board.getWidth(), board.getHeight()));
		
		Helper.throwIfNull(board);
		this.board = board;
		
		Helper.throwIfNull(drawer);
		this.drawer = drawer;

		listener = new GameListener(board, this);
		
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
		
		revalidate();
	}
	
	@Override
	public void onInitialClick(CellView cell)
	{
		unhighlightAllCells();
		
		if (cell.getPosition().equals(lastSelectedPosition))
		{
			lastSelectedPosition = null;
		}
		else
		{
			Iterable<Position> reachablePositions = 
					listener.onFromPositionSelected(cell.getPosition());
		
			for (Position position : reachablePositions)
			{
				getAt(position).setHighlighted(true);
			}
			
			lastSelectedPosition = cell.getPosition();
		}
		
		repaint();
	}

	@Override
	public void onConfirmationClick(CellView cell)
	{
		listener.onToPositionSelected(lastSelectedPosition, cell.getPosition());
		lastSelectedPosition = null;
	}
	
	public void announceCheck()
	{
		JOptionPane.showMessageDialog(this, "CHECKED");
	}
	
	public void announceGameOver(PieceColor winnerColor)
	{	
		JOptionPane.showMessageDialog(this, "GAME OVER - " + winnerColor + " WINS!");
		System.exit(0);
	}
	
	public Piece announcePawnPromotion(PieceColor pawnColor)
	{
		char firstChar = '\0';
		String message = "Rook / Bishop / Queen / Knight";
		
		while (true)
		{
			String input = JOptionPane.showInputDialog(this, message);
			
			if (!Helper.isNullOrEmpty(input))
			{
				firstChar = input.charAt(0);
				
				if (Helper.isBishopChar(firstChar))
					return new Bishop(pawnColor);
				
				if (Helper.isKnightChar(firstChar))
					return new Knight(pawnColor);
				
				if (Helper.isQueenChar(firstChar))
					return new Queen(pawnColor);
				
				if (Helper.isRookChar(firstChar))
					return new Rook(pawnColor);
			}
		}
	}
	
	public void redraw()
	{
		unhighlightAllCells();
		repaint();
	}
	
	private void initialize()
	{
		for (int row = 0; row < board.getHeight(); row++)
		{	
			for (int col = 0; col < board.getWidth(); col++)
			{	
				Piece piece = board.getAt(row, col);
				boolean isCellDark = !(row % 2 == 0 && col % 2 == 0 || row % 2 != 0 && col % 2 != 0);
				
				cells[row][col] = new CellView(piece, isCellDark, 
						drawer, this, new Position(row, col));
			}
		}
	}
	
	private void unhighlightAllCells()
	{
		for (CellView[] cellRow : cells)
			for (CellView cell : cellRow)
				cell.setHighlighted(false);
	}
	
	private CellView getAt(Position position)
	{
		return cells[position.getRow()][position.getColumn()];
	}
}
