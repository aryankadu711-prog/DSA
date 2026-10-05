import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        char[][] board = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; 
        boolean[] diag2 = new boolean[2 * n]; 
        
        backtrack(results, board, 0, n, cols, diag1, diag2);
        return results;
    }
    
    private void backtrack(List<List<String>> res, char[][] board, int row, int n, 
                           boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            List<String> current = new ArrayList<>();
            for (char[] r : board) {
                current.add(new String(r));
            }
            res.add(current);
            return;
        }
        
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n; 
            int d2 = row + col;     
            
            if (cols[col] || diag1[d1] || diag2[d2]) continue;
            
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;
            
            backtrack(res, board, row + 1, n, cols, diag1, diag2);
            
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }
}