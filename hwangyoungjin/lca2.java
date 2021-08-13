package ppsolve.baejoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P11438 {

    static int N,M,K;
    static int[] depth;
    static int[][] parent;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = 0;
        for(int i=1; i<N; i*=2){
            K++;
        }
        tree = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            tree[i] = new ArrayList<Integer>();
        }
        depth = new int[N+1];
        parent = new int[K][N+1];
        for(int i=1; i<N; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        M = sc.nextInt();

        //dfs로 depth 기록
        dfs(1,1);

        //fileParent
        fillParent();

        StringBuffer buf = new StringBuffer();

        //lca
        for(int i=1; i<=M; i++){
            //lca
            int a = sc.nextInt();
            int b = sc.nextInt();
            int result = lca(a,b);
            buf.append(result + "\n");
        }

        System.out.println(buf.toString());
    }

    public static void dfs(int start, int deep){
        depth[start] = deep;
        for(int i : tree[start]){
            if(depth[i] != 0) continue;
            dfs(i,deep+1);
            parent[0][i] = start;
        }
    }

    public static void fillParent(){
        for(int i=1; i<K; i++){
            for(int j=1; j<=N; j++){
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    public static int lca(int a, int b){
        //a를 무조건 depth 더 깊은 값으로
        if(depth[a] < depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }

        //a를 b와 depth 맞추기
        for(int i=K-1; i>=0; i--){
            if((int)Math.pow(2,i) <= depth[a] - depth[b]){
                a = parent[i][a];
            }
        }

        //depth 맞췄는데 같으면 return
        if(a==b) return a;

        //다르면 같은 depth에서 같은 조상 찾기
        for(int i=K-1; i>=0; i--){
            //같은 조상이 있을때까지 위로 올라간다.
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        //a와 b의 바로위가 최소 공통 조상이 된다
        return parent[0][a];
    }
}
