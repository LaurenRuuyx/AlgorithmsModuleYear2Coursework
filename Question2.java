public class Question2 {

    public static void main(String[] args){
        Character[][] board = new Character[8][8];
        for(int i=0; i<board.length; ++i){
            for(int j=0; j<board[0].length; ++j){
                if(i==0){
                    board[i][j] = 'Q';
                }
                else{
                    board[i][j] = '.';
                }
            }
        }
        System.out.println(validboard(board));

    }
    

    public static boolean validboard(Character[][] board){
        if(board == null){
            return false;
        }
        if(board.length != 8){
            return false;
        }
        if(board[0].length != 8){
            return false;
        }
        int queenCount = 0;
        for(int i=0; i<board.length;++i){
            for(int j=0; j<board[0].length; ++j){
                if(board[i][j] == null){
                    return false;
                }
                if(board[i][j] != 'Q' && board[i][j] != '.'){
                    return false;
                }
                if(board[i][j] == 'Q'){
                    queenCount += 1;
                }
            }

        }
        if(queenCount == 8){
            return true;
        }
        else{
            return false;
        }

    }
    
}
