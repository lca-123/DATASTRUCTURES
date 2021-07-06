package ShangGuiGu.sort;

import java.util.ArrayList;
import java.util.Arrays;

/*
    基数排序 O(n*k) 稳定
    基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
    有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。
    最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。基数排序基于分别排序，分别收集，所以是稳定的。
    准备十个桶依次为0到9，先按照所有元素的个位数将元素依次放入桶中，再从0到9依次从桶中依次取出元素组成新数组；
    再按照所有元素的十位数重复上述操作，直到最大数字的位数
    此时数组已经排序完毕
 */
public class RadixSortDemo {
    public static void main(String[] args) {
//        int[] arr = {100, 10, 8, 9, 8,20,  3};
//        RadixSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random()*80000000);
        }
//        System.out.println(Arrays.toString(arr));
        long d1 = System.currentTimeMillis();
        RadixSort(arr);
        long d2 =System.currentTimeMillis();
        System.out.println(d2 - d1);//3s左右
//        System.out.println(Arrays.toString(arr));
    }

    public static void RadixSort(int[] arr){
        //定义一个二维数组表示10个桶
        ArrayList<ArrayList<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucket.add(new ArrayList<Integer>());
        }

        //获取最大数字的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int maxDigit = (max + "").length();

        //开始循环
        int mod = 10, div = 1;
        for (int i = 0; i < maxDigit; i++, mod *= 10, div*= 10) {
            //将数字放入桶中
            for (int value : arr) {
                int num = (value % mod) / div;
                bucket.get(num).add(value);
            }
            //从桶中取出元素放回数组并将桶清空
            int index = 0;
            for (ArrayList<Integer> b:bucket) {
                for (int item: b){
                    arr[index++] = item;
                }
                b.clear();
            }
        }
    }
}
