package Network_Connection;

import java.util.*;

class tree implements Comparable<tree>{
    int a;
    int b;
    int val ;

    tree(int val,int a,int b) {
        this.val = val;
        this.a=a;
        this.b=b;
    }
    @Override
    public int compareTo(tree o) {
        return this.val>o.val?1:-1;
    }
}
public class NetworkConnection_210806 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        PriorityQueue<tree> pq =new PriorityQueue<>();
        int[]  node =new int[N+1];
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if(a!=b){
                pq.add(new tree(c,a,b));
            }
        }
        int indegree=0;
        int sum=0;
        while (!pq.isEmpty()&&indegree<N-1) {
            tree tmp =pq.poll();
            int a=find(tmp.a,node);
            int b=find(tmp.b,node);
            if(a!=b) {
                union(a,b,node);
                sum += tmp.val;
                indegree++;
            }

        }
        System.out.println(sum);

    }
    static int find(int a,int[] node){
        if(node[a]==0){
            return a;
        }else {
            return node[a] =find(node[a],node);
        }
    }
    static void union(int a,int b,int[] node) {
        if(a>b) {
            int t= a;
            a=b;
            b=t;
        }
        node[b] =a;
    }
}
