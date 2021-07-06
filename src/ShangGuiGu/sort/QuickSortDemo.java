package ShangGuiGu.sort;

import java.util.Arrays;

/*
       快速排序 O(nlogn)  不稳定
       快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
        步骤1：从数列中挑出一个元素，称为 “基准”（pivot ）；
        步骤2：重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
        步骤3：递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSortDemo {
    public static void main(String[] args) {
        int[] arr = {100, 10, 8, 9, 8,20,  3, -2};
        QuickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            arr[i] = (int)(Math.random()*80000000);
//        }
////        System.out.println(Arrays.toString(arr));
//        long d1 = System.currentTimeMillis();
//        QuickSort(arr,0,arr.length - 1);
//        long d2 =System.currentTimeMillis();
//        System.out.println(d2 - d1);//不到1s 敲快
////        System.out.println(Arrays.toString(arr));
    }

    public static void QuickSort(int[] arr, int start, int end){
        int l = start;//左下标
        int r = end;//右下标

        //找到中轴值
        int pivot = arr[(start + end)/2];

        //开始循环 使得比pivot小的值都在左边 比pivot大的值都在右遍
        while(l < r){
            //在pivot左边一直找直到找到一个大于等于pivot的值
            while(arr[l] < pivot) l++;
            //在pivot右边一直找直到找到一个小于等于pivot的值
            while(arr[r] > pivot) r--;

            //说明pivot左右两边的值已经划分好 左边值都小于等于pivot 右边值都大于等于pivot
            if(l >= r) break;

            //交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果l 或 r已经到了pivot，则不需要移动了
            if(arr[l] == pivot) r--;
            if (arr[r] == pivot) l++;
        }

        //如果l==r,必须l++，r--，否则出现栈溢出
        if(l == r){
            l++;
            r--;
        }
        if(start < r) QuickSort(arr,start,r);
        if(l < end) QuickSort(arr,l,end);
    }

}
