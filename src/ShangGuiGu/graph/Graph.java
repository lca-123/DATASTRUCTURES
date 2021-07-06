package ShangGuiGu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args) {
        //测试一把图的创建
        int n = 8; //节点的个数
        String[] Vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //添加顶点
        for(String vertex : Vertexs){
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);
        //显示邻接矩阵
        System.out.println("显示图");
        graph.showGraph();
        //测试dfs
        System.out.println("深度优先遍历");
        graph.dfs();
        System.out.println("广度优先遍历");
        graph.bfs();

    }

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图的邻接矩阵
    private int numOfEdges;//存储图边的数量
    private boolean[] isVisited;//记录某个节点是否被访问

    //构造方法
    public Graph(int n){
        //初始化矩阵和ArrayList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    //v1第一个点的下标 v2第二个点的下标 weight权值
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //图中常用的方法
    //返回节点的方法
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i对应的数据
    public String getValByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //得到第一个邻接节点的下标 存在就返回对应的下边 不存在就返回-1
    public int getFirstNeighobor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //对一个节点的深度优先算法 i第一次就是0
    private void dfs(int i){
        //首先访问到的节点，输出
        System.out.print(getValByIndex(i) + " ");
        //将节点设置为以访问
        isVisited[i] = true;
        int w = getFirstNeighobor(i);//查找节点的第一个邻接点
        while(w != -1){//说明有下一个邻接点
            if(!isVisited[w]){//此节点没有被访问过
                dfs(w);//递归深度优先访问
            }else{//此节点已经被访问过
                //找到i节点的下一个邻接点
                w= getNextNeighbor(i,w);
            }
        }
    }
    //对dfs重载，遍历所有的节点
    public void dfs(){
        //遍历所有的节点，进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(i);
            }
        }
        System.out.println();
        for (int i = 0; i < getNumOfVertex(); i++) {
            isVisited[i] = false;
        }
    }

    //对一个节点的广度优先算法
    private void bfs(int i){
        int u;//表示队列的头节点的下标
        int w;//邻接点下标
        //创建一个队列，记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //输出i节点的信息
        System.out.print(getValByIndex(i) +" ");
        //将i标记为以访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
            //取出队列的头节点下标
            u = queue.removeFirst();
            //得到第一个邻接点的下标w
            w = getFirstNeighobor(u);
            while(w != -1){//找到
                //判断是否访问过
                if(!isVisited[w]){//没有访问过
                    //输出节点信息
                    System.out.print(getValByIndex(w) + " ");
                    //标记为以访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }else{//已经访问过
                    //以u为前驱节点 找w后面的第一个邻接点
                    w = getNextNeighbor(u,w);//体现出广度优先
                }

            }
        }
    }
    //重载bfs，遍历所有节点
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(i);
            }
        }
        for (int i = 0; i < getNumOfVertex(); i++) {
            isVisited[i] = false;
        }
    }
}
