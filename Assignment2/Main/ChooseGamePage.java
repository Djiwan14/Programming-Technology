package Main;

import GameBoards.EasyField;
import GameBoards.MediumField;
import GameBoards.HardField;

import javax.swing.*;
import java.awt.*;

public class ChooseGamePage {
    private JFrame frame;

    public ChooseGamePage(){
        createFrame();

        createButtons();
        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame("Select Board Size");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, with spacing
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createButtons() {
        JButton easyButton = new JButton("6x6 - Easy");
        JButton mediumButton = new JButton("10x10 - Medium");
        JButton hardButton = new JButton("14x14 - Hard");

        easyButton.addActionListener(e -> {
            new EasyField(); // Creates an EasyField board |
            frame.dispose();
        });
        mediumButton.addActionListener(e -> {
            new MediumField(); // Creates a MediumField board |
            frame.dispose();
        });

        hardButton.addActionListener(e -> {
            new HardField(); // Creates a HardField board |
            frame.dispose();
        });

        frame.add(easyButton);
        frame.add(mediumButton);
        frame.add(hardButton);
    }

}
