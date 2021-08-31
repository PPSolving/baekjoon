package ppsolve.baejoon;

import java.util.Arrays;
import java.util.Scanner;

public class P1915 {
    static int N,M;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        dp = new int[N+1][M+1];
        int maxSize = 0;
        for(int i=1; i<=N; i++){
            String num = sc.next();
            String[] nums = num.split("");
            for(int j=0; j<nums.length; j++){
                dp[i][j+1] = Integer.parseInt(nums[j]);
                if(maxSize < dp[i][j+1]) maxSize = dp[i][j+1];
            }
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(dp[i][j] == 0 || dp[i-1][j-1] == 0
                        || dp[i][j-1] == 0 || dp[i-1][j] == 0) continue;
                int min = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                dp[i][j] = ++min;
                if(maxSize < dp[i][j]) maxSize = dp[i][j];

            }
        }
        System.out.println((maxSize*maxSize));
    }
}
