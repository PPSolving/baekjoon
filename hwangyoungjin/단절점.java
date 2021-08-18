package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Scanner;

public class P11266 {
    static int V,E;
    static boolean isPoint[];
    static int[] orders;
    static int order; // order는 전역으로 관리
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        isPoint = new boolean[V+1];
        orders = new int[V+1];
        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        order = 1;
        //입력된 그래프가 연결그래프가 아닐 수도 있으므로
        for(int i=1; i<=V; i++){
            if(orders[i] == 0) dfs(i,0,true);
        }

        int count = 0;
        StringBuffer buf = new StringBuffer();
        for(int i=1; i<=V; i++){ //1번 부터 확인하므로 오름차순으로 출력
            if(isPoint[i]){
                count++;
                buf.append(i+" ");
            }
        }

        System.out.println(count+"\n"+buf.toString());
    }
    static int dfs(int id, int parent, boolean isRoot){
        orders[id] = order;
        int child = 0; //root가 true가 아니면 의미 X
        int minLow = order;
        order++;

        for(int next : graph[id]){
            //부모는 재방문 X
            if(next == parent) continue;
            if(orders[next] == 0){ // 0이면 방문하지 않은 곳
                child++;
                int low = dfs(next,id,false);

                //방문 하지 않은 자식으로부터 반환된 low(연결된 Order 의 최소값)가
                //자신의 order 보다 더 크거나 자신과 같으면 자신보다 더 먼저 방문한(= order가 적은)
                //노드를 방문 할 수 있는 경로가 존재하지 않는 것이므로 자신은 단절점
                if(!isRoot && low >= orders[id]) isPoint[id] = true;

                //low 최신화
                minLow = Math.min(minLow,low);
            } else{
                //방문은 했지만 연결된(= 경로가 존재하는)노드의 order 값이므로 연결된 노드 모두 확인
                minLow = Math.min(minLow,orders[next]);
            }
        }

        if(isRoot && child > 1) isPoint[id] = true;

        return minLow;
    }
}
