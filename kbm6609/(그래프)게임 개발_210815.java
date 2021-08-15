package Game_Development;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class GameDevelopment_210815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> qe = new LinkedList();
        Vector<Vector<Integer>> arr = new Vector<>();
        int[][] time =new int[N][2];
        int[] indegree =new int[N];
        for (int i = 0; i < N; i++) {
            arr.add(new Vector<>());
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st =new StringTokenizer(br.readLine());
            int count =st.countTokens();
            for(int j= 0 ;j<count;j++){
                int t = Integer.parseInt(st.nextToken());
                if(j==0) time[i][0] =t;
                else if(t!=-1) {
                    arr.get(t-1).add(i);
                    indegree[i]++;
                }
            }
        }

        for(int i=0;i<N;i++){
            if(indegree[i] ==0){
                qe.add(i);
                indegree[i] =-1;
            }
        }
        while (!qe.isEmpty()) {
            int t = qe.poll();
            time[t][0] +=time[t][1];
            for (int i: arr.get(t)){
                indegree[i] --;
                time[i][1] = time[i][1]<time[t][0]?time[t][0]:time[i][1];
                if(indegree[i] ==0) {
                    qe.add(i);
                    indegree[i] =-1;
                }
            }

        }
        for(int[] i : time){
            System.out.println(i[0]);
        }

    }
}
