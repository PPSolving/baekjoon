package ppsolve.baejoon;

import java.util.Arrays;
import java.util.Scanner;

public class P11659 {
    static int N,M;
    static long[] dp; //dp[i] : 1번째부터 j번째까지의 합
    static int[] nums;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        dp = new long[N+1];
        nums = new int[N+1];
        for(int i=1; i<=N; i++){
            nums[i] = sc.nextInt();
            dp[i] = nums[i]+dp[i-1];
        }

        StringBuffer buf = new StringBuffer();
        for(int k=0; k<M; k++){
            int i = sc.nextInt();
            int j = sc.nextInt();
            long result = dp[j]-dp[i-1];
            buf.append(result+"\n");
        }
        System.out.println(buf.toString());
    }
}
