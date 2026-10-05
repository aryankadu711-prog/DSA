class Solution {
    List<List<String>> result= new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        List<String> board= new ArrayList<>();
        for(int i=0;i<n;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<n;j++){
                sb.append('.');
            }
            board.add(sb.toString());
        }
        queens(board,0);
        return result;
    }
    public void queens(List<String> board, int row){
        if(row==board.size()){
            result.add(new ArrayList<>(board));
            return ;
        }
        for(int i=0;i<board.size();i++){
            if(isvalid(board,row,i)){
                StringBuilder newRow = new StringBuilder(board.get(row));
                newRow.setCharAt(i, 'Q');
                board.set(row, newRow.toString());
                queens(board, row + 1);
                newRow.setCharAt(i, '.');
                board.set(row, newRow.toString());
            }
        }
    }
    public boolean isvalid(List<String> board, int row, int col){
        for(int i=row;i>=0;i--){
           if( board.get(i).charAt(col)=='Q'){
            return false;
           }
        }
        for(int i=row-1, j=col-1;i>=0&&j>=0;i--,j--){
            if(board.get(i).charAt(j)=='Q'){
                return false;
            }
        }
        for(int i=row-1, j=col+1;i>=0&&j<board.size();i--,j++){
            if(board.get(i).charAt(j)=='Q'){
                return false;
            }
        }
        return true;
    }

}