package ShangGuiGu.linkedList;

import java.util.Stack;

//通过单链表管理水浒传中的英雄
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList s = new SingleLinkedList();
        s.addByOrder(1,"宋江","及时雨");
        s.addByOrder(4, "林冲","包子头");
        s.addByOrder(3, "吴用","智多星");
        s.addByOrder(2,"卢俊义","玉麒麟");
        s.list();
        System.out.println("=========");
        System.out.println(s.length());
        System.out.println(s.findLastIndex(1));

        System.out.println("=========");
        s.reversePrint();
        s.reverse();
        s.list();

        System.out.println("=========");
        s.update(4,"林冲","豹子头");
        s.list();

        System.out.println("=========");
        s.delete(1);
        s.delete(4);
        s.delete(3);
        s.delete(2);
        s.list();
    }
}

//定义SingleLinkedList 管理英雄
class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "","");


    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后一个节点
    //2.将最后一个节点的next 指向新的节点
    public void add(int val, String name, String nickname){
        //创建新节点
        HeroNode newNode = new HeroNode(val,name,nickname);
        //因为head不能动，因此需要创建一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        //将最后一个节点的next 指向新节点
        temp.next = newNode;
    }

    //显示链表信息
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //按顺序将新节点插入链表
    public void addByOrder(int val, String name, String nickname){
        //创建新节点
        HeroNode newNode = new HeroNode(val,name,nickname);
        //因为head不能动，因此需要创建一个辅助遍历temp
        HeroNode temp = head;
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
        if(flag){//编号已经存在
            System.out.println("准备插入的英雄编号 " + newNode.val + " 已经存在，不能插入！");
        }else{//编号不存在 插入链表
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    //按照val值修改节点信息
    public void update(int val, String name, String nickname) {
        //创建新节点
        HeroNode newNode = new HeroNode(val,name,nickname);
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }

        //根据val找到需要修改的节点
        //定义一个辅助变量
        HeroNode temp = head.next;
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

    //删除节点
    public void delete(int val){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }

        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点
        while(true){
            if(temp.next == null) break;//已经到链表的最后
            if(temp.next.val == val){
                flag = true;//找到待删除节点的前一个节点
                break;
            }
            temp = temp.next;//遍历
        }
        //判断flag
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("待删除的节点不存在！");
        }
    }

    //获取单链表的节点的个数
    public int length(){
        HeroNode head = this.head;
        if(head.next == null) return 0;
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表的倒数第k个节点【新浪面试题】
    //思路：遍历length - k个节点
    public HeroNode findLastIndex(int index){
        HeroNode head = this.head;
        //判断链表是否为空
        if(head.next == null) return null;
        //第一次遍历得到链表长度
        int length = this.length();
        //先做index的校验
        if(index <= 0 || index > length) return null;
        //定义辅助变量
        HeroNode cur = head.next;
        //for循环找到倒数第index个
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //单链表反转【腾讯面试题】
    public void reverse(){
        HeroNode head = this.head;
        //当前链表为空或只有一个节点，无需反转
        if(head.next == null || head.next.next == null) return;

        //定义辅助指针，帮助我们遍历链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向cur的下一个节点!!
        HeroNode newhead = new HeroNode(0,"","");

        //遍历链表并完成反转
        //每遍历一个节点就将其取出放在newhead的最前端
        while(cur != null){
            next = cur.next;
            cur.next = newhead.next;
            newhead.next = cur;
            cur = next;
        }
        head.next = newhead.next;
    }

    //逆序打印单链表【百度面试题】
    //使用栈结构Stack
    public void reversePrint(){
        HeroNode head = this.head;
        //判断空链表
        if(head.next == null) return;
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}

class HeroNode {
    public int val;
    public String name;
    public String nickName;
    public HeroNode next;
    //构造方法
    public HeroNode(int val, String name, String nickName) {
        this.val = val;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "val=" + val +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}