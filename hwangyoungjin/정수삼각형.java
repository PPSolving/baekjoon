package ppsolve.baejoon;

import java.util.Arrays;
import java.util.Scanner;

public class P1932 {
    static int N;
    static int[][] triangle;
    static int[][] dp; //i번 행 j번째 row 까지 오는데 걸린 최대 값
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        triangle = new int[N][N];
        dp = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<=i; j++){
                int num = sc.nextInt();
                triangle[i][j] = num;
            }
        }
        solution();

        Arrays.sort(dp[N-1]);
        System.out.println(dp[N-1][N-1]);
    }
    static void solution(){
        //init
        dp[0][0] = triangle[0][0];
        for(int i=1; i<N; i++){
            for(int j=0; j<=i; j++){
                int before = j == 0 ? 0 : j-1;
                dp[i][j] = Math.max(dp[i-1][before]+triangle[i][j],dp[i-1][j]+triangle[i][j]);
            }
        }
    }
}
