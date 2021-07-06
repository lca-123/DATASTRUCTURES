package ShangGuiGu.tree;

public class treeNode {
    private int val;
    private String name;
    private treeNode left;
    private treeNode right;

    public treeNode(int val, String name) {
        this.val = val;
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public treeNode getLeft() {
        return left;
    }

    public void setLeft(treeNode left) {
        this.left = left;
    }

    public treeNode getRight() {
        return right;
    }

    public void setRight(treeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "treeNode{" +
                "val=" + val +
                ", name='" + name + '\'' +
                '}';
    }


    //编写前序遍历方法
    public void preList(){
        System.out.println(this);//输出父节点
        //递归左子树遍历
        if(this.left != null){
            this.left.preList();
        }

        //递归右子树遍历
        if(this.right != null){
            this.right.preList();
        }
    }

    //编写中序遍历方法
    public void infixList(){
        //递归左子树遍历
        if(this.left != null){
            this.left.infixList();
        }

        System.out.println(this);//输出父节点

        //递归右子树遍历
        if(this.right != null){
            this.right.infixList();
        }
    }

    //编写后序遍历方法
    public void postList(){
        //递归左子树遍历
        if(this.left != null){
            this.left.postList();
        }

        //递归右子树遍历
        if(this.right != null){
            this.right.postList();
        }

        System.out.println(this);//输出父节点
    }


    //编写前序查找方法 找到则返回节点 否则返回null
    public treeNode preSearch(int val){
        treeNode res = null;
        //判断当前节点是不是
        if (this.val == val){
            return this;
        }

        //递归左子树查找
        if (this.getLeft() != null){
            res = this.getLeft().preSearch(val);
        }
        if(res !=  null){
            return res;
        }

        //递归右子树查找
        if (this.getRight() != null){
            res = this.getRight().preSearch(val);
        }
        return res;
    }

    //编写中序查找方法 找到则返回节点 否则返回null
    public treeNode infixSearch(int val){
        treeNode res = null;

        //递归左子树查找
        if (this.getLeft() != null){
            res = this.getLeft().preSearch(val);
        }
        if(res !=  null){
            return res;
        }

        //判断当前节点是不是
        if (this.val == val){
            return this;
        }

        //递归右子树查找
        if (this.getRight() != null){
            res = this.getRight().preSearch(val);
        }
        return res;
    }

    //编写后序查找方法 找到则返回节点 否则返回null
    public treeNode postSearch(int val){
        treeNode res = null;

        //递归左子树查找
        if (this.getLeft() != null){
            res = this.getLeft().preSearch(val);
        }
        if(res !=  null){
            return res;
        }

        //递归右子树查找
        if (this.getRight() != null){
            res = this.getRight().preSearch(val);
        }
        if(res !=  null){
            return res;
        }

        //判断当前节点是不是
        if (this.val == val){
            return this;
        }

        return res;
    }


    //递归删除节点 如果是叶子节点则删除 如果是非叶子节点则删除子树
    public void del(int val){
        //首先判断左子节点是否要删除
        if(this.left != null && this.left.val == val){
            this.left = null;
            return ;
        }

        //然后判断右子节点是否要删除
        if(this.right != null && this.right.val == val){
            this.right = null;
            return ;
        }

        //遍历左子树
        if(this.left != null){
            this.left.del(val);
        }

        //遍历右子树
        if(this.right != null){
            this.right.del(val);
        }
    }


    //线索化二叉树新加变量
    private int leftType;//为0 表示指向左子树 为1 表示指向前驱节点
    private int rightType;//为0 表示指向右子树 为1 表示指向后继节点

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}
