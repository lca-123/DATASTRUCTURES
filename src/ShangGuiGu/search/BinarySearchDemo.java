package ShangGuiGu.search;

import java.util.*;


//二分查找的前提是 数组是有序的
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] arr = {1, 4, 20, 40, 100, 100, 100};
        System.out.println(BinarySearchPlus(arr, 0, arr.length - 1, 100));
    }

    //完善：查找到所有值之后返回
    //思路：找到mid后不马上返回，向mid索引值的左右扫描，将下表加入到ArrayList中
    public static ArrayList<Integer> BinarySearchPlus(int[] arr, int left, int right, int target) {
        //当left > right 时 没有找到返回-1
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (target > midVal) {
            //向右递归
            return BinarySearchPlus(arr, mid + 1, right, target);
        } else if (target < midVal) {
            //向左递归
            return BinarySearchPlus(arr, left, mid - 1, target);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == target) {
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            Collections.reverse(resIndexList);
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == target) {
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }


    }


    public static int BinarySearch(int[] arr, int left, int right, int target) {
        //当left > right 时 没有找到返回-1
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (target > midVal) {
            //向右递归
            return BinarySearch(arr, mid + 1, right, target);
        } else if (target < midVal) {
            //向左递归
            return BinarySearch(arr, left, mid - 1, target);
        } else {
            return mid;
        }
    }
}