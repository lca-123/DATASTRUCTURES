package ShangGuiGu.sort;


import java.util.Arrays;

/*
    插入排序 O(n^2) 稳定
    1.把n个待排序的元素看成是一个有序表和一个无序表
    2.开始时有序表中只有一个元素，无序表中有n - 1个元素
    3.排序中从无序表中抽出一个元素插入到有序表中的相应位置
*/
public class InsertionSortDemo {
    public static void main(String[] args) {
//        int[] arr = {100, 10, 8, 9, 8,20,  3, -2};
//        InsertionSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        System.out.println(Arrays.toString(arr));
        long d1 = System.currentTimeMillis();
        InsertionSort(arr);
        long d2 =System.currentTimeMillis();
        System.out.println(d2 - d1);//不到1s
        System.out.println(Arrays.toString(arr));
    }

    //用插入排序给数组排序(written by myself) 交换法 3s左右
    public static void InsertionSortMyself(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[insertIndex]){
                    int temp = arr[j];
                    arr[j] = arr[insertIndex];
                    arr[insertIndex] = temp;
                    insertIndex--;
                }else{
                    break;
                }
            }
        }
    }

    //官方写法 移位法
    public static void InsertionSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i, insertVal = arr[i];
            while(insertIndex > 0 && insertVal < arr[insertIndex - 1]){
                arr[insertIndex] = arr[insertIndex - 1];
                insertIndex--;
            }
            arr[insertIndex] = insertVal;
        }
    }
}
