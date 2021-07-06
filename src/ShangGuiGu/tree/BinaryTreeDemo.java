package ShangGuiGu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        treeNode t1 = new treeNode(1, "宋江");
        treeNode t2 = new treeNode(2, "卢俊义");
        treeNode t3 = new treeNode(3, "吴用");
        treeNode t4 = new treeNode(4, "林冲");
        treeNode t5 = new treeNode(5, "关胜");

        //手动创建二叉树
        t1.setLeft(t2);
        t1.setRight(t3);
        t3.setRight(t4);
        t3.setLeft(t5);
        b.setRoot(t1);

//        //前序遍历 1 2 3 5 4
//        b.preList();
//
//        //中序遍历 2 1 5 3 4
//        b.infixList();
//
//        //后序遍历 2 5 4 3 1
//        b.postList();

//        //查找
//        treeNode res = b.preSearch(5);
//        if(res != null){
//            System.out.println(res);
//        }else{
//            System.out.println("没有找到");
//        }

        b.del(3);
        b.preList();

    }
}


//定义二叉树
class BinaryTree {
    private treeNode root;

    public void setRoot(treeNode root) {
        this.root = root;
    }

    //前序遍历
    public void preList(){
        if (this.root != null){
            this.root.preList();
        }else{
            System.out.println("二叉树为空，无法前序遍历");
        }
    }

    //中序遍历
    public void infixList(){
        if (this.root != null){
            this.root.infixList();
        }else{
            System.out.println("二叉树为空，无法中序遍历");
        }
    }

    //后序遍历
    public void postList(){
        if (this.root != null){
            this.root.postList();
        }else{
            System.out.println("二叉树为空，无法后序遍历");
        }
    }

    //前序查找
    public treeNode preSearch(int val){
        if (this.root != null){
            return this.root.preSearch(val);
        }else{
            return null;
        }
    }

    //中序查找
    public treeNode infixSearch(int val){
        if (this.root != null){
            return this.root.infixSearch(val);
        }else{
            return null;
        }
    }

    //后序查找
    public treeNode postSearch(int val){
        if (this.root != null){
            return this.root.postSearch(val);
        }else{
            return null;
        }
    }

    //删除节点
    public void del(int val){
        if(this.root == null){
            System.out.println("空树无法删除");
        }else {
            if(root.getVal() == val){
                root = null;
            }else{
                root.del(val);
            }
        }

    }
}

