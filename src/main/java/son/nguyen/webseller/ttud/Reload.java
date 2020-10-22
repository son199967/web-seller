package son.nguyen.webseller.ttud;

import java.util.Scanner;

public class Reload {
   public static int n;
   public  static int[] t;
   public static  int r;
   public  static  int[][] rt;
   public static  int a;
    public static void main(String[] args) {
        a=1;
        Scanner sc =new Scanner(System.in);
        n=sc.nextInt();
        t=new int[n];
        for (int i=0;i<n;i++){
            t[i]=sc.nextInt();
        }
        r=sc.nextInt();
        rt= new int[r][2];
        for (int i=0;i<r;i++){
            for (int j=0;j<2;j++){
                rt[i][j]=sc.nextInt();
            }
        }
       for (int i=0;i<r;i++){
           if (rt[i][0]==1){
               if ((t[rt[i][0] - 1] != rt[i][1] && t[rt[i][0] - 2] != rt[i][1])){
                   a++;
               }
           }else  if(rt[i][0]==r){
               if ((t[rt[i][0] - 1] != rt[i][1] && t[rt[i][0]] != rt[i][1])){
                   a++;
               }
           }else {
               if ((t[rt[i][0] - 1] != rt[i][1] && t[rt[i][0] - 2] != rt[i][1] && t[rt[i][0]] != rt[i][1])) {
               a+=2;
               }
           }
           t[rt[i][0]-1]= rt[i][1];
           System.out.println(a);
       }
    }

}
