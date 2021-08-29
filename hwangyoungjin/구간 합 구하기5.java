package ppsolve.baejoon;

import java.util.Arrays;
import java.util.Scanner;

public class P11660 {
    static int N,M;
    static int[][] dp; //dp[i][j] = 1,1 부터 i,j까지의 합
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        dp = new int[N+1][N+1];
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map[i][j] = sc.nextInt();
                dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + map[i][j];
            }
        }
        StringBuffer buf = new StringBuffer();
        for(int i=0; i<M; i++){
            int sY = sc.nextInt();
            int sX = sc.nextInt();
            int eY = sc.nextInt();
            int eX = sc.nextInt();
            int result = dp[eY][eX] - dp[sY-1][eX] - dp[eY][sX-1] + dp[sY-1][sX-1];
            buf.append(result+"\n");
        }
        System.out.println(buf.toString());

    }
}
