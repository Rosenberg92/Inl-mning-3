import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameUI extends JPanel {

    JButton startButton = new JButton("New Game");
    JButton quitButton = new JButton("Quit");
    JButton winButton = new JButton("Cheat");
    JButton[] buttons = new JButton[16];
    JPanel gridPanel = new JPanel();
    JPanel panel = new JPanel();

    public GameUI(ActionListener listener) {

        setLayout(new BorderLayout());

        panel.setLayout(new FlowLayout());
        gridPanel.setLayout(new GridLayout(4, 4));
        add(panel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        panel.add(startButton);
        panel.add(winButton);
        panel.add(quitButton);

        startButton.addActionListener(listener);
        quitButton.addActionListener(listener);
        winButton.addActionListener(listener);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(listener);
            gridPanel.add(buttons[i]);
        }
    }

    public JButton[] getButtons() {
        return buttons;
    }
}
