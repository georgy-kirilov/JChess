package views.gui;

import common.Position;
import enums.GameStatus;

public interface GameListener
{
	Iterable<Position> onFromPositionSelected(Position from);
	
	GameStatus onToPositionSelected(Position from, Position to);
}
