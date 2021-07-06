package ShangGuiGu.sort;

/*
    冒泡排序 O(n^2) 稳定
    多次遍历数组 如果当前元素与后一个元素逆序则交换 直到没有交换的那次遍历结束
    两两交换
 */

import java.util.Arrays;

public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        System.out.println(Arrays.toString(arr));
        long d1 = System.currentTimeMillis();
        BubbleSort(arr);
        long d2 =System.currentTimeMillis();
        System.out.println(d2 - d1);//10s左右
    }

    //用冒泡排序给数组排序
    public static void BubbleSort(int[] arr){
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }else{
                flag = false;
            }
        }
    }
}
