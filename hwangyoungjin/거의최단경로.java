package ppsolve.baejoon;

import java.util.*;

//거의최단경로
//맨처음 방법 https://chinpa.tistory.com/63 에서 1번으로 해서 틀림
public class P5719 {
    static int N,M;
    static ArrayList<Integer>[] pre;     //최단 경로가 여러개 이므로 자신의 부모가 여러개 일 수 있다.
    static ArrayList<Node>[] graph;
    static long[] dist;
    static boolean[][] disablePath; // 도로는 1개 이므로, true는 갈 수 없는 도로를 의미

    static class Node implements Comparable<Node>{
        int id;
        long w;

        public Node(int id, long w) {
            this.id = id;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return (int)(w-o.w);
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuffer buf = new StringBuffer();
        while(true){
            N = sc.nextInt();
            M = sc.nextInt();

            if(N == 0 && M == 0) break;

            //vertex 는 0번 부터
            pre = new ArrayList[N];
            graph = new ArrayList[N];
            dist = new long[N];
            disablePath = new boolean[N][N];
            for(int i=0; i<N; i++){
                graph[i] = new ArrayList<>();
                pre[i] = new ArrayList<>();
            }

            int start = sc.nextInt(); //start
            int end = sc.nextInt(); //end

            for(int i=0; i<M; i++){
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                graph[u].add(new Node(v,w));
            }

            //첫번쨰 최단거리 구하고
            dijkstra(start,end);
            //최단거리 backtracking으로 지우고
            deletePath(start,end);
            //두번째 최단거리
            dijkstra(start,end);
            long result = dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
            buf.append(result+"\n");
        }
        System.out.println(buf.toString());
    }
    static void dijkstra(int start, int end){
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            //목적지 도착 -> 또다른 경로도 봐야하므로
            if(cur.id == end) break;

            //현재까지 기록된 cur에 가는 거리가 pq에서 뽑은 cur까지의 거리보다 더 짧으면
            if(dist[cur.id] < cur.w ) continue;

            for(Node next : graph[cur.id]){

                //두 번째 다익스트라일때 없어진 경로를 체크
                if(disablePath[cur.id][next.id]) continue;

                //길이가 같다는건 또 다른 최단 경로를 의미
                if(dist[next.id] == dist[cur.id]+next.w){
                    pre[next.id].add(cur.id); // next는 cur을 거쳐서 갈 수 있다.
                    continue;
                }

                if(dist[next.id] > dist[cur.id]+next.w){
                    dist[next.id] = dist[cur.id]+next.w; // 거리 갱신
                    pre[next.id].clear();//이전 기록은 모두 지우고 next 새로 갱신해야하므로 next에 cur을 추가
                    pre[next.id].add(cur.id);
                    pq.add(new Node(next.id, dist[next.id]));
                }
            }
        }
    }
    static void deletePath(int start, int end){
        //출발지 인 경우
        if(start==end) return;
        for(int preNum : pre[end]){
            // 삭제 안한 경로만 탐색
            if(!disablePath[preNum][end]) { 
                deletePath(start, preNum);
                disablePath[preNum][end] = true;
            }
        }
    }
}
