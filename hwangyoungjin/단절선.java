package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class P11400 {
    static int V,E;
    static ArrayList<Integer>[] graph;
    static int order;
    static int[] orders;
    static ArrayList<Edge> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        order = 1;
        graph = new ArrayList[V+1];
        orders = new int[V+1];
        answer = new ArrayList<>();
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=E; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph[from].add(to);
            graph[to].add(from);
        }
        djs(1,0);
        StringBuffer buf = new StringBuffer();
        buf.append(answer.size()+"\n");
        Collections.sort(answer);
        for(Edge e : answer){
            buf.append(e.from+" "+e.to+"\n");
        }
        System.out.println(buf.toString());
    }

    static int djs(int id, int parent ){
        orders[id] = order;
        int minLow = orders[id];
        order++;

        for(int next : graph[id]){

            //되돌아가기 방지
            if(next == parent) continue;

            //order가 없는 = 방문하지 않은
            if(orders[next] == 0) {
                int low = djs(next,id);

                if(low > orders[id]) {
                    //단절선이므로 추가
                    if(next < id) answer.add(new Edge(next,id));
                    else answer.add(new Edge(id,next));
                }
                minLow = Math.min(low,minLow);
            }
            minLow = Math.min(orders[next],minLow);
        }

        //low 값
        return minLow;
    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Edge o) {
            if(from == o.from) return to-o.to;
            return from - o.from;
        }
    }
}
