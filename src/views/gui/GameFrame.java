package views.gui;

import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.BorderLayout;

import core.GuiIoProvider;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	private final Rectangle GAME_FRAME_BOUNDS = new Rectangle(0, 0, 640, 640);

	public GameFrame(GuiIoProvider guiIoProvider)
	{
		setBounds(GAME_FRAME_BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(guiIoProvider, BorderLayout.CENTER);
	}
}
