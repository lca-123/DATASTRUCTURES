package ShangGuiGu.search;


import java.util.*;


//插值查找有序数组
    //优化二分查找中的mid计算 mid = left + (right - left)*(target - arr[left])/(arr[right] - arr[left])
//在数据量较大，关键字分布比较均匀时速度很快
//对于关键字分布不均匀的情况下，该方法不一定比二分查找快
public class InsertValueSearchDemo {
    public static void main(String[] args) {
        int[] arr = {1,2, 100, 100, 100};
        System.out.println(InsertValueSearchPlus(arr,0,arr.length - 1,100));
    }

    public static int InsertValueSearch(int[] arr, int left, int right, int target){
        if(left > right || target < arr[0] || target > arr[arr.length - 1]){
            return -1;
        }
        if(arr[left] == arr[right]){
            if(target == arr[left]){
                return left;
            }else{
                return -1;
            }
        }
        //求出mid
        int mid = left + (right - left)*(target - arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];

        if (target > midVal) {
            //向右递归
            return InsertValueSearch(arr, mid + 1, right, target);
        } else if (target < midVal) {
            //向左递归
            return InsertValueSearch(arr, left, mid - 1, target);
        } else {
            return mid;
        }
    }

    public static ArrayList<Integer> InsertValueSearchPlus(int[] arr, int left, int right, int target){
        //当left > right 时 没有找到返回-1
        if (left > right|| target < arr[0] || target > arr[arr.length - 1]) {
            return new ArrayList<Integer>();
        }

        if(arr[left] == arr[right]){
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = left; i <= right; i++) {
                list.add(i);
            }
            return list;
        }
        //求出mid
        int mid = left + (right - left)*(target - arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];

        if (target > midVal) {
            //向右递归
            return InsertValueSearchPlus(arr, mid + 1, right, target);
        } else if (target < midVal) {
            //向左递归
            return InsertValueSearchPlus(arr, left, mid - 1, target);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == target) {
                resIndexList.add(temp);
                temp--;
            }
            Collections.reverse(resIndexList);
            resIndexList.add(mid);
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == target) {
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }


    }
}
