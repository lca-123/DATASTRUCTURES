package ShangGuiGu.queue;

//用数组模拟环形队列
public class CircleArrayQueue {
    private int maxSize;//表示数组的最大容量, 但是队列的实际容量是maxSize - 1
    private int front;//队列头:指向队列的第一个元素 初始值为0
    private int rear;//队列尾：指向队列的最后一个元素的后一个位置，初始值为0 并且留一个空的空间
    private int[] arr;//该数组用于存放数据，模拟队列

    //构造方法
    public CircleArrayQueue(int arrMaxSize){
        this.maxSize = arrMaxSize;
        this.arr = new int[arrMaxSize];
        this.front = 0;
        this.rear = 0;
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void add(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据！！");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，考虑取模
        rear = (rear + 1)%maxSize;
    }

    //获取队列的数据，出队列
    public int get(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据！");
        }
        int val = arr[front];
        front = (front + 1)%maxSize;
        return val;
    }

    //显示队列的所有数据
    public void show(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，没有数据！");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，不是取出数据
    public int peek(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据！");
        }
        return arr[front];
    }
}
