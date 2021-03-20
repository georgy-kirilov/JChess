package views.gui;

import core.GuiIoProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuFrame extends JFrame
{
    private final Rectangle MAIN_MENU_FRAME_BOUNDS = new Rectangle(0, 0, 640, 640);

    private Button startNewGameButton;

    public MainMenuFrame(GuiIoProvider guiIoProvider)
    {
        setBounds(MAIN_MENU_FRAME_BOUNDS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        initializeStartNewGameButton(guiIoProvider);
        setVisible(true);
    }

    private void initializeStartNewGameButton(GuiIoProvider guiIoProvider)
    {
        startNewGameButton = new Button("New Game");
        startNewGameButton.setBounds(new Rectangle(50, 50, 150, 150));
        startNewGameButton.setVisible(true);

        MainMenuFrame mainMenuFrame = this;

        startNewGameButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                mainMenuFrame.setVisible(false);
                GameFrame gameFrame = new GameFrame(guiIoProvider);
                gameFrame.setVisible(true);
            }
        });

        add(startNewGameButton);
    }
}
