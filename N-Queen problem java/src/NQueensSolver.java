import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NQueensSolver {
    private int N;

    public NQueensSolver(int N) {
        this.N = N;
    }

    public List<List<String>> solveNQueens() {
        List<List<String>> solutions = new ArrayList<>();
        char[][] chessboard = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(chessboard[i], '.');
        }
        backtrackNQueens(0, chessboard, solutions);
        return solutions;
    }

    private void backtrackNQueens(int row, char[][] chessboard, List<List<String>> solutions) {
        if (row == N) {
            solutions.add(constructSolution(chessboard));
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isValidMove(row, col, chessboard)) {
                chessboard[row][col] = 'Q';
                backtrackNQueens(row + 1, chessboard, solutions);
                chessboard[row][col] = '.';
            }
        }
    }

    private boolean isValidMove(int row, int col, char[][] chessboard) {
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
            if (col - (row - i) >= 0 && chessboard[i][col - (row - i)] == 'Q') {
                return false;
            }
            if (col + (row - i) < N && chessboard[i][col + (row - i)] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> constructSolution(char[][] chessboard) {
        List<String> solution = new ArrayList<>();
        for (char[] row : chessboard) {
            solution.add(new String(row));
        }
        return solution;
    }
}
