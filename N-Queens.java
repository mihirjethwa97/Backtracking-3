// Time Complexity : O(n!)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

class Solution {
    List<List<String>> result;

    private String genString(int idx, int n){
        String result = "";
        for(int i=0;i<n;i++){
            if(i==idx) result+="Q";
            else result+=".";
        }
        return result;
    }

    private boolean[][] updateBoard(boolean[][] in, int n, int row, int col){
        boolean[][] copy = deepCopy(in, n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==row || j == col) copy[i][j] = false;
            }
        }
        int r = row;
        int c = col;
        while(r<n && c<n){
            copy[r][c] = false;
            r++;
            c++;
        }
        r = row;
        c = col;
        while(r< n && r>=0 &&c <n){
            copy[r][c] = false;
            r--;
            c++;
        }
        r= row;
        c = col;
        while(r<n && c<n && r >= 0 && c >= 0){
            copy[r][c] = false;
            r--;
            c--;
        }
        r = row;
        c = col;
        while(r<n && c<n && r >= 0 && c >= 0){
            copy[r][c] = false;
            r++;
            c--;
        }
        return copy;
    }

    private boolean[][] deepCopy(boolean[][] in, int n){
        boolean[][] out = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                out[i][j] = in[i][j];
            }
        }
        return out;
    }

    private void rec(boolean[][] board, ArrayList<Integer> places, int n, int i){
        //base
        if(places.size() == n) {
            ArrayList<String> list = new ArrayList<>();
            for(int k=0;k<n;k++){
                list.add(genString(places.get(k), n));
            }
            result.add(list);
            return;
        }
        //logic
        //creating deep copy of earlier board
        boolean [][] copy = deepCopy(board, n);
        for(int j=0;j<n;j++){
            //check if the value for j if its true
            if(copy[i][j] == true){
                places.add(j);
                boolean[][] updated = updateBoard(copy, n, i, j);
                rec(updated, places, n, i+1);
                places.remove(places.size()-1);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        boolean[][] board = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j] = true;
            }
        }
        // boolean[][] test = new boolean[][]{{true, true, true, true},{true, true, true, true},{true, true, true, true},{true, true, true, true}};
        // boolean[][] check = updateBoard(test, n, 2, 2);
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         System.out.print(check[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        rec(board, new ArrayList<>(), n, 0);
        return result;
    }
}
