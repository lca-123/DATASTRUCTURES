package ShangGuiGu.tree;

//线索化二叉树
//将空闲的指针(叶子节点空指针)指向特定遍历方法的前驱节点和后继节点
//例如下面创建的二叉树 中序遍历为 8 3 10 1 14 6
//10 的前驱节点为 3  后继节点为 1
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索化二叉树的功能
        treeNode t1 = new treeNode(1, "tom");
        treeNode t2 = new treeNode(3, "jack");
        treeNode t3 = new treeNode(6, "smith");
        treeNode t4 = new treeNode(8, "mary");
        treeNode t5 = new treeNode(10, "king");
        treeNode t6 = new treeNode(14, "dim");
//        treeNode t7 = new treeNode(20, "max");

        //二叉树后边会递归创建 现在先手动创建
        t1.setLeft(t2);
        t1.setRight(t3);
        t2.setLeft(t4);
        t2.setRight(t5);
        t3.setLeft(t6);
//        t4.setLeft(t7);

        ThreadedBinaryTree tb = new ThreadedBinaryTree();
        tb.setRoot(t1);

        //前序线索化 1 3 8 10 6 14
        //中序线索化 8 3 10 1 14 6
        //后序线索化 8 10 3 14 6 1
        tb.postthreaded();


        //以10号节点测试 10号的前驱节点为3 后继节点为1
//        treeNode rightnode = t3.getRight();
//        System.out.println(rightnode);

        tb.postListThreaded();
    }
}


//定义线索化二叉树 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private treeNode root;
    //为了实现线索化 需要创建一个指向当前节点的前驱节点的指针
    //在递归线索化时，pre总是保留前一个节点
    private treeNode pre = null;

    public void setRoot(treeNode root) {
        this.root = root;
    }

    //对二叉树进行前序线索化
    public void prethreaded(){
        this.prethreaded(root);
    }
    public void prethreaded(treeNode node){
        //节点为空 不能线索化
        if(node == null){
            return ;
        }

        //线索化当前节点
        //先处理当前节点的前驱节点
        if(node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //再处理后继节点 在遍历到下一个节点时处理上一个节点的后继节点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理完一个节点后 让当前节点是下一个节点的前驱节点
        pre = node;

        //线索化左子树
        if(node.getLeftType() == 0) {
            prethreaded(node.getLeft());
        }
        //线索化右子树
        if(node.getRightType() == 0) {
            prethreaded(node.getRight());
        }
    }

    //遍历前序线索化二叉树
    public void preListThreaded() {
        //定义一个变量 存储当前遍历的节点 从root开始
        treeNode node = root;
        while (node != null) {
            //打印当前节点
            System.out.println(node);

            //循环找到leftType == 1 的节点 就是遍历的第一个节点
            if (node.getLeftType() == 0) {
                node = node.getLeft();
                continue;
            }

            //如果当前节点的右指针指向后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }

            //替换遍历的节点
            node = node.getRight();
        }
    }

    //对二叉树进行中序线索化
    public void infixthreaded(){
        this.infixthreaded(root);
    }
    public void infixthreaded(treeNode node){
        //节点为空 不能线索化
        if(node == null){
            return ;
        }

        //先线索化左子树
        if(node.getLeftType() == 0) {
            infixthreaded(node.getLeft());
        }

        //再线索化当前节点
        //先处理当前节点的前驱节点
        if(node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //再处理后继节点 在遍历到下一个节点时处理上一个节点的后继节点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理完一个节点后 让当前节点是下一个节点的前驱节点
        pre = node;

        //最后线索化右子树
        if(node.getRightType() == 0) {
            infixthreaded(node.getRight());
        }
    }

    //遍历中序线索化二叉树
    public void infixListThreaded() {
        //定义一个变量 存储当前遍历的节点 从root开始
        treeNode node = root;
        while (node != null) {
            //循环找到leftType == 1 的节点 就是遍历的第一个节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //打印当前节点
            System.out.println(node);

            //如果当前节点的右指针指向后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }

            //替换遍历的节点
            node = node.getRight();
        }
    }

    //对二叉树进行后序线索化
    public void postthreaded(){
        this.postthreaded(root);
    }
    public void postthreaded(treeNode node){
        //节点为空 不能线索化
        if(node == null){
            return ;
        }

        //线索化左子树
        if(node.getLeftType() == 0) {
            postthreaded(node.getLeft());
        }
        //线索化右子树
        if(node.getRightType() == 0) {
            postthreaded(node.getRight());
        }

        //线索化当前节点
        //先处理当前节点的前驱节点
        if(node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //再处理后继节点 在遍历到下一个节点时处理上一个节点的后继节点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理完一个节点后 让当前节点是下一个节点的前驱节点
        pre = node;
    }

    //遍历后序线索化二叉树 不会！以下代码错误 
    public void postListThreaded() {
        //定义一个变量 存储当前遍历的节点 从root开始
        treeNode node = root;
        while (node != null) {
            //打印当前节点
            System.out.println(node);

            //循环找到leftType == 1 的节点 就是遍历的第一个节点
            if (node.getLeftType() == 0) {
                node = node.getLeft();
                continue;
            }

            //如果当前节点的右指针指向后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }

            //替换遍历的节点
            node = node.getRight();
        }
    }
}