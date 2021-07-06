package ShangGuiGu.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree a = new ArrBinaryTree(arr);
        a.preList();
    }
}
//顺序存储二叉树 用数组存储的二叉树 通常用于存储完全二叉树
//下标为n的元素的左子节点为2n+1 右子节点为2n+2 父节点为(n-1)/2
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //顺序存储二叉树的前序遍历
    public void preList(){
        this.preList(0);
    }
    public void preList(int index) {
        //如果数组为空或arr.length为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
        }
        System.out.print(arr[index] + " ");

        //向左遍历
        if (index * 2 + 1 < arr.length) {
            preList(2 * index + 1);
        }

        //向右遍历
        if (index * 2 + 2 < arr.length) {
            preList(2 * index + 2);
        }
    }
}