package TenAlgorithm.dac;

public class HanoiTower {
    public static void main(String[] args) {
        //hanoitower(30,'A','B','C');
        //25 - 33554431
        //63 - 9223372036854775807
        //64 - 18446744073709551615
        //System.out.println(Long.toString(hanoicount(63)));
    }

    private static long count = 1;
    //使用分治算法 解决汉诺塔
    public static void hanoitower(int num, char a, char b, char c){
        //如果只有一个盘子
        if(num == 1){
            System.out.println(count + " : " + "第 1 个盘从 " + a + " -> " + c);
            count++;
        }else{
            //先把n-1个盘子从a移到b
            hanoitower(num - 1, a,c,b);
            //将最下面一个盘从a移到c
            System.out.println(count + " : " + "第 "+ num + " 个盘从 " + a + " -> " + c);
            count++;
            //最后将n-1个盘子从b移到c
            hanoitower(num - 1, b,a,c);
        }
    }

    public static long hanoicount(int num){
        if(num == 1) return 1;
        return (long)(2*hanoicount(num - 1) + 1);
    }
}
