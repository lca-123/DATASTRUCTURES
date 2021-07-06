package ShangGuiGu.linkedList;

//使用单向环形链表解决约瑟夫问题
public class JosephuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList c = new CircleSingleLinkedList();
        c.countBoy(10,20,125);
    }
}
class CircleSingleLinkedList {
    //创建一个first节点，当前节点没有编号
    private Boy first = null;

    //添加小孩构成环形链表
    public void addBoy(int nums){
        //nums做数据校验
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }

        Boy curBoy = null;//辅助节点，帮助创建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy(){
        //判断是否为空
        if(first == null) return;
        //first不能动 因此使用辅助指针
        Boy curBoy = first;
        while (curBoy.getNext() != first) {
            System.out.println("小孩的编号为 " + curBoy.getNo());
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     *
     * @param startNo 表示从第几个小孩开始数
     * @param countNo 表示数几下
     * @param nums 表示最初小孩总数
     */
    //根据用户输入，计算小孩出圈顺序
    public void countBoy(int startNo, int countNo, int nums){
        //先对数据进行校验
        addBoy(nums);
        if(first == null || startNo < 0 || startNo > nums){
            System.out.println("参数输入有误，请从新输入");
            return;
        }
        //将first指向第一个报数的小孩的前一个并创建helper指针
        while(first.getNext().getNo() != startNo) first = first.getNext();
        Boy helper = first;

        //将first指向第一个报数的小孩
        first = first.getNext();

        //报数时，让first和helper移动countNo - 1次，然后出圈
        //此为循环，直到圈中只有一个节点
        while (helper != first) {//圈中只有一个人，退出循环

            //移动first和helper指针
            for (int i = 0; i < countNo - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的就是要出圈的小孩
            System.out.printf("小孩 %d 出圈\n",first.getNo());
            //这时将first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩为 " + first.getNo());

    }

}

//创建一个boy类作节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}