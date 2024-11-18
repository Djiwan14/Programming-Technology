package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningPage {
    private JFrame frame;
    private JButton button;
    private JLabel label;

    public OpeningPage() {
        createFrame();
        createLabel(frame);
        createButton(frame);
        frame.setVisible(true); // Make visible after all components are added |
    }

    public JFrame createFrame() {
        frame = new JFrame("Tricky five-in-row");
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    public void createLabel(JFrame frame) {
        label = new JLabel("Welcome to the game", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
        label.setForeground(Color.WHITE);
        label.setOpaque(true); // Allow background color to be visible |
        label.setBackground(Color.DARK_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add a white border |
        label.setBounds((frame.getWidth() - 300) / 2, 150, 300, 40); // Centered manually |
        frame.add(label);
    }

    public void createButton(JFrame frame) {
        button = new JButton("Click to Start");
        button.setFont(new Font("Courier New", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add border |
        button.setBounds((frame.getWidth() - 150) / 2, 200, 150, 40); // Centering manually |

        // when we press on the button we open new page with 3 types of maps of the game |
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the opening page
                new ChooseGamePage(); // Open the map selection page
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);// Change background on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.BLUE); // Restore original color
            }
        });

        frame.add(button);
    }
}
