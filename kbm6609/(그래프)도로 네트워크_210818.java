package Road_Network;

import java.util.Scanner;
import java.util.Vector;

public class RoadNetwork_210818 {
    static int[][] parent;
    static int[][] min;
    static int[][] max;
    static boolean[] check;
    static int[] depth;
    static Vector<Vector<Integer>> tree;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int a,b,c;
        tree =new Vector<>();
        parent = new int[N][20];
        min = new int[N][20];
        max = new int[N][20];
        check =new boolean[N];
        depth =new int[N];
        sb =new StringBuilder();
        for (int i = 0; i < N; i++) {
            tree.add(new Vector<>());
            for (int j = 0; j < 20; j++) {
                min[i][j] =1000000;
            }
        }




        for (int i = 0; i < N - 1; i++) {
            a= sc.nextInt()-1;
            b= sc.nextInt()-1;
            c= sc.nextInt();
            tree.get(a).add(b);
            tree.get(a).add(c);
            tree.get(b).add(a);
            tree.get(b).add(c);
        }
        dfs(0,0);
        dp();
      /*  System.out.println();
        System.out.println("parent");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(parent[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("min");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(min[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("max");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(max[i][j]+" ");
            }
            System.out.println();
        }
*/
        int M =sc.nextInt();
        for (int i = 0; i < M; i++) {
            a= sc.nextInt()-1;
            b= sc.nextInt()-1;
            lca(a,b);
        }
        System.out.println(sb);


    }

    static void dfs(int index ,int deep){
        check[index] = true;
        depth[index] = deep;
        for (int i = 0; i < tree.get(index).size(); i+=2) {
            int a =tree.get(index).get(i);
            int b =tree.get(index).get(i+1);
            if(check[a]) continue;
            parent[a][0] =index;
            min[a][0] =b;
            max[a][0] =b;
            dfs(a,deep+1);
        }
    }

    static void dp(){
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < parent.length; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
                min[j][i] = Math.min(min[j][i - 1], min[parent[j][i - 1]][i - 1]);
                max[j][i] = Math.max(max[j][i - 1], max[parent[j][i - 1]][i - 1]);
            }
        }
    }
    static void lca(int a, int b){
        int minnum=0,maxnum=0;
        if(depth[a] >depth[b]){
            int tmp =a ;
            a= b;
            b= tmp;
        }
        if(depth[a] != depth[b]) {
            minnum=1000000;
            for (int i = 19; i >= 0; i--) {
                if (depth[a] < depth[parent[b][i]]) {
                    if (minnum > min[b][i]) minnum = min[b][i];
                    if (maxnum < max[b][i]) maxnum = max[b][i];
                    b = parent[b][i];
                }
            }
            if (minnum > min[b][0]) minnum = min[b][0];
            if (maxnum < max[b][0]) maxnum = max[b][0];
            b = parent[b][0];
        }
        if(a==b) {
            sb.append(minnum+" "+maxnum+"\n");
        }else{
            int s;
            if(minnum ==0) minnum =1000000;
            for(int i=19;i>=0;i--){
                if(parent[a][i] !=parent[b][i]){
                    s = Math.min(min[a][i], min[b][i]);
                    minnum = Math.min(minnum, s);
                    s = Math.max(max[a][i], max[b][i]);
                    maxnum = Math.max(maxnum, s);
                    a = parent[a][i];
                    b = parent[b][i];
                }
            }
            s = Math.min(min[a][0], min[b][0]);
            minnum = Math.min(minnum, s);
            s = Math.max(max[a][0], max[b][0]);
            maxnum = Math.max(maxnum, s);
            sb.append(minnum+" "+maxnum+"\n");
        }
    }
}
