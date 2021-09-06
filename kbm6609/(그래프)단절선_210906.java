package Cut_Off_Line;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class line implements Comparable<line> {
    int a,b;

    line(int a, int b) {
        if(a>b){
            int tmp =a;
            a= b;
            b=tmp;
        }
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(line o) {
        if(o.a ==this.a){
            return this.b-o.b;
        }else {
            return this.a - o.a;
        }
    }
}

public class CutOffLine_210906 {
    static ArrayList<Integer>[] tree;
    static int[] order;
    static int count;
    static PriorityQueue<line> pq;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        tree =new ArrayList[V];
        order =new int[V];
        count =0;
        pq =new PriorityQueue<>();
        for (int i = 0; i < V; i++) {
            tree[i] =new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            tree[a].add(b);
            tree[b].add(a);
        }
        for (int i = 0; i < V; i++) {
            if(order[i]==0){
                dfs(i,-1);
            }
        }
        StringBuffer sb =new StringBuffer();
        sb.append(pq.size()+"\n");
        while (!pq.isEmpty()) {
            line tmp =pq.poll();
            sb.append((tmp.a+1)+" "+(tmp.b+1)+"\n");
        }
        System.out.print(sb);
    }
    static int dfs(int index,int pa){
        order[index] = ++count;
        int ret =count;

        for (int i = 0; i < tree[index].size(); i++) {
            int rep = tree[index].get(i);
            if(rep ==pa) continue;
            if(order[rep] ==0) {
                int tmp = dfs(rep, index);
                if (order[rep] <= tmp){
                    pq.add(new line(index,rep));
                }
                ret =Math.min(ret,tmp);
            }else{
                ret =Math.min(ret,order[rep]);
            }
        }
//        if(index ==6) {
//            for (int i = 0; i < tree[index].size(); i++) {
//                int rep = tree[index].get(i);
//                if (rep == pa) continue;
//                if (order[rep] == 0) {
//                    int tmp = dfs(rep, index);
//                    if (order[rep] <= tmp) {
//                        System.out.println((index + 1) + " " + (rep + 1) + " " + tmp);
//                        pq.add(new line(index, rep));
//                    }
//                    ret = Math.min(ret, tmp);
//                } else {
//                    ret = Math.min(ret, order[rep]);
//                }
//            }
//        }

        return ret;
    }
}
