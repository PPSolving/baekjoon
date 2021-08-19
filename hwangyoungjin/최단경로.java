package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class P1753 {
    static int V,E,S; //S : start Node
    static int[] dist;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        dist = new int[V+1];
        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>();
        }
        S = sc.nextInt();
        for(int i=1; i<=E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Node(v,w)); //단 방향
        }
        dijkstra(S);
        StringBuffer buf = new StringBuffer();
        for(int dis =1; dis<=V; dis++){
            if(dis == S) buf.append(0+"\n");
            else if(dist[dis] != Integer.MAX_VALUE) buf.append(dist[dis]+"\n");
            else buf.append("INF\n");
        }
        System.out.println(buf.toString());
    }
    static void dijkstra(int start){
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            //이미 start부터 cur까지 걸린 거리보다 cur의 w가 크다면 cur direct로 갈 필요X
            if(dist[cur.id] < cur.w) continue;

            for(Node next : graph[cur.id]){

                if(dist[next.id] > dist[cur.id] + next.w){
                    dist[next.id] = dist[cur.id] + next.w;
                    pq.add(new Node(next.id,dist[next.id]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int id;
        int w;

        public Node(int id, int w) {
            this.id = id;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w-o.w;
        }
    }
}