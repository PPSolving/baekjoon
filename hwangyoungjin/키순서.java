package ppsolve.baejoon;

import java.util.Scanner;

public class P2458 {
    static int N,M;
    static int[][] dist;
    static final int INF = 501;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        dist = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                dist[i][j] = INF;
            }
        }
        for(int i=0; i<M; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            dist[s][e] = 1;
        }

        //floyd
        /** 중간에 연결된 V을 기준으로 거리 갱신 **/
        /** 존재하지 않는 경로는 거리 갱신 되지 X **/
        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int answer = 0;
        //1부터 N번에 대해 모든
        for(int k = 1; k<=N; k++){
            int cnt = 0;
            for(int i=1; i<=N; i++){
                /**한쪽에서라도 다른쪽 갈수 있다는 건 비교가 가능하다는 것**/
                if(dist[k][i] != INF || dist[i][k] != INF) cnt++;
            }
            if(cnt == N-1) answer += 1;
        }
        System.out.println(answer);
    }
}
