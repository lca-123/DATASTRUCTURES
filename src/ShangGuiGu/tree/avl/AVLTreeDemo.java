package ShangGuiGu.tree.avl;
/*
    普通二叉排序树的弊端：在创建时可能出现左右子树偏移过大 导致像一个链表 遍历慢
    解决：
        平衡二叉树 AVL树 红黑树 即根节点的左右子树高度差不大于1 且左右子树也为平衡二叉树
        平衡二叉树本身是二叉排序树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //创建一个AVL树
        AVLTree avl = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avl.add(new Node(arr[i]));
        }

        //中序遍历
        avl.infixlist();

        //测试代码
        System.out.println("树的高度为" + avl.getRoot().height());
        System.out.println("树的右子树高度为" + avl.getRoot().leftHeight());
        System.out.println("树的左子树高度为" + avl.getRoot().rightHeight());
    }
}

//创建AVL树 复用二叉排序树的代码 增加功能
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public void infixlist(){
        if(root != null){
            root.infixlist();
        }else{
            System.out.println("空树无法遍历");
        }
    }

    //查找要删除的节点
    public Node search(int val){
        if(root == null) {
            return null;
        }else{
            return root.search(val);
        }
    }

    //查找父节点
    public Node searchParent(int val){
        if(root == null){
            return null;
        }else{
            return root.searchParent(val);
        }
    }

    //删除节点
    public void del(int val){
        if(root == null){
            return;
        }else{
            //先找到需要删除的节点 targetNode
            Node targetNode = search(val);
            if(targetNode == null){
                System.out.println("二叉排序树中没有要删除的节点");
                return;
            }
            //如果我们发现二叉排序树只有一个节点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //查找targetNode的父节点
            Node parent = searchParent(val);

            //如果删除的节点是叶子节点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode是父节点的左子节点还是右子节点 并将parent节点的对应子节点置空
                if(parent.left != null && parent.left.val == val){
                    parent.left = null;
                }else if(parent.right != null && parent.right.val == val){
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){
                //从右子树找最小值替换当前节点
                targetNode.val = delRightTreeMin(targetNode.right);
                //也可从左子树找最大值替换当前节点
            }else{
                //删除只有一颗子树的节点
                //如果要删除的节点有左节点且不是根节点
                if(targetNode.left != null){
                    if(parent!= null) {//要删除的不是根节点
                        if (parent.left.val == val) {//targetNode是parent的左子节点
                            parent.left = targetNode.left;
                        } else {//targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    }else{//parent为空 即要删除的是根节点
                        root = targetNode.left;
                    }
                }else{//如果要删除的节点有右节点
                    if(parent != null) {
                        if (parent.left.val == val) {//targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        } else {//targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }
                }
            }


        }
    }

    /**
     *
     * @param node 要删除的节点（当作二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小节点的值 同时删除该节点
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        while(target.left != null){
            target = target.left;
        }
        int temp = target.val;
        del(temp);
        return temp;
    }



}

//创建节点 复用二叉排序树的代码 增加功能
class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" + "val=" + val + '}';
    }

    //返回以该节点为根节点的树的高度
    public int height(){
        return Math.max(this.left == null? 0 : this.left.height()
                , this.right == null ? 0 : this.right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight(){
        if(this.left == null){
            return 0;
        }else{
            return this.left.height();
        }
    }

    //返回右子树高度
    public int rightHeight(){
        if(this.right == null){
            return 0;
        }else{
            return this.right.height();
        }
    }

    //左旋转方法
    //左右子树高度差大于1 且左子树高度小 则需要将二叉树左旋转至平衡
    private void leftRotate(){
        //创建新节点 以当前根节点的值为值
        Node newnode = new Node(val);

        //把新的节点的左子树设置为当前节点的左子树
        newnode.left = left;

        //把新节点的右子树设置为当前节点的右子树的左子树
        newnode.right = right.left;

        //把当前节点的值换成右子节点的值
        val = right.val;

        //把当前节点的右子树设置为当前节点的右子树的右子树
        right = right.right;

        //把当前节点的左子节点设置为新节点
        left = newnode;
    }

    //右旋转方法
    //左右子树高度差大于1 且右子树高度小 则需要将二叉树右旋转至平衡
    private void rightRotate(){
        //创建新节点 以当前根节点的值为值
        Node newnode = new Node(val);

        //把新的节点的右子树设置为当前节点的右子树
        newnode.right = right;

        //把新节点的左子树设置为当前节点的左子树的右子树
        newnode.left = left.right;

        //把当前节点的值换成左子节点的值
        val = left.val;

        //把当前节点的左子树设置为当前节点的左子树的左子树
        left = left.left;

        //把当前节点的右子节点设置为新节点
        right = newnode;
    }

    //添加节点 需要满足二叉排序树的要求
    //如果不符合avl树的要求则需要旋转
    public void add(Node node){
        if(node == null){
            return ;
        }
        //判断传入的节点的值 和当前子树的根节点的值的关系
        if(node.val < this.val){
            if(this.left == null){
                //左子节点为空 直接添加
                this.left = node;
            }else{//左子树递归
                this.left.add(node);
            }
        }else{//node.val >= this.val
            if(this.right == null){
                //右子节点为空 直接添加
                this.right = node;
            }else{//右子树递归
                this.right.add(node);
            }
        }
        //左右子树高度差大于1 且左子树高度小 则需要将二叉树左旋转至平衡
        if(rightHeight() - leftHeight() > 1){
            //如果它的右子树的左子树高度大于它的右子树高度 则需要先将右子树右旋转
            if(right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();
            return ;
        }

        //左右子树高度差大于1 且右子树高度小 则需要将二叉树右旋转至平衡
        if(leftHeight() - rightHeight() > 1){
            //如果它的左子树的右子树高度大于它的左子树高度 则需要先将左子树左旋转
            if(left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
            return ;
        }
    }

    //中序遍历
    public void infixlist(){
        if(this.left != null){
            this.left.infixlist();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixlist();
        }
    }

    //查找要删除的节点
    public Node search(int val){
        //如果找到返回该节点 没找到返回null
        if(this.val == val){
            return this;
        }else if(this.val > val){
            //向左子树递归查找
            if (this.left == null) {
                return null;
            }else{
                return this.left.search(val);
            }
        }else{
            //向右子树递归查找
            if(this.right == null){
                return null;
            }else{
                return this.right.search(val);
            }
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int val){
        if((this.left != null && this.left.val == val)||
                (this.right != null && this.right.val == val)){
            return this;
        }else{
            if(val < this.val && this.left != null){
                //如果查找的值小于当前节点的值 且当前节点的左子节点不为空 递归左子树
                return this.left.searchParent(val);
            }else if(val >= this.val && this.right != null){
                //如果查找的值大于等于当前节点的值 且当前节点的右子节点不为空 递归右子树
                return this.right.searchParent(val);
            }else{
                return null;//
            }
        }

    }
}
