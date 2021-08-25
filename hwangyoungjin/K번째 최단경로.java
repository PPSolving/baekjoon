package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1854 {
    static int N,M,K;
    static PriorityQueue<Long>[] dist;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node>{
        int id;
        long w;

        public Node(int id, long w) {
            this.id = id;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (w-o.w);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        //init
        dist = new PriorityQueue[N+1];
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            //dist의 기록할 경로 수는 K개
            dist[i] = new PriorityQueue<>(K);
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph[a].add(new Node(b,c));
        }
        dijkstra(1);
        StringBuffer buf = new StringBuffer();
        for(int i=1; i<=N; i++){
            if(dist[i].size() == K) buf.append((-1*dist[i].poll())+"\n");
            else buf.append("-1\n");
        }
        System.out.println(buf.toString());
    }
    static void dijkstra(int start){

        dist[start].add(0l);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            for(Node next : graph[cur.id]) {
                //start -> cur -> next 까지의 거리
                long len = cur.w+next.w;

                //start 부터 next까지의 경로가 K개 이하라면 그냥 경로에 추가
                if(dist[next.id].size() < K){
                    dist[next.id].add(len*-1);
                    pq.add(new Node(next.id, len));
                }
                //start 부터 next까지의 경로가 K개 이상인데 K번째 보다 빠른 경로라면 K번째 갱신하고 경로에 추가
                else if(dist[next.id].peek()*-1 > len){
                    dist[next.id].poll();
                    dist[next.id].add(len*-1);
                    //최단 경로는 양수값으로
                    pq.add(new Node(next.id, len));
                }
            }
        }
    }
}
