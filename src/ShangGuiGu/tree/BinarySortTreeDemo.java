package ShangGuiGu.tree;

/*
    二叉排序树或二叉搜索树(BST) 高效的对数据进行查询和添加
    数组如果添加中间元素 后边元素都要移动 [添加慢]
    链表添加快 [检索满]
    二叉排序树添加只需要找到相应位置加入即可 检索也很快
    对于二叉排序树的非叶子节点 其左节点的值比其小 右节点的值比其大
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr ={7, 3, 10, 12, 1, 5, 9};
        BinarySortTree bt = new BinarySortTree();
        //循环地添加节点到二叉排序树
        for (int value : arr) {
            bt.add(new Node(value));
        }
        bt.del(3);
        bt.infixlist();
    }
}


//创建二叉排序树
class BinarySortTree{
    private Node root;

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

//创建节点
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

    //添加节点 需要满足二叉排序树的要求
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
