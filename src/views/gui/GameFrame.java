package views.gui;

import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.BorderLayout;

import core.IOProvider;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	public GameFrame(Rectangle bounds, IOProvider gameAnnouncer)
	{
		setBounds(bounds);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add((BoardGuiView)gameAnnouncer, BorderLayout.CENTER);
		setVisible(true);		
	}
}
