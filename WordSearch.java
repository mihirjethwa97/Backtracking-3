// Time Complexity : O(M*N*3^k)
// Space Complexity : O(k)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

class Solution {
    boolean flag = false;
    int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1},{-1, 0}};
    int m;
    int n;
    private void dfs(char[][] board,int wordIdx, String word,int row,int col){
        if(wordIdx == word.length()) {
            flag = true;
            return;
        }
        if(flag) return;
        for(int i = 0 ;i<dirs.length ; i++){
            int nr = row + dirs[i][0];
            int nc = col + dirs[i][1];
            if(nr >= 0 && nr < m && nc >=0 && nc < n){
                char c = word.charAt(wordIdx);           
                if(board[nr][nc] == c){
                    board[nr][nc] = 1;
                    dfs(board, wordIdx+1 , word, nr, nc);
                    board[nr][nc] = c;
                }
            }
        }
        return;
    }
    public boolean exist(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;

        for(int i=0;i<m;i++){
            for(int j = 0; j<n;j++){
                if(board[i][j] == word.charAt(0)){
                    board[i][j] = 1;
                    dfs(board, 1, word, i, j);
                    board[i][j] = word.charAt(0);
                    if(flag) return true;
                }
            }
        }
        return false;
    }
}
