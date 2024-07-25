public class Maze {
    public static void main(String[] args) {
        boolean[][] m={
                {true,true,true},
                {true,true,true},
                {true,true,true},
                {true,true,true}
        };
        path("",m,0,0);
    }
    public static void path(String p,boolean[][] m,int r,int c){
        if(r==m.length-1 && c==m[0].length-1){
            System.out.println(p);
            return;
        }
        if(!m[r][c]){
            return;
        }
        m[r][c]=false;
        if(r<m.length-1){
            path(p+'D',m,r+1,c);
        }
        if(c<m[0].length-1){
            path(p+'R',m,r,c+1);
        }
        if(r>0){
            path(p+'U',m,r-1,c);
        }
        if(c>0){
            path(p+'L',m,r,c-1);
        }
        m[r][c]=true;
    }
}
