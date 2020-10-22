package son.nguyen.webseller.ttud;

import java.util.Scanner;

public class signal {
    static  int N;
    static  int b;
    static  long[] a;
    public static void main(String[] args) {
        Integer aX=-1;
        Scanner sc =new Scanner(System.in);
        N=sc.nextInt();
        b=sc.nextInt();
        if (b>N){
            System.out.println(aX);
        }
        a= new long[N];
        if (N<3||N>200000) {
            System.out.println(aX);
            return;
        }
        if (b<1||b>50) {
            System.out.println(aX);
            return;
        }

        for (int i=0;i<N;i++){
            a[i]=sc.nextInt();
            if (a[i]<=0){
                System.out.println("-1");
                return;
            }

        }
        long Max1= 0;
        long Max2= 0;
        for (int i=0;i<b-1;i++){
           Max1 = a[i]>Max1?a[i]:Max1;
        }
        for (int i=b;i<N;i++){
            Max2 = a[i]>Max2?a[i]:Max2;
        }
        if (Max1-a[b-1]<b||Max2-a[b-1]<b) {
            System.out.println("-1");
            return;
        }
        System.out.println(Max1+Max2 - 2*a[b-1]);
    }
}
