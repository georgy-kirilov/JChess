package views.gui;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame
{
    public MainMenuFrame(Rectangle bounds)
    {
        setBounds(bounds);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Button startNewGameButton = new Button("New Game");
        startNewGameButton.setBounds(new Rectangle(50, 50, 150, 150));
        startNewGameButton.setVisible(true);

        setVisible(true);
        add(startNewGameButton);
    }
}
