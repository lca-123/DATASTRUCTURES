package ShangGuiGu.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/*
    赫夫曼树 带权路径长(wpl)最小的二叉树 带权路径长 = Σ叶子节点权值×层数
    构建方法：
    1.将数列从小到大排序，每一个数据都是一个节点，每个节点可看成一个最简单的二叉树
    2.取出根节点权值最小的两颗二叉树
    3.组成新的二叉树，该二叉树的根节点权值是前面两颗二叉树根节点权值之和
    4.再将这颗新二叉树以根节点权值大小进行排序，重复上述过程
    5.直到数列中所有元素都被处理
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        //创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(arr);
        ///测试赫夫曼树 - 前序遍历
        prelist(huffmanTreeRoot);//67, 29, 38, 15, 7, 8, 23, 10, 4, 1, 3, 6, 13
    }

    //创建赫夫曼树方法
    public static Node createHuffmanTree(int[] arr){
        //为了方便 遍历arr并将arr的每个元素构建一个node并将node放入到一个ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for(int val:arr){
            nodes.add(new Node(val));
        }
        while (nodes.size() > 1) {
            //排序 从小到大Node
            Collections.sort(nodes);

            //取出权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //构建一颗新的二叉树
            Node parent = new Node(leftNode.val + rightNode.val);
            parent.left = leftNode;
            parent.right = rightNode;

            //从nodes中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parent加入nodes
            nodes.add(parent);
        }

        //返回赫夫曼树的root节点
        return nodes.get(0);
    }

    //前序遍历
    public static void prelist(Node root){
        if(root != null){
            root.prelist();
        }else{
            System.out.println("空树，无法遍历");
        }
    }
}

//创建节点类
//为了方便Collections排序 实现Comparable接口
class Node implements Comparable<Node>{
    int val;//节点权值
    Node left;//左子节点
    Node right;//右子节点

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" + "val=" + val + '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;//从小到大进行排序
    }

    //写一个前序遍历
    public void prelist(){
        System.out.println(this);
        if(this.left != null) this.left.prelist();
        if(this.right != null) this.right.prelist();
    }
}