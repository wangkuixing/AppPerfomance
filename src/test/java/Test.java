import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        String s="hahaha: xixixi";
        System.out.println(s.substring(s.indexOf(":") + 1));

        int[] n=new int[10000];
        for (int i=0; i<10000; i++){
            n[i]=(int) (Math.random()*100+1);
        }

        long startTime1=System.currentTimeMillis();
        funcSort(n);
        long endTime1=System.currentTimeMillis();
        System.out.println("maopao run time is:" + (endTime1 - startTime1));

//        long startTime=System.currentTimeMillis();
//        func(n);
//        long endTime=System.currentTimeMillis();
//        System.out.println("insert run time is:" + (endTime - startTime));

    }

    public static void func(int[] a) {
        if (a.length<1){return;}

        for (int i=1; i<a.length; ++i){
            int value=a[i];
            int j=i-1;
            for (; j>=0; --j){
                if (a[j] > value){
                    a[j+1]=a[j];
                } else {break;}
            }
            a[j+1]=value;
        }
//        for (int i=0; i<a.length; i++){
//            System.out.println(a[i]);
//        }
    }

    public static void funcSort(int[] a) {
        if (a.length<1){return;}
        for (int i=0; i<a.length; ++i){
            boolean flag=false;
            for (int j=0; j<a.length-i-1; ++j){
                if (a[j] > a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag = true;
                }
            }
            if (!flag){break;}
        }
        for (int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }
}