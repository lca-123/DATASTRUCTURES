package TenAlgorithm.binarysearchnorecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(binarySearch(arr,1));
    }


    /**
     * 二分查找的非递归实现
     * @param arr 待查找数组(升序)
     * @param target 目标数
     * @return 返回对应下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){//说明可以继续查找
            int mid = (left + right)/2;
            if(arr[mid] == target){
                return mid;//找到了
            }else if(arr[mid] > target){
                right = mid - 1;//向左边查找
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
