package Order_Height;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class OrderHeight_210812 {
    static Vector<Vector<Integer>> next;
    static Vector<Vector<Integer>> pre;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        int N,M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M =Integer.parseInt(st.nextToken());
        next =new Vector<>();
        pre =new Vector<>();
        for (int i = 0; i < N; i++) {
            pre.add(new Vector<>());
            next.add(new Vector<>());
        }
        for (int i = 0; i < M; i++) {
             st =new StringTokenizer(br.readLine());
            int a =Integer.parseInt(st.nextToken())-1;
            int b =Integer.parseInt(st.nextToken())-1;
            pre.get(b).add(a);
            next.get(a).add(b);
        }
        int sum =0;
        for (int i = 0; i < N; i++) {
            visit =new boolean[N];
            if(nextnum(i)+prenum(i) == N-1) sum++;
        }
        System.out.println(sum);
    }
    static int nextnum(int index){
        visit[index]= true;
        int sum =0;
        for(int i:next.get(index)){
            if(!visit[i]) sum+=nextnum(i)+1;
        }
        return sum;
    }
    static int prenum(int index){
        visit[index]= true;

        int sum =0;
        for(int i:pre.get(index)){
            if(!visit[i])  sum+=prenum(i)+1;
        }
        return sum;

    }
}
