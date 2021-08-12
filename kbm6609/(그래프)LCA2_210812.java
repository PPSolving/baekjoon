package LCA2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class LCA2_210810 {
    static Vector<Vector<Integer>> tree;
    static boolean[] visit ;
    static int[][] parent;
    static int[] deep;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int a,b;

        tree = new Vector<>();
        visit= new boolean[N];
        parent = new int[N][17];
        deep=new int[N];
        for (int i = 0; i < N; i++) {
            tree.add(new Vector<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st =new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            tree.get(b).add(a);
            tree.get(a).add(b);
        }

        //트리 깊이 설정및 parent 배열의 0번쨰 즉 첫번쨰 부모 노드 설정
        dfs(0,0);
        parentset();

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            StringTokenizer st =new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            if(deep[a] > deep[b]){
                int tmp =a;
                a= b;
                b= tmp;
            }
            for (int j = 16; j >= 0; j--) {
                if(deep[a]<=deep[parent[b][j]] ) b= parent[b][j] ;
            }
            if(a==b) {
            }
            else{
               for(int j=16;j>=0;j--){
                   if(parent[a][j]!=parent[b][j]) {
                       a = parent[a][j];
                       b = parent[b][j];
                   }
                }
               a= parent[a][0];
            }
            sb.append(a+1+"\n") ;
        }
        System.out.println(sb);

    }
    static void dfs(int index,int depth){
        visit[index] =true;
        deep[index]=depth;
        for(int i:tree.get(index)){
            if(!visit[i]){
                dfs(i,depth+1);
                parent[i][0] =index;
            }
        }
    }

    static void parentset() {
        for(int i=1;i<17;i++){
            for (int j = 1; j < parent.length; j++) {
                parent[j][i] =parent[parent[j][i-1]][i-1];
            }
        }
    }
}
