import java.util.Scanner;

public class TicTacToe {
    private static final int Rows = 3;
    private static final int Colums = 3;
    private static String[][] board = new String[Rows][Colums];
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        //starts the main loop of playAgain
        boolean playAgain;
        // clears board when game is restarted
        do {
            clearBoard();
            String currentPlayer = "X";
            int moveCount = 0;
            boolean gameWon = false;
//then prompts user to choose where their play goes
            while (moveCount < Rows * Colums && !gameWon) {
                display();
                int rowMove = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                int colMove = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;

                // then shows message if move is invalid
                while (!isValidMove(rowMove, colMove)) {
                    System.out.println("Invalid move, The space is occupied or out of bounds");
                    rowMove = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                    colMove = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;
                }

                // keeps track of moves
                board[rowMove][colMove] = currentPlayer;
                moveCount++;

                //then finally displays the winner
                if (isWin(currentPlayer)) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameWon = true;

                    //code for tying
                } else if (isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    break;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
// then put the YNConfirm method here to prompt the user to play again
            playAgain = SafeInput.getYNConfirm(console, "Do you want to play again?");
        } while (playAgain);

        System.out.println("Thanks for playing :)");
    }


    // this will clear the board
    private static void clearBoard() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Colums; j++) {
                board[i][j] = " ";
            }
        }
    }

    // this is the method that displays the baord
    private static void display() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Colums; j++) {
                System.out.print(board[i][j]);
                if (j < Colums - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < Rows - 1) System.out.println("---------");
        }
    }

    //this confirms a valid move
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    // this is for a win by filling a row
    private static boolean isRowWin(String Player) {
        for (int i = 0; i < Rows; i++)
            if (board[i][0].equals(Player) && board[i][1].equals(Player) && board[i][2].equals(Player)) {
                return true;
            }

        return false;
    }

    //this is for if the player wins by filling a column
    private static boolean isColWin(String player) {
        for (int i = 0; i < Colums; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    //this is for if the player wins by diagonals
    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    //this is for confirming a player won
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);

    }

    //this is for if the game ties
    private static boolean isTie() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Colums; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}










