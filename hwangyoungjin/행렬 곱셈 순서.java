package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P11049 {
    static int N;
    static int[][] dp;
    static ArrayList<Matrix> list;
    static class Matrix{
        int a;
        int b;

        public Matrix(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N+1][N+1]; // 1번 부터 시작이므로
        list = new ArrayList<>();
        list.add(null); // 1번 부터
        for(int i=1; i<=N; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            list.add(new Matrix(a,b));
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        int result = divideConquer(1,N);
        System.out.println(result);
    }
    public static int divideConquer(int start, int end){
        if(start == end) return 0;

        if(dp[start][end] != Integer.MAX_VALUE) return dp[start][end];

        for(int i=start; i<end; i++){
            int com1 = divideConquer(start, i); //dp[s][i];
            int com2 = divideConquer(i+1,end); //dp[i+1][e]
            Matrix m1 = list.get(start); //start번째 행렬
            Matrix m2 = list.get(i); // i번째 행렬
            Matrix m3 = list.get(end); // end번째 행렬
            //dp[s][i] 결과의 행렬 X dp[i+1][e] 결과의 행렬 곱하기 횟수
            //(m1.a*m2.b*m3.b) = (start,i) 두 행렬 곱하기 횟수
            dp[start][end] = Math.min(dp[start][end],com1+com2+(m1.a*m2.b*m3.b));
        }

        return dp[start][end];

    }
}
