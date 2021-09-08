package Halloween_Cemetery;


import java.util.Scanner;

class cemetery{
    long val;
    boolean dontaccess; //묘비
    boolean ispotal; //포탈
    int potalx,potaly,potalt;
    cemetery(){
        val =Integer.MAX_VALUE;
        dontaccess = false;
        ispotal =false;
    }
    void dispotal(int x,int y,int t){
        this.potalx =x;
        this.potaly =y;
        this.potalt = t;
        ispotal =true;
    }


}
public class HalloweenCemetery_210908 {
    static   Scanner sc;
    static StringBuilder sb= new StringBuilder();
    static int[][] move = {
            {0,1},
            {1,0},
            {-1,0},
            {0,-1}
    };
    public static void main(String[] args) {
        sc=new Scanner(System.in);
        while(true) {
            int W = sc.nextInt();
            int H = sc.nextInt();
            if(W==0&&H==0) break;
            run(W,H);
        }
        System.out.print(sb);
    }


    static void run(int W,int H){
        cemetery[][] ce =new cemetery[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                ce[i][j] =new cemetery();
            }
        }
        int N =sc.nextInt();
        int x,y,tox,toy,t;
        for (int i = 0; i < N; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            ce[y][x].dontaccess =true;
        }
        N=sc.nextInt();
        for (int i = 0; i < N; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            tox =sc.nextInt();
            toy =sc.nextInt();
            t= sc.nextInt();

            ce[y][x].dispotal(tox,toy,t);
        }
        ce[0][0].val=0l;

        for (int k = 0; k < W * H - 1; k++) {
           velman(ce,H,W);
        }
        if(velman(ce,H,W)){
            sb.append("Never\n");
        }else{
            if(ce[H-1][W-1].val !=Integer.MAX_VALUE){
                sb.append(ce[H-1][W-1].val+"\n");
            }else{
                sb.append("Impossible\n");
            }
        }
    }
   static boolean velman(cemetery[][] ce,int H,int W){
        int x,y;
        boolean ch =false;
        for (int i = 0; i < H; i++) {
            for(int j=0;j<W;j++){
                if(ce[i][j].val != Integer.MAX_VALUE&& !(i==H-1&&j==W-1)) {
                    if (ce[i][j].ispotal) {
                        if (ce[i][j].val + (long) ce[i][j].potalt
                                < ce[ce[i][j].potaly][ce[i][j].potalx].val) {
                            ce[ce[i][j].potaly][ce[i][j].potalx].val = ce[i][j].val + (long) ce[i][j].potalt;
                            ch =true;
                        }
                    } else {
                        for (int m = 0; m < 4; m++) {
                            if (i + move[m][0] >= 0 && i + move[m][0] < H && j + move[m][1] >= 0 && j + move[m][1] < W) {
                                y = i + move[m][0];
                                x = j + move[m][1];
                                if (!ce[y][x].dontaccess &&ce[i][j].val+1 <ce[y][x].val) {
                                    ce[y][x].val =ce[i][j].val +1;
                                    ch =true;
                                }
                            }
                        }
                    }
                }

            }
        }
        return ch;
    }

}
