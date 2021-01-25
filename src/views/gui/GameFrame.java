package views.gui;

import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	public GameFrame(Rectangle bounds, BoardView boardView)
	{
		setBounds(bounds);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(boardView, BorderLayout.CENTER);
		setVisible(true);
	}
}
