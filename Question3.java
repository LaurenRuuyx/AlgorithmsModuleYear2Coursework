public class Question3 {
    public static void main(String[] args){
        Character[][] board = {
            {'.','.','.','.','.','.','.','Q'},
            {'.','.','.','.','.','.','Q','.'},
            {'.','.','.','.','.','Q','.','.'},
            {'.','.','.','.','Q','.','.','.'},
            {'.','.','.','Q','.','.','.','.'},
            {'.','.','Q','.','.','.','.','.'},
            {'.','Q','.','.','.','.','.','.'},
            {'Q','.','.','.','.','.','.','.'}
        };
        System.out.println(convertBoardToBinary(board));

    }
    public static String convertBoardToBinary(Character[][] board){
        String binary = "";
        for(int i=board.length-1; i>=0; --i){
            for (int j=0; j<board[0].length; ++j){
                if(board[i][j] == 'Q'){
                    String temp = Integer.toBinaryString(j);
                    temp = String.format("%3s", temp).replaceAll(" ", "0");
                    binary = binary + temp;
                }
            }
        }
        return binary;

    }
}
