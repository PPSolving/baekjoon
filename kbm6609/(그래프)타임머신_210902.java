package Time_Machine;

import java.util.ArrayList;
import java.util.Scanner;

class line{
    int value;
    int end;
    line(int end,int value) {
        this.end = end;
        this.value = value;
    }
}
public class TimeMachine_210902 {
    static ArrayList<line>[] tree;
    static long[] val ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N =sc.nextInt();
        int M =sc.nextInt();
        StringBuilder sb=new StringBuilder();
        tree =new ArrayList[N];
        val =new long[N];
        boolean ch;
        for (int i = 0; i < N; i++) {
            val[i] =Long.MAX_VALUE;
            tree[i] =new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a= sc.nextInt()-1;
            int b= sc.nextInt()-1;
            int c =sc.nextInt();
            tree[a].add(new line(b,c));
        }
        val[0] = 0;
        for (int i = 0; i < N - 1; i++) {
            bellman();
        }
        if(bellman()){
            sb.append("-1");
        }else{
            for (int i = 1; i < val.length; i++) {
                if(val[i]!=Long.MAX_VALUE){
                    sb.append(val[i]+"\n");
                }else{
                    sb.append("-1\n");
                }
            }
        }
        System.out.print(sb);
    }
    static boolean bellman(){
        boolean ch =false;
        for (int i = 0; i < val.length; i++) {
            if(val[i]!=Long.MAX_VALUE){
                for (int j = 0; j < tree[i].size(); j++) {
                    line tmp =tree[i].get(j);
                    if(tmp.value+val[i]<val[tmp.end]){
                        val[tmp.end] =tmp.value+val[i];
                        ch =true;
                    }
                }
            }
        }
        return ch;
    }
}
