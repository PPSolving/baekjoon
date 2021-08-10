package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Scanner;

public class P2252 {
    static int N,M;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new ArrayList[N+1];
        visit = new boolean[N+1];
        for(int i=1; i<=N;i++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            // a < b , size는 indegree가 된다
            graph[b].add(a);
        }
        for(int i=1; i<=N; i++){
            if(!visit[i]){
                sort(i);
            }
        }
    }
    static void sort(int num){
        if(visit[num]) return;
        visit[num] = true;
        for(int next : graph[num]){
            if(visit[next]) continue;
            sort(next);
        }
        System.out.print(num+" ");
    }
}
