package views.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import common.Helper;
import common.Position;

import models.pieces.*;
import models.boards.Board;

import core.GameListener;
import core.IOProvider;

import enums.PieceColor;
import views.gui.drawers.PieceDrawer;

@SuppressWarnings("serial")
public class BoardGuiView extends JPanel implements CellViewListener, IOProvider
{	
	private final Board board;
	private final CellView[][] cells;
	private final PieceDrawer pieceDrawer;
	private final GameListener gameListener;
	private Position lastSelectedPosition;
	
	public BoardGuiView(Board board, PieceDrawer pieceDrawer)
	{
		setLayout(new GridLayout(board.getWidth(), board.getHeight()));
		
		Helper.throwIfNull(board);
		this.board = board;
		
		Helper.throwIfNull(pieceDrawer);
		this.pieceDrawer = pieceDrawer;

		gameListener = new GameListener(board, this);
		
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
			Collection<Position> reachablePositions = 
					gameListener.onFromPositionSelected(cell.getPosition());
		
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
		gameListener.onToPositionSelected(lastSelectedPosition, cell.getPosition());
		lastSelectedPosition = null;
	}
	
	@Override
	public void announceCheck()
	{
		JOptionPane.showMessageDialog(this, "CHECK");
	}
	
	@Override
	public void announceGameOver(PieceColor winnerColor)
	{	
		String format = "GAME OVER - %s WINS!";
		String message = String.format(format, winnerColor.toString().toUpperCase());
		JOptionPane.showMessageDialog(this, message);
		System.exit(0);
	}
	
	@Override
	public Piece announcePawnPromotion(PieceColor pawnColor)
	{		
		String separator = "  ";
		
		String[] options = new String[] 
		{ 
			"[Q]ueen", 
			"[R]ook", 
			"[K]night", 
			"[B]ishop",
		};
		
		String message = String.join(separator, options);
		
		while (true)
		{
			String input = JOptionPane.showInputDialog(this, message);
			
			if (!Helper.isNullOrEmpty(input) && input.length() == 1)
			{
				char firstChar = input.charAt(0);
				
				if (Helper.isBishopChar(firstChar))
				{
					return new Bishop(pawnColor);					
				}
				
				if (Helper.isKnightChar(firstChar))
				{
					return new Knight(pawnColor);					
				}
				
				if (Helper.isQueenChar(firstChar))
				{
					return new Queen(pawnColor);					
				}
				
				if (Helper.isRookChar(firstChar))
				{
					return new Rook(pawnColor);			
				}
			}
		}
	}
	
	@Override
	public void announceCastlingPositions(Collection<Position> positions)
	{
		for (Position position : positions)
		{
			getAt(position).setCastlable(true);
		}
	}
	
	@Override
	public void redrawBoard()
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
				boolean cellBackgroundDark = 
						!(row % 2 == 0 && col % 2 == 0 || row % 2 != 0 && col % 2 != 0);
				
				cells[row][col] = new CellView(
						piece, cellBackgroundDark, 
						pieceDrawer, this, new Position(row, col));
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
				cell.setCastlable(false);
			}		
		}
	}
	
	private CellView getAt(Position position)
	{
		return cells[position.getRow()][position.getColumn()];
	}
}
