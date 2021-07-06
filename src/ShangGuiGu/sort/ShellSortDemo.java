package ShangGuiGu.sort;

import java.util.Arrays;

/*
    希尔排序 O(nlogn) 不稳定
    1.希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，
      每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
    2.我们来看下希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，
      这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2…1}，称为增量序列。
    3. 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
        步骤1：选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
        步骤2：按增量序列个数k，对序列进行k 趟排序；
        步骤3：每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
*/
public class ShellSortDemo {
    public static void main(String[] args) {
//        int[] arr = {100, 10, 8, 9, 8,20,  3, -2};
//        ShellSortPlus(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        System.out.println(Arrays.toString(arr));
        long d1 = System.currentTimeMillis();
        ShellSortPlus(arr);
        long d2 =System.currentTimeMillis();
        System.out.println(d2 - d1);//不到1s
        System.out.println(Arrays.toString(arr));
    }

    //对每一个分组进行交换排序 7s左右
    public static void ShellSort(int[] arr){
        //循环步数
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            int temp = 0;
            //从第一组开始排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //对每一个分组进行插入排序 移位法 不到100ms
    public static void ShellSortPlus(int[] arr){
        //增量gap并逐步缩小增量
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            //从第gap个元素开始逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if(arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //找到位置，插入
                    arr[j] = temp;
                }
            }
        }
    }
}
