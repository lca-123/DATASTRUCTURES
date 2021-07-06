package ShangGuiGu.search;

import java.util.Arrays;

//斐波那契查找（黄金分割法）
    //同样优化二分查找中的mid计算 mid = left + F(k - 1) - 1
public class FibonacciSearchDemo {
    static int maxFibSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,2, 3, 4, 5, 6};
        System.out.println(FibSearch(arr,10));
    }

    public static int FibSearch(int[] arr, int target){
        int left = 0, right = arr.length - 1;
        int k = 1;//表示斐波那契分割的下标
        int mid = 0;
        int[] f = fib();
        while(right > f[k - 1]) k++;
        //因为fib(k) - 1可能大于数组长度 因此需要使用Array类构造新的数组并指向temp[]
        //不足的部分用0补足 实际上需要用arr中最后的数填充
        int[] temp = Arrays.copyOf(arr,f[k]);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        //开始查找
        while(left <= right){
            mid = left + f[k - 1] - 1;
            if(target < temp[mid]){
                right = mid - 1;
                k--;
            }else if(target > temp[mid]){
                left = mid + 1;
                k -= 2;
            }else {
                //需要确定返回哪个下标
                return Math.min(mid, right);
            }
        }

        //没有找到
        return -1;
    }

    //返回斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxFibSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxFibSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
