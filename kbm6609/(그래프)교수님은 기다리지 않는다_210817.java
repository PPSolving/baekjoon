package Professor_Doesnt_Wait;


import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProfessorDoesntWait_210817 {
    static int[] tree;
    static int[] weight;
    public static void main(String[] args) throws IOException {
//        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        Scanner sc =new Scanner(System.in);

        while(true) {
            int N =sc.nextInt();
            int M =sc.nextInt();
            tree = new int[N + 1];
            weight = new int[N + 1];
            if(N==0 &&M==0) break;
            for(int i=0;i<M;i++) {
                char c =sc.next().charAt(0);
                int a =sc.nextInt();
                int b =sc.nextInt();
                if (c=='!') {
                    int w =sc.nextInt();
                    union(a, b, w);

                } else {
                    if (find(a) == find(b)) {
                        sb.append(weight[a] - weight[b] + "\n");
                    } else {
                        sb.append("UNKNOWN\n");

                    }
                }
            }
        }
        System.out.println(sb);

    }

    static void union(int a, int b,int w) {
        int ra =find(a);
        int rb =find(b);
        int wa =weight[a];
        int wb =weight[b];

        if(ra==rb) return;
        if(wa>wb+w){
          tree[rb] =ra;
          weight[rb] =wa -wb-w;
        }else{
            tree[ra] =rb;
            weight[ra] =wb + w -wa;
        }
    }


    static int find(int index) {
        if(tree[index] ==0){
            return index;
        }else{
            //index 의 집합을 find 의 결과로 수정
            int root = find(tree[index]);
            weight[index] += weight[tree[index]];
            return tree[index] =root;
        }

    }
}
