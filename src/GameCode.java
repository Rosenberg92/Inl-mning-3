import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameCode extends JFrame implements ActionListener {

    GameUI gui;
    int emptyButton = 15;

    ArrayList<Integer> numbers = new ArrayList<>();

    public GameCode() {

        gui = new GameUI(this);
        add(gui);

        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        } numbers.add(0);

        generateNumbers();

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public  void generateNumbers() {
        JButton[] buttons = gui.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            if (numbers.get(i) == 0) {
                buttons[i].setText("");
                emptyButton = i;

            } else {
                buttons[i].setText(String.valueOf(numbers.get(i)));
            }
        }
    }

    public void sortNumbers() {
        Collections.sort(numbers);
        numbers.remove((Integer)0);
        numbers.add(0);
        emptyButton = 15;
        generateNumbers();
        gui.revalidate();
        gui.repaint();
    }

    public void resetGame() {
        numbers.remove((Integer)0);
        Collections.shuffle(numbers);
        numbers.add(0);
        emptyButton = 15;
        generateNumbers();
        gui.revalidate();
        gui.repaint();
    }

    private boolean isButtonEmpty(int index) {
        int row = index / 4;
        int col = index % 4;
        int emptyRow = emptyButton / 4;
        int emptyCol = emptyButton % 4;

        return (Math.abs(row - emptyRow) + Math.abs(col - emptyCol)) == 1;
    }


    public static boolean isGameFinished(ArrayList<Integer> numbers) {
        for(int i = 0; i < numbers.size() - 1; i++) {
            if(numbers.get(i) != i + 1){
                return false;
            }
        }return numbers.getLast() == 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("Quit")) {
            System.exit(0);
        } else if (clickedButton.getText().equals("New Game")) {
            resetGame();
        } else if (clickedButton.getText().equals("Cheat")) {
            sortNumbers();
            if (isGameFinished(numbers)) {
                JOptionPane.showMessageDialog(null, "Grattis, du har vunnit!");
            }
        } else { JButton[] buttons = gui.getButtons();
            for (int i = 0; i < buttons.length; i++) {
                if (clickedButton == buttons[i] && isButtonEmpty(i)) {
                    Collections.swap(numbers, i, emptyButton);
                    emptyButton = i;
                    generateNumbers();

                    if (isGameFinished(numbers)) {
                        JOptionPane.showMessageDialog(null, "Grattis, du har vunnit!");
                    }
                    break;
                }
            }
        }
    }
}





