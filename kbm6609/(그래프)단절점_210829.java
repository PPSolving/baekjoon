package Shortest_Path;

import java.util.*;

public class ShortestPath_210827 {
    static ArrayList<Integer>[] tree;
    static int count;
    static int[] order;
    static boolean[] check;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        tree =new ArrayList[N+1];
        pq =new PriorityQueue<>();
        order =new int[N+1];
        check =new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            tree[i] =new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            tree[a].add(b);
            tree[b].add(a);
        }

        count = 0;

        for (int i = 1; i <= N; i++) {
            if(order[i]==0){
                dfs(i,0);
            }
        }
        int ans = pq.size();
        StringBuilder sb = new StringBuilder();
        sb.append(ans+"\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()+" ");
        }
        System.out.println(sb);

    }
    static int dfs(int index, int pa) {
        order[index] =++count;
        int ret=count;

        int c = 0;
        for (int i = 0; i < tree[index].size(); i++) {
            int tmp =tree[index].get(i);
            if(tmp ==pa) continue;
            if(order[tmp]==0){
                int k =dfs(tmp,index);
                c++;
                if (pa > 0 && k >= order[index] && !check[index]) {
//                    System.out.println(index+"  "+"1  "+pa);
                    check[index]=true;
                    pq.add(index);
                }

                ret =Math.min(ret,k);
            }else{
                ret =Math.min(ret,order[tmp]);
            }
        }
        if(pa==0&&c>=2){
//            System.out.println(index+"  "+"2 "+pa);
            check[index]=true;
            pq.add(index);
        }
        return ret;



    }

}
