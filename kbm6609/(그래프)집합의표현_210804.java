package BJ_Representation_set;

import java.util.Scanner;
public class RepresentationSet_210804 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n =sc.nextInt();
        int m =sc.nextInt();
        int[] parent = new int[n+2];
        for(int i= 0;i<m;i++){
            int k =sc.nextInt();
            int x =sc.nextInt()+1;
            int y =sc.nextInt()+1;
            if(k==0){
                union(x,y,parent);
            }else {
                x= find(x,parent);
                y= find(y,parent);
                System.out.println("x = "+x +" y = "+y);

                if(x ==y) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }

    static void union(int x, int y, int[] parent) {
        //일반적으로 합집합을 할때 작은 같에 큰값이 들어 가기떄문에 find 로 둘중 흡수할 흡수 당할 집합을 알기위해
        //루트 노드를 찾는다
        x = find(x,parent);
        y = find(y,parent);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        //두 노드가 같은 집합에 속하지않을때 실행
        if(x!=y){
            //y가 더 큰값이 되게 해준다
            if(x>y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            parent[y]=x;
        }

    }

    static int find(int x, int[] parent) {
        //개념상으론 자기자신의 값을 가지고 잇어야하지만 배열을 인덱스 값으로 초기화 해야하는 불필요함을 줄이기 위해
        //디폰트 값인 0이 나오면 해당 인덱스가 루트 노드 임을 이용
        if (parent[x] == 0){
            return x;
        }else {
            //부모 노드가 0이 아니라면 루트 노드가 아니라는것이므로
            // 루트노드를 찾기 위해 연결된 부모 노드로 이동 할것이고 최종적으로
            //find 함수의 반환값은 루트 노드가 될것이므로 루트노드를 찾는 과정중 접근한 노드들의 부모노드를 루트 노드로 설정
            return parent[x] =find(parent[x],parent);
        }
    }
}
