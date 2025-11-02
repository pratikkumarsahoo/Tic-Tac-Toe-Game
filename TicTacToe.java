import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain;

        System.out.println("🎮 Welcome to Two-Player Tic-Tac-Toe Game!");
        

        do {
            initializeBoard();
            char currentPlayer = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] and column[1-3]): ");
                int row = sc.nextInt() - 1;
                int col = sc.nextInt() - 1;

                // Validate move
                if (row < 0 || col < 0 || row > 2 || col > 2) {
                    System.out.println("Invalid position! Please enter values between 1 and 3.");
                    continue;
                }

                if (board[row][col] != '-') {
                    System.out.println("This position is already taken. Try again!");
                    continue;
                }

                board[row][col] = currentPlayer;

                // Check for winner
                if (checkWinner(currentPlayer)) {
                    printBoard();
                    System.out.println("🎉 Player " + currentPlayer + " wins!");
                    gameOver = true;
                } 
                // Check for draw
                else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw! 🤝");
                    gameOver = true;
                } 
                else {
                    // Switch player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            // Ask to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String response = sc.next().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        System.out.println("Thanks for playing Tic-Tac-Toe! 👋");
        sc.close();
    }

    // Initialize the board with '-'
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current board
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Check if board is full
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for winning conditions
    public static boolean checkWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }
}
