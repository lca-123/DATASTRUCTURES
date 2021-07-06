package ShangGuiGu.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack a = new ArrayStack(4);
        String key = "";
        boolean loop = false;
        Scanner sc = new Scanner(System.in);

        while(!loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：入栈");
            System.out.println("pop：出栈");
            System.out.println("请输入选择");
            key = sc.next();
            switch (key){
                case "show":
                    a.list();
                    break;
                case "exit":
                    sc.close();
                    loop = true;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    a.push(sc.nextInt());
                    break;
                case "pop":
                    System.out.println("出栈元素为 " + a.pop());
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造方法
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //判断栈是否已满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈push
    public void push(int val){
        //先判断是否已满
        if(isFull()){
            System.out.println("栈已满，无法入栈");
            return;
        }
        stack[++top] = val;
    }

    //出栈pop
    public int pop(){
        //先判断栈是否为空
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法出栈");
        }
        return stack[top--];
    }

    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("栈为空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}