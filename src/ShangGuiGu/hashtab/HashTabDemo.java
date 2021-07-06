package ShangGuiGu.hashtab;

import java.util.Scanner;

//使用哈希表管理员工信息
    //哈希表 数组+链表 数组+二叉树
public class HashTabDemo {
        public static void main(String[] args) {
            //创建哈希表
            HashTab hashtab = new HashTab(7);

            //写一个简单菜单来测试
            String key = " ";
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("add：添加员工");
                System.out.println("list:显示员工");
                System.out.println("find:查找员工");
                System.out.println("exit：退出系统");

                key = sc.next();
                switch (key){
                    case "add":
                        System.out.println("输入id");
                        int id = sc.nextInt();
                        System.out.println("输入姓名");
                        String name = sc.next();
                        Emp emp = new Emp(id, name);
                        hashtab.add(emp);
                        break;
                    case "list":
                        hashtab.list();
                        break;
                    case "exit":
                        sc.close();
                        System.exit(0);
                        break;
                    case "find":
                        System.out.println("请输入要查找的id");
                        id = sc.nextInt();
                        hashtab.find(id);
                        break;
                    default:
                        break;
                }
            }
        }
}


//创建哈希表 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    //构造器
    public HashTab(int size){
        //初始化 empLinkedListArray
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //不要忘了分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //编写一个散列函数 使用简单的取模法
    public int hashcode(int id){
        return id % size;
    }

    //添加员工
    public void add(Emp emp){
        //根据员工id，得到该员工应该添加到哪个链表
        int empLinkedListNo = hashcode(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入id查找员工
    public void find(int id){
        //根据员工id，得到该员工应该添加到哪个链表
        int empLinkedListNo = hashcode(id);

        Emp emp = empLinkedListArray[empLinkedListNo].findEmp(id);
        if(emp != null){
            System.out.print("在第 " + empLinkedListNo + " 条链表中找到该员工");
            System.out.println(emp);
        }else{
            System.out.println("没有找到该雇员");
        }
    }
}




//员工类
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "    => Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//数组中的链表
class EmpLinkedList {
    //头指针，指向第一个Emp
    private Emp head;//默认null

    //添加员工到链表
    //假定 当添加员工时，id是自增的 即id的分配总是从小到大
    //因此直接加入到链表最后一个
    public void add(Emp emp){
        //如果是第一个员工
        if(head == null){
            head = emp;
            return;
        }else{
            //不是第一个员工 则用辅助指针到最后
            Emp curEmp = head;
            while(curEmp.next != null){
                curEmp = curEmp.next;
            }
            curEmp.next = emp;
        }
    }

    //遍历链表的员工信息
    public void list(int no){
        if(head == null){
            System.out.println("第 " + no +" 条链表为空");
            return;
        }
        System.out.print("第 " + no +" 条链表信息为 ");
        Emp curEmp = head;
        while(curEmp != null){
            System.out.print(curEmp);
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找员工
    //如果查找到，就返回Emp，如果没有找到，就返回null
    public Emp findEmp(int id){
        //判断链表是否为空
        if(head == null){
            return null;
        }

        Emp curEmp = head;
        while (curEmp.id != id) {
            curEmp = curEmp.next;
            if (curEmp == null) {
                break;
            }
        }
        return curEmp;
    }
}