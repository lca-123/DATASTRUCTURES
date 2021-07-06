package ShangGuiGu.sort;

/**
    归并排序 O(nlogn) 稳定
        步骤1：把长度为n的输入序列分成两个长度为n/2的子序列；
        步骤2：对这两个子序列分别采用归并排序；
        步骤3：将两个排序好的子序列合并成一个最终的排序序列。
 */
public class MergeSortDemo {
    public static void main(String[] args) {
//        int[] arr = {100, 10, 8, 9, 8,20,  3, -2};
//        int[] temp = new int[arr.length];
//        MergeSort(arr,0,arr.length - 1,temp);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random()*80000000);
        }
        int[] temp = new int[arr.length];
        long d1 = System.currentTimeMillis();
        mergeSort(arr,0,arr.length - 1,temp);
        long d2 =System.currentTimeMillis();
        System.out.println(d2 - d1);//不到1s左右
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right)/2;//中间索引
            //向左递归
            mergeSort(arr,left,mid,temp);
            //向右递归
            mergeSort(arr,mid + 1,right,temp);
            //合并
            merge(arr,left,right,mid,temp);
        }
    }

    /**
     * 合并算法
     * @param arr 原始数组
     * @param left 左边索引
     * @param right 右边索引
     * @param mid 中间索引
     * @param temp 中转数组
     */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp){
        int i = left, j = mid + 1, index = 0;//index 中间数组的当前索引

        //先把左右两边的有序数据按照规则填充到temp数组，直到左右两边有一边处理完毕
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[index++] = arr[i++];
            }else{
                temp[index++] = arr[j++];
            }
        }
        //把剩余的那边依次填充到temp中
        while(i <= mid) temp[index++] = arr[i++];
        while(j <= right) temp[index++] = arr[j++];
        //将temp数组的元素拷贝到arr
        index = 0;
        i = left;
        while (i <= right) {
            arr[i++] = temp[index++];
        }
    }
}
