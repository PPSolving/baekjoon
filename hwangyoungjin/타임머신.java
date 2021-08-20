package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P11657 {
    static int V,E;
    static ArrayList<Edge> edges;
    static boolean isTimeBack; // true 이면 음수 가중치로 시간 뒤로

    //N = 500, M = 6000 인 경우 최대 300만번의 반복문을 돌게 된다.
    //이때 음의 간선이 -10,000 이라면 약 -300억의 값으로
    //underflow가 발생하게 되어 출력초과가 나오는 경우가 발생한다.
    //때문에 dist 거리배열의 자료형을 long으로 선언
    static long[] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        dist = new long[V + 1];
        isTimeBack = false;
        edges = new ArrayList<>();
        for (int i = 1; i <= E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(from, to, w));
        }
        bellman(1);

        if (isTimeBack) System.out.println("-1");
        else {
            StringBuffer buf = new StringBuffer();
            for (int i = 2; i <= V; i++) {
                if (dist[i] == Integer.MAX_VALUE) buf.append("-1\n");
                else buf.append(dist[i]+"\n");
            }
            System.out.println(buf.toString());
        }
    }
    static void bellman(int start){
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        for(int i=0; i<V; i++){ // v번
            for(int j=0; j<E; j++){
                Edge e = edges.get(j);

                //from 까지 무한대라면 시작점에서 갈 수 X
                if(dist[e.from] == Integer.MAX_VALUE) continue;

                dist[e.to] = Math.min(dist[e.to],dist[e.from]+e.w);
            }
        }
        for(int j=0; j<E; j++){
            Edge e = edges.get(j);
            if(dist[e.from] == Integer.MAX_VALUE) continue;
            if(dist[e.to] > dist[e.from]+e.w) isTimeBack = true;
        }
    }
    static class Edge{
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
}
