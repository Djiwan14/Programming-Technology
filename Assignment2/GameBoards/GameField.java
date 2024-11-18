package GameBoards;

import Main.ChooseGamePage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameField {
    protected int size;
    protected JFrame frame;
    protected JPanel boardPanel;
    protected JButton[][] buttons;
    protected JLabel activePlayer;
    protected JButton backButton;
    protected char currentPlayer = 'X';
    private GameLogic gameLogic;

    public GameField(int size) {
        this.size = size;
        createFrame();
        createBoard();
        createTopBoard();
        gameLogic = new GameLogic(size, this);
    }

    private void createFrame() {
        frame = new JFrame("Game Board: " + size + "x" + size);
        frame.setSize(600, 650);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void createBoard() {
        boardPanel = new JPanel(new GridLayout(size, size));
        buttons = new JButton[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setPreferredSize(new Dimension(600 / size, 600 / size));

                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> handlePlayerMove(finalI, finalJ));
                boardPanel.add(buttons[i][j]);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void createTopBoard() {
        JPanel topPanel = new JPanel(new BorderLayout());

        activePlayer = new JLabel("Player X's Turn", SwingConstants.CENTER);
        activePlayer.setFont(new Font("Verdana", Font.BOLD, 16));
        topPanel.add(activePlayer, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> navigateBack());
        topPanel.add(backButton, BorderLayout.WEST);

        frame.add(topPanel, BorderLayout.NORTH);
    }

    private void navigateBack() {
        frame.dispose();
        new ChooseGamePage();
    }

    private void handlePlayerMove(int row, int col) {
        if (!gameLogic.handlePlayerMove(row, col, currentPlayer)) return;

        if (gameLogic.checkWin(row, col, currentPlayer)) {
            showEndGameMessage("Player " + currentPlayer + " wins!");
            return;
        }

        int adjacentCount = gameLogic.getMaxAdjacentCount(row, col, currentPlayer);
        if (adjacentCount == 3) {
            gameLogic.removeRandomSigns(currentPlayer, 1);
        } else if (adjacentCount == 4) {
            gameLogic.removeRandomSigns(currentPlayer, 2);
        }

        if (gameLogic.isDraw()) {
            showEndGameMessage("It's a draw!");
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        activePlayer.setText("Player " + currentPlayer + "'s Turn");
    }

    private void showEndGameMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);

        int response = JOptionPane.showConfirmDialog(frame, "Would you like to restart?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            frame.dispose();
            new ChooseGamePage();
        } else {
            frame.dispose();
        }
    }

    public String getButtonText(int row, int col) {
        return buttons[row][col].getText();
    }

    public void setButton(int row, int col, String text, boolean isPlayerX) {
        buttons[row][col].setText(text);
        buttons[row][col].setBackground(isPlayerX ? Color.GREEN : Color.RED);
    }

    public List<JButton> getPlayerButtons(char player) {
        List<JButton> playerButtons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (buttons[i][j].getText().equals(String.valueOf(player))) {
                    playerButtons.add(buttons[i][j]);
                }
            }
        }
        return playerButtons;
    }

    public void clearButton(JButton button) {
        button.setText("");
        button.setBackground(Color.WHITE);
    }
}
