package core;

import java.util.Collection;

import common.Position;
import enums.PieceColor;
import models.pieces.Piece;

public interface IOProvider
{
	void announceCheck();
	
	void announceGameOver(PieceColor winnerColor);
	
	Piece announcePawnPromotion(PieceColor pawnColor);
	
	void announceCastlingPositions(Collection<Position> positions);
	
	void redrawBoard();
}
