package ppsolve.baejoon;

import java.util.Scanner;

public class P1717 {
    static int[] parent;
    static int N,M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }
        M = sc.nextInt();
        for(int i=0; i<M; i++){
            int c = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(c == 1){
                if(find(a) != find(b)) System.out.println("NO");
                else System.out.println("YES");
            }else{
                union(a,b);
            }
        }
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        int ap = find(a);
        int bp = find(b);
        if(ap != bp){
            parent[bp] = ap;
        }
    }

}
