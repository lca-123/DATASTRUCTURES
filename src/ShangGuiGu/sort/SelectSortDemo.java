package ShangGuiGu.sort;

import java.util.Arrays;

/*
    选择排序 O(n^2) 不稳定
    1.从数组中找到最小的一个元素 与第一个交换
    2.从第二个元素开始找最小的一个元素，与第二个元素交换
    3.以此类推
*/
public class SelectSortDemo {
    public static void main(String[] args) {
//        int[] arr = {10, 8, 8, 3, -2};
//        SelectSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        System.out.println(Arrays.toString(arr));
        long d1 = System.currentTimeMillis();
        SelectSort(arr);
        long d2 =System.currentTimeMillis();
        System.out.println(d2 - d1);//3s左右

    }

    //用选择排序给数组排序
    public static void SelectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(i != min){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
}
