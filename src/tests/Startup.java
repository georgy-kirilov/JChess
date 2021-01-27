package tests;

import core.GuiChessEngine;

import models.boards.*;
import views.gui.BoardGuiView;
import views.gui.drawers.TextPieceDrawer;

public class Startup
{	
	public static void main(String[] args) 
	{
		new GuiChessEngine(new BoardGuiView(new FischerRandomBoard(), new TextPieceDrawer()));
	}
}
