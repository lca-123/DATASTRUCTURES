package ShangGuiGu.search;

    //线性查找
public class SeqSearchDemo {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 6, 11, -2};
        int target = -2;
        System.out.println(SeqSearch(arr,target));
    }

    public static int SeqSearch(int[] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == target){
                return i;
            }
        }
        return -1;
    }
}
