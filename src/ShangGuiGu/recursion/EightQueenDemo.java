package ShangGuiGu.recursion;


//使用递归回溯算法解决八皇后问题
public class EightQueenDemo {
    //定义一个max表示有多少个皇后
     static int max = 8;
     static int count = 0;
    //定义一个数组array，保存皇后放置位置的结果
     static int[] arr = new int[max];

    public static void main(String[] args) {
        check(0);
        System.out.println(count);
    }

    //打印出皇后摆放的位置
    public static void print(){
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面摆放的皇后冲突
    public static boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
            return true;
    }

    //编写一个方法，放置第n个皇后
    public static void check(int n){
        if (n == max){//8个皇后已然放好，是一个结果
            print();
            count++;
            return;
        }
        //依次放入皇后，并检验是否冲突
        for (int i = 0; i < max; i++) {
            //先把这个皇后 n， 放入该行的第一列
            arr[n] = i;
            //判断当放置第n个皇后后，是否冲突
            if(judge(n)){//不冲突
                //接着放第n+1个皇后
                check(n + 1);
            }
        }
    }
}
