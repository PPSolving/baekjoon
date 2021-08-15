package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Scanner;

public class P1516 {
    static int N;
    static int[] time;
    static int[] dp; //i번째가 완성되는 최소 시간
    static int[] indegree;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        time = new int[N+1];
        dp = new int[N+1];
        indegree = new int[N+1];
        graph = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
            int num = sc.nextInt();
            //맨처음은 weight
            time[i] = num;
            dp[i] = num;

            num = sc.nextInt();
            while(num != -1){
                graph[i].add(num);
                num = sc.nextInt();
            }
            indegree[i] = graph[i].size();
        }

        for(int i=1; i<=N; i++){
            if(indegree[i] != 0){
                game(i);
            }
        }
        StringBuffer buf = new StringBuffer();
        for(int i=1; i<=N; i++){
            buf.append(dp[i]+"\n");
        }
        System.out.println(buf.toString());
    }

    public static void game(int start){
        if(indegree[start] == 0) return;

        for(int before : graph[start]){
            game(before);
            //가장 큰 값이라는것은 앞에서 끝날게 있었다는 의미
            dp[start] = Math.max(dp[start],dp[before]+time[start]);
            indegree[start] -= 1;
        }
    }
}
