package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P3860 {
    static int W,H,G,E;
    static int[][] map;
    static ArrayList<Edge> edges;
    static int[][] dist;
    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Edge {
        Node from;
        Node to;
        int w;

        public Edge(Node from, Node to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        W = sc.nextInt();
        H = sc.nextInt();
        StringBuffer buf = new StringBuffer();
        while(W != 0 && H != 0){
            map = new int[H][W];
            edges = new ArrayList<>();
            G = sc.nextInt();
            for(int i=0; i<G; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[y][x] = 1; //1은 묘비
            }
            E = sc.nextInt();
            for(int i=0; i<E; i++){
                int preX = sc.nextInt();
                int preY = sc.nextInt();
                map[preY][preX] = -1; //귀신 구멍은 -1
                int nextX = sc.nextInt();
                int nextY = sc.nextInt();
                int w = sc.nextInt();
                edges.add(new Edge(new Node(preX,preY), new Node(nextX,nextY),w));
            }
            //map 에서 가능한 edge 추가
            makePath();

            boolean result = bellman();

            if(!result){
                if(dist[H-1][W-1] == Integer.MAX_VALUE) buf.append("Impossible\n");
                else buf.append(dist[H-1][W-1]+"\n");
            }else{
                buf.append("Never\n");
            }
            W = sc.nextInt();
            H = sc.nextInt();
        }
        System.out.println(buf.toString());
    }

    static void makePath() {
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                // 묘비 or 귀신구멍이거나 도착지이면 continue
                // 귀신구멍은 바로 연결된 곳으로 이동하므로 주변으로 갈 수 X
                if (map[y][x] == 1 || map[y][x] == -1 || (y == H - 1 && x == W - 1)) continue;

                //동 남 서 북
                int[][] direction = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

                for (int k = 0; k<=3; k++) {
                    int nextY = y + direction[k][1];
                    int nextX = x + direction[k][0];

                    if ( nextY >=0 && nextY < H && nextX >= 0 && nextX < W && map[nextY][nextX] != 1 ) {
                        edges.add(new Edge(new Node(x,y), new Node(nextX,nextY), 1));
                    }
                }
            }
        }
    }
    static boolean bellman(){
        //0,0 부터 y,x 까지의 거리
        dist = new int[H][W];
        for(int i=0; i<dist.length; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        boolean check = false;
        int v = H*W - G;

        for(int n=0; n<v; n++){
            for(int i=0; i<edges.size(); i++) {
                Edge e = edges.get(i);
                Node from = e.from;
                Node to = e.to;
                //Start부터 From까지 갈수 없다면 continue
                if (dist[from.y][from.x] == Integer.MAX_VALUE) continue;

                //거리 갱신
                dist[to.y][to.x] = Math.min(dist[to.y][to.x], dist[from.y][from.x] + e.w);
            }
        }
        //한번 더 갱신되면 음수 사이클 존재
        for(int i=0; i<edges.size(); i++){
            Edge e = edges.get(i);
            Node from = e.from;
            Node to = e.to;

            if(dist[from.y][from.x] == Integer.MAX_VALUE) continue;

            //거리 갱신
            if(dist[to.y][to.x] > dist[from.y][from.x]+e.w){
                check = true;
            }
        }
        return check;
    }
}
