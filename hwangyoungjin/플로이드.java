package ppsolve.baejoon;

import java.util.Scanner;

public class P11404 {
    static int N,M;
    static long[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        dist = new long[N+1][N+1];
        //dist fill
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i=1; i<=M; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int w = sc.nextInt();
            if(dist[from][to] > w) dist[from][to] = w;
        }
        //floyd
        floyd();
        StringBuffer buf = new StringBuffer();
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(dist[i][j] == Integer.MAX_VALUE) buf.append(0+" ");
                else buf.append(dist[i][j]+" ");
            }
            buf.append("\n");
        }
        System.out.println(buf.toString());
    }
    static void floyd(){
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(i == j) continue;
                    dist[i][j] = Math.min(dist[i][j],(dist[i][k]+dist[k][j]));
                }
            }
        }
    }
}
