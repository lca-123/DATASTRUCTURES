package ShangGuiGu.queue;


import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arr = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("p(peek):查看队列头的数据");
            key = sc.next().charAt(0);
            switch (key){
                case 's':
                    arr.show();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int val = sc.nextInt();
                    arr.add(val);
                    break;
                case 'g':
                    try{
                        int res = arr.get();
                        System.out.println("取出的数据是" + res);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int res = arr.peek();
                        System.out.println("队列头的数据是" + res);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出！");
    }
}

