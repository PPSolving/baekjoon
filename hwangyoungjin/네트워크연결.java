package ppsolve.baejoon;

import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int w;

    public Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return w-o.w;
    }
}

public class P1922 {

    static int N, M; //컴퓨터와 연결선의 개수
    static int answer;
    static int[] parent;
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        answer = 0;
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }
        pq = new PriorityQueue<Edge>();
        for(int i=1; i<=M; i++){
           int from = sc.nextInt();
           int to = sc.nextInt();
           int w = sc.nextInt();
           pq.add(new Edge(from,to,w));
        }
        kruskal();
        System.out.println(answer);
    }
    public static void kruskal(){
        int select = 0;
        while(select < N-1){
            Edge e = pq.poll();
            if(find(e.from) == find(e.to)) continue;
            union(e.from,e.to);
            answer+=e.w;
            select++;

        }
    }
    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        parent[pb] = pa;
    }
}
