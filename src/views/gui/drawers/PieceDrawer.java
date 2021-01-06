package views.gui.drawers;

import java.awt.Rectangle;
import java.awt.Graphics2D;

import models.pieces.Piece;

public interface PieceDrawer
{
	void drawPiece(Graphics2D g, Piece piece, Rectangle cellDimensions);
}
