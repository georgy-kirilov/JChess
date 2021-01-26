package views.gui;

import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.BorderLayout;

import core.GuiIoProvider;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	public GameFrame(Rectangle bounds, GuiIoProvider guiIoProvider)
	{
		setBounds(bounds);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(guiIoProvider, BorderLayout.CENTER);
		setVisible(true);
	}
}
