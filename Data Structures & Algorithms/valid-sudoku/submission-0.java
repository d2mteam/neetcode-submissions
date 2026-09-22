class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][] box = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }

                int num = board[i][j] - '1';

                int boxRow = i / 3;
                int boxCol = j / 3;

                int boxId = boxRow * 3 + boxCol;

                row[i][num]++;
                col[j][num]++;
                box[boxId][num]++;

                if (row[i][num] > 1 ||
                    col[j][num] > 1 ||
                    box[boxId][num] > 1) {
                    return false;
                }
            }
        }

        return true;
    }
}