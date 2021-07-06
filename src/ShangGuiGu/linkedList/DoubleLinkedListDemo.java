package ShangGuiGu.linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList d = new DoubleLinkedList();
        d.addByOrder(1,"宋江","及时雨");
        d.addByOrder(4, "林冲","包子头");
        d.addByOrder(3, "吴用","智多星");
        d.addByOrder(2,"卢俊义","玉麒麟");
        d.list();
        System.out.println();
        d.update(4,"林冲","豹子头");
        d.list();
        System.out.println();
        d.delete(5);
        d.list();
    }
}

//创建一个双向链表类
class DoubleLinkedList {

    private DoubleHeroNode head = new DoubleHeroNode(0, "","");

    //遍历双向链表
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //添加节点到双向链表的最后
    public void add(int val, String name, String nickname){
        //创建新节点
        DoubleHeroNode newNode = new DoubleHeroNode(val,name,nickname);
        //因为head不能动，因此需要创建一个辅助遍历temp
        DoubleHeroNode temp = head;
        //遍历链表，找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        //将最后一个节点的next 指向新节点
        //构成双向
        temp.next = newNode;
        newNode.pre = temp;
    }

    //按顺序将新节点插入链表
    public void addByOrder(int val, String name, String nickname){
        //创建新节点
        DoubleHeroNode newNode = new DoubleHeroNode(val,name,nickname);
        //因为head不能动，因此需要创建一个辅助遍历temp
        DoubleHeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false
        while(true){
            if(temp.next == null) break;
            if(temp.next.val > newNode.val){
                break;
            }else if(temp.next.val == newNode.val){
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.println("准备插入的英雄编号 " + newNode.val + " 已经存在，不能插入！");
        }else{
            newNode.next = temp.next;
            temp.next = newNode;
            newNode.pre = temp;
            //如果是最后一个节点则不执行这句话
            if(newNode.next != null){
                newNode.next.pre = newNode;
            }

        }
    }

    //按照val值修改节点信息
    public void update(int val, String name, String nickname) {
        //创建新节点
        DoubleHeroNode newNode = new DoubleHeroNode(val,name,nickname);
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }

        //根据val找到需要修改的节点
        //定义一个辅助变量
        DoubleHeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while(true){
            if(temp == null) break;
            if(temp.val == newNode.val){
                flag = true;//找到了
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        }else{
            System.out.println("没有找到编号 " + newNode.val + " 的节点，不能修改");
        }
    }

    //从双向链表中删除节点
    public void delete(int val){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }

        DoubleHeroNode temp = head.next;
        boolean flag = false;//标志是否找到待删除节点
        while(true){
            if(temp == null) break;//已经到链表的最后
            if(temp.val == val){
                flag = true;//找到待删除节点
                break;
            }
            temp = temp.next;//遍历
        }
        //判断flag
        if(flag){
            temp.pre.next = temp.next;
            //如果是最后一个节点 则不执行这句话 否则会出空指针异常
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("待删除的节点不存在！");
        }
    }

}


class DoubleHeroNode {
    public int val;
    public String name;
    public String nickName;
    public DoubleHeroNode next;//指向下一个节点
    public DoubleHeroNode pre;//指向上一个节点

    //构造方法
    public DoubleHeroNode(int val, String name, String nickName) {
        this.val = val;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "val=" + val +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}