public class FindWord{
//    public static void exist(char[][] board, String word) {
//        for(int r=0;r< board.length;r++){
//            for(int c=0;c<board.length;c++){
//                ans("",board,word,r,c);
//            }
//        }
//
//
//        //System.out.println(ans("",board,word,board.length,board[0].length));
//    }
//    public static void ans(String p,char[][] a,String s,int r,int c){
//        if(r==a.length-1 && c==a[0].length-1){
//            System.out.println(p+a[r][c]);
//            return;
//        }
//        if(a[r][c]=='0'){
//            return;
//        }
//        char ch=a[r][c];
//        p+=a[r][c];
//        a[r][c]='0';
//        if(r<a.length-1 && c>0){
//            ans(p,a,s,r+1,c-1);
//        }
//        if(r<a.length-1){
//            ans(p,a,s,r+1,c);
//        }
//        if(c<a[0].length-1){
//             ans(p,a,s,r,c+1);
//        }
//        if(r>0){
//             ans(p,a,s,r-1,c);
//        }
//        if(c>0){
//             ans(p,a,s,r,c-1);
//        }
//        a[r][c]=ch;
//    }
public static boolean exist(char[][] board, String word) {
    for (int i = 0; i < board.length; i++)
        for (int j = 0; j < board[0].length; j++)
            if (board[i][j] == word.charAt(0) && isFound(board, i, j, word, 0))
                return true;

    return false;
}

    private static boolean isFound(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (word.charAt(index) != board[i][j]) return false;
        char temp = board[i][j];
        board[i][j] = '*';
        if (isFound(board, i + 1, j, word, index + 1) ||
                isFound(board, i - 1, j, word, index + 1) ||
                isFound(board, i, j + 1, word, index + 1) ||
                isFound(board, i, j - 1, word, index + 1))
            return true;
        board[i][j] = temp;
        return false;
    }
    public static void main(String[] args) {
        char[][] c={ {'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String s="SEE";
        System.out.println(exist(c,s));
    }
}
