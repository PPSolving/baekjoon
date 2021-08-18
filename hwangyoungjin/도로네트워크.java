package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P3176 {
    static int N,K,M; // N : 정점수, M : 2의 지수 , // K : 쿼리 수
    static int[] depth;
    static int[][] parent; // parent[K][V] 정점 V의 2^K번째 조상 정점 번호
    static int[][] minLen; // minDist[K][V]  정점 V의 2^K번째 조상까지의 최소거리 도로
    static int[][] maxLen; // maxDist[K][V]  정점 V의 2^K번째 조상까지의 최대거리 도로
    static int min,max;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        depth = new int[N+1];
        graph = new ArrayList[N+1];

        M = 0;
        //N이 15이면 15<2^4 이므로 M = 4;
        for(int i=1; i<=N; i*=2){
            M++;
        }

        parent = new int[M][N+1];
        minLen = new int[M][N+1];
        maxLen = new int[M][N+1];


        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=N-1; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int len = sc.nextInt();
            graph[a].add(new Node(b,len));
            graph[b].add(new Node(a,len));
        }

        int maxSize = 0;
        int root = 0;

        for(int i=1; i<=N; i++){
            if(graph[i].size() > maxSize){
                maxSize = graph[i].size();
                root = i;
            }
        }

        //m = 0 일때 init, depth 설정
        dfs(root,1);

        //parent, minLen. maxLen fill
        fillParent();

        K = sc.nextInt();

        StringBuffer buf = new StringBuffer();
        for(int i=1; i<=K; i++){
            int d = sc.nextInt();
            int e = sc.nextInt();
            lca(d,e);
            buf.append(min+" "+max+"\n");
        }

        System.out.println(buf.toString());
    }

    static void dfs(int start,int cnt){
        depth[start] = cnt;

        for(Node n : graph[start]){
            if(depth[n.num] == 0){
                dfs(n.num,cnt+1);
                parent[0][n.num] = start;
                minLen[0][n.num] = n.len;
                maxLen[0][n.num] = n.len;
            }
        }
    }
    static void fillParent(){
        for(int m=1; m<M; m++ ){
            for(int i = 1; i<=N; i++){
                parent[m][i] = parent[m-1][parent[m-1][i]];
                if(parent[m][i] == 0) {//부모가 없으면 max 계산 X
                    continue;
                }
                minLen[m][i] = Math.min(minLen[m-1][i],minLen[m-1][parent[m-1][i]]);
                maxLen[m][i] = Math.max(maxLen[m-1][i],maxLen[m-1][parent[m-1][i]]);
            }
        }
    }

    static void lca(int d, int e){
        //depth 더 긴쪽을 d로 맞추기
        if(depth[d] < depth[e]){
            int tmp = d;
            d = e;
            e = tmp;
        }
        //System.out.println("start d:"+d+", e :"+e);

        min = Integer.MAX_VALUE;
        max = 0;

        //depth 다르면 맞추기
        for(int m=M-1; m>=0; m--){
            if((int)Math.pow(2,m) <= depth[d] - depth[e]){
                min = Math.min(min,minLen[m][d]);
                max = Math.max(max,maxLen[m][d]);
                d = parent[m][d];
            }
        }

        //depth 맞춘상태
        //System.out.println("depth 확인 depth["+e+"] = "+depth[e]+", depth["+d+"] = "+depth[d]);
        if(d == e) return;

        //맨위로 가서
        for (int m = M-1; m>=0; m--){
            if (parent[m][d] != parent[m][e]){
                min = Math.min(min,Math.min(minLen[m][d],minLen[m][e]));
                max = Math.max(max,Math.max(maxLen[m][d],maxLen[m][e]));
                d = parent[m][d]; // 조상이 다른경우 이므로 위로 올라가기
                e = parent[m][e]; // 조상이 다른경우 이므로 위로 올라가기
            }
        }
        min = Math.min(min,Math.min(minLen[0][d],minLen[0][e]));
        max = Math.max(max,Math.max(maxLen[0][d],maxLen[0][e]));
    }
}
class Node{
    int num;
    int len;

    public Node(int num, int len) {
        this.num = num;
        this.len = len;
    }
}