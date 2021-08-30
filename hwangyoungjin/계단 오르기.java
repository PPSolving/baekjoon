package ppsolve.baejoon;

import java.util.Scanner;

public class P2579 {
    static int N;
    static int[] dp; //dp[i] = i 번째 계단까지 올라가는 최댓값
    static int[] nums;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N+1];
        nums = new int[N+1];
        for(int i=1; i<=N; i++){
            nums[i] = sc.nextInt();
            dp[i] = nums[i];
        }
        //init
        //2칸 까지는 연속으로 밞는게 최대값
        if(N>1) dp[2] += dp[1];

        for(int i=3; i<=N; i++){
            dp[i] = Math.max(dp[i-2],dp[i-3]+nums[i-1]) + nums[i];
        }
        System.out.println(dp[N]);
    }
}
