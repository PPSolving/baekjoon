package Floyd;

import java.util.Scanner;

public class Floyd_210904 {
    static int[][] val;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        val =new int[N][N];
        int a,b,c;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                val[i][j] = i==j?0:10000001;
            }
        }


        for (int i=0;i<M ;i++) {
            a = sc.nextInt() - 1;
            b = sc.nextInt() - 1;
            c = sc.nextInt();
            val[a][b] = val[a][b] == 0 ? c : (c < val[a][b] ? c : val[a][b]);
        }
        floid();

        for (int[] i : val) {
            for(int j:i){
                if(j==10000001)
                    System.out.print("0 ");
                else
                    System.out.print(j+" ");
            }
            System.out.println();
        }



    }
    static void floid(){
        int v =val.length;
        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if(i!=k&&j!=k) {
                        val[i][j] = Math.min(val[i][j], val[i][k] + val[k][j]);
                    }
                }
            }
        }
    }
}
