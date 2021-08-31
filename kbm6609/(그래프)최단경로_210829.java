package Shortest_Path;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class line implements Comparable<line>{
    int value;
    int end;
    line(int end,int value) {
        this.end = end;
        this.value = value;
    }
    @Override
    public int compareTo(line o) {
        return this.value-o.value;
    }
}
public class ShortestPath_210829 {
    static ArrayList<line>[] tree;
    static int[] val;
    static PriorityQueue<line> pq;
    public static void main(String[] args) {
        StringBuilder sb= new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int N =sc.nextInt();
        int M =sc.nextInt();
        int root =sc.nextInt()-1;
        val=new int[N];
        tree = new ArrayList[N];
        pq =new PriorityQueue<>();
        for(int i=0;i<N; i++){
            val[i]=Integer.MAX_VALUE;
            tree[i] =new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a= sc.nextInt()-1;
            int b= sc.nextInt()-1;
            int c=sc.nextInt();
            tree[a].add(new line(b,c));
        }
        val[root]=0;
        pq.add(new line(root,0));
        while(!pq.isEmpty()){
            line tmp = pq.poll();
            for (int i = 0; i < tree[tmp.end].size(); i++) {
                line r = tree[tmp.end].get(i);
                if (val[r.end] >   r.value + tmp.value) {
                    val[r.end] = r.value + tmp.value;
                    pq.add(new line(r.end ,val[r.end]));
                }
            }
        }
        for (int i = 0; i < val.length; i++) {
            sb.append(val[i]==Integer.MAX_VALUE?"INF\n":val[i]+"\n");
        }
        System.out.print(sb);
    }
}
