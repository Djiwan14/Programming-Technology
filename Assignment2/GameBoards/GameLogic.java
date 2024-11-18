package GameBoards;

import java.util.Collections;

public class GameLogic {
    private int size;
    private GameField gameField;
    private int filledCells;

    public GameLogic(int size, GameField gameField) {
        this.size = size;
        this.gameField = gameField;
        this.filledCells = 0;
    }

    public boolean handlePlayerMove(int row, int col, char currentPlayer) {
        if (!gameField.getButtonText(row, col).isEmpty()) {
            return false;  // Cell already occupied
        }

        gameField.setButton(row, col, String.valueOf(currentPlayer), currentPlayer == 'X');
        filledCells++;
        return true;
    }

    public boolean checkWin(int row, int col, char player) {
        return checkDirection(row, col, player, 1, 0) ||  // Horizontal
                checkDirection(row, col, player, 0, 1) ||  // Vertical
                checkDirection(row, col, player, 1, 1) ||  // Diagonal (Top-Left to Bottom-Right)
                checkDirection(row, col, player, 1, -1);   // Diagonal (Top-Right to Bottom-Left)
    }

    private boolean checkDirection(int row, int col, char player, int rowDelta, int colDelta) {
        int count = 1;
        count += countConsecutive(row, col, player, rowDelta, colDelta);
        count += countConsecutive(row, col, player, -rowDelta, -colDelta);
        return count >= 5;
    }

    private int countConsecutive(int row, int col, char player, int rowDelta, int colDelta) {
        int count = 0;
        int newRow = row + rowDelta;
        int newCol = col + colDelta;

        while (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                gameField.getButtonText(newRow, newCol).equals(String.valueOf(player))) {
            count++;
            newRow += rowDelta;
            newCol += colDelta;
        }

        return count;
    }

    public int getMaxAdjacentCount(int row, int col, char player) {
        return Math.max(
                Math.max(countDirection(row, col, player, 1, 0), countDirection(row, col, player, 0, 1)),  // Horizontal & Vertical
                Math.max(countDirection(row, col, player, 1, 1), countDirection(row, col, player, 1, -1))  // Diagonals
        );
    }

    private int countDirection(int row, int col, char player, int rowDelta, int colDelta) {
        int count = 1;
        count += countConsecutive(row, col, player, rowDelta, colDelta);
        count += countConsecutive(row, col, player, -rowDelta, -colDelta);
        return count;
    }

    public void removeRandomSigns(char player, int signsToRemove) {
        var playerButtons = gameField.getPlayerButtons(player);

        Collections.shuffle(playerButtons);
        for (int i = 0; i < signsToRemove && !playerButtons.isEmpty(); i++) {
            gameField.clearButton(playerButtons.remove(0));
            filledCells--;
        }
    }

    public boolean isDraw() {
        return filledCells == size * size;
    }
}
