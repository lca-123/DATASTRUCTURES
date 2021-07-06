package TenAlgorithm.dijkstra;

import java.util.Arrays;

/*
注意：迪杰斯特拉算法不能处理负权重
    迪杰斯特拉算法 计算从出发顶点到其他顶点的最短路径
    1.从出发顶点开始 遍历周围顶点并存储出发顶点到周围顶点的距离 
      其中距离最短的一个顶点的最短路径可以确定 并将此顶点设置为以访问
    2.从此顶点遍历周围顶点 如果出现从此顶点到某一节点的距离小于现在此节点到出发顶点的距离 则更新这一节点的距离
    3.遍历所有没有确定最短路径的顶点距离 选取最少的重复1.步骤 重复循环的次数为节点个数减一
 */
public class DijkstraAlgorithm {

    private static final int INF = 65535;//表示两个顶点不连通

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {INF, 5, 7, INF, INF, INF, 2},
                {5, INF, INF, 9, INF, INF, 3},
                {7, INF, INF, INF, 8, INF, INF},
                {INF, 9, INF, INF, INF, 4, INF},
                {INF, INF,8, INF, INF, 5, 4},
                {INF, INF, INF, 4, 5, INF, 6},
                {2, 3, INF, INF, 4, 6, INF},
        };

        Graph graph = new Graph(vertex, matrix);
        graph.dijkstra(6);
        graph.showDijkstra();
    }
}

//记录已经访问节点集合 会动态更新
class VisitedVertex {
    //记录节点是否被访问过 1表示访问过， 0表示未访问
    public int[] already_arr;
    //每一个节点的前驱节点下标
    public int[] pre_visited;
    //记录出发节点到其他所有节点的距离
    public int[] dis;

    private static final int INF = 65535;//表示两个顶点不连通

    //length表示顶点个数 index表示出发顶点
    public VisitedVertex(int length, int index){
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        //初始化dis数组
        Arrays.fill(dis,INF);
        this.dis[index] = 0;

        //设置出发顶点被访问过
        this.already_arr[index] = 1;
    }

    //判断index顶点是否被访问过
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    //更新出发顶点到index这个节点的距离
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    //更新顶点pre的前驱节点为indx
    public void updatePre(int pre, int index){
        pre_visited[pre] = index;
    }

    //继续选择并返回新的访问节点
    public int updateArr(){
        int min = INF, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index被访问过
        already_arr[index] = 1;
        return index;
    }
    //返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }

    //显示最终结果
    public void showRes(char[] vertex){
        System.out.println("==============");
        for(int i : already_arr){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==============");
        for(int i : pre_visited){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==============");
        for(int i : dis){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("==============");
        int count = 0;
        for(int i : dis){
            if(i != 65535){
                System.out.print(vertex[count] + "(" +i +") ");
            }else{
                System.out.print("N ");
            }
            count++;
        }
    }
}



class Graph {
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//表示已经访问的节点的集合

    public Graph(char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }
    //显示邻接矩阵
    public void show(){
        for(int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //更新index下标节点到周围节点的距离和周围节点的前驱节点
    private void update(int index){
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            //len表示出发顶点到index顶点的距离 + 从index顶点到j顶点的距离
            len = vv.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过，且距离小于出发点到j的距离，则需要更新
            if(!vv.in(j) && len < vv.getDis(j)){
                vv.updatePre(j,index);//更新j的前驱节点为index
                vv.updateDis(j,len);//更新出发顶点到j的距离
            }
        }
    }

    //迪杰斯特拉算法实现 index表示出发顶点对应的下标
    public void dijkstra(int index){
        this.vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点

        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问节点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    public void showDijkstra(){
        vv.showRes(this.vertex);
    }
}