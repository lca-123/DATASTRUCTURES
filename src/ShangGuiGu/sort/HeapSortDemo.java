package ShangGuiGu.sort;

import java.util.Arrays;

/*
    堆排序 O(nlogn) 不稳定
    1.构造大顶堆
    2.将大顶堆堆顶元素沉底
    3.重复上述步骤
 */
public class HeapSortDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9};
//        HeapSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
//        System.out.println(Arrays.toString(arr));
        long d1 = System.currentTimeMillis();
        HeapSort(arr);
        long d2 =System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println(d2 - d1);//10ms左右

    }
    //堆排序
    public static void HeapSort(int[] arr){
        int temp = 0;//临时变量
        //构建大顶堆
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //继续调整构造大顶堆
            //不需要像上边那样循环的原因：除了根节点和其左右节点其他非叶子节点已经是大顶堆
            adjustHeap(arr,0,j);
        }
    }

    /**不好理解 的确！！！！
     * 将一个数组（对应二叉树）调整成一个大顶堆
     * 完成将以i对应的非叶子节点的树调整为大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];//先取出当前的元素，保存为临时变量
        for (int k = i*2 + 1; k < length; k = k*2 +1) {//k指向i的左子节点
            if(k + 1 < length && arr[k] < arr[k + 1]){//说明左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if(arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//i指向k，继续循环比较
            }else{//上层非叶子节点构建大顶堆时下层非叶子节点已经构建完毕 只有在上层产生数据交换时才会破坏下层大顶堆
                //如果上层没有发生交换 则大顶堆已经构建好 直接break
                break;
            }
        }
        //for循环结束后，已经将i为父节点的树的最大值放到了顶(局部)
        arr[i] = temp;//将temp放到调整过后的位置
    }
}
