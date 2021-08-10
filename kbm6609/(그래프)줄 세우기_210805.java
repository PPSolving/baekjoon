package Line_up;

import java.util.Scanner;
import java.util.Vector;

public class Lineup_210805 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        boolean[] ch =new boolean[n];
        Vector<Vector<Integer>> point = new Vector<>();
        for(int i=0;i<n;i++){
            point.add(new Vector<>());
        }
        for (int i = 0; i < m; i++) {
            int a= sc.nextInt()-1;
            int b= sc.nextInt()-1;
            if(a!=b){ point.get(b).add(a);}
        }
        for (int i = 0; i < n; i++) {
            run(i,point,ch);
        }

    }
    static void run(int k, Vector<Vector<Integer>> p ,boolean[] ch){
        if(!ch[k]) {
            Vector<Integer> point = p.get(k);
            for (int i = 0; i < point.size(); i++) {
                run(point.get(i),p,ch);
            }
            System.out.print((k+1) + " ");
            ch[k] = true;
        }
    }
}
