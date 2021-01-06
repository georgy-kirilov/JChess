package views.gui;

import common.Position;

public interface GameListener
{
	Iterable<Position> onFromPositionSelected(Position from);
	
	void onToPositionSelected(Position from, Position to);
}
