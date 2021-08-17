package ppsolve.baejoon;

import java.util.Scanner;

public class P3830 {
    static int N,M;
    static int[][] parent; //parent[i][2] => i의 부모 : [i][0], i의 부모가 i보다 큰 무게 : [i][1]
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        StringBuffer answerBuf = new StringBuffer();
        while(N != 0 || M != 0){
            parent = new int[N+1][2];
            for(int i=1; i<=N; i++){
                parent[i][0] = i;
            }
            for(int i=1; i<=M; i++){
                char ch = sc.next().charAt(0);
                int a = sc.nextInt();
                int b = sc.nextInt();
                if(ch == '!'){
                    int w = sc.nextInt();
                    union(a,b,w);
                }else if(ch == '?'){
                    int pa = find(a);
                    int pb = find(b);
                    if(pa != pb){
                        answerBuf.append("UNKNOWN\n");
                        continue;
                    }
                    int dif = parent[a][1]-parent[b][1];
                    answerBuf.append(dif+"\n");
                }
            }
            N = sc.nextInt();
            M = sc.nextInt();
        }

        System.out.println(answerBuf.toString());


    }
    static void union(int a, int b, int w){ //b가 a보다 w 무겁다
        int pa = find(a);
        int pb = find(b);
        if(pa != pb){
            if(parent[a][1] < parent[b][1] + w){
                parent[pa][0] = pb;
                parent[pa][1] =parent[b][1]+w-parent[a][1];
            } else{
                parent[pb][0] = pa;
                parent[pb][1] = parent[a][1]-w-parent[b][1];
            }
        }
    }
    static int find(int a){ //true : a. false : b
        if(parent[a][0] == a){
            return a;
        }
        int paren = find(parent[a][0]);
        parent[a][1] = parent[parent[a][0]][1]+parent[a][1];
        parent[a][0] = paren;
        return paren;
    }
}
