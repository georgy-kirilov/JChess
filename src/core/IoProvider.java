package core;

import java.util.Collection;

import common.Position;

import enums.PieceColor;
import enums.DrawStatus;

import models.pieces.Piece;

public interface IoProvider
{
	void announceCheck();
	
	void announceCheckmate(PieceColor winnerColor);
	
	Piece announcePawnPromotion(PieceColor pawnColor);
	
	void announceCastlingPositions(Collection<Position> positions);
	
	void announceDraw(DrawStatus reasonForDraw);
	
	void redrawBoard();
}
