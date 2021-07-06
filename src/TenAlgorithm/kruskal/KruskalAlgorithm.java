package TenAlgorithm.kruskal;

import java.awt.event.WindowFocusListener;
import java.util.Arrays;

//运用克鲁斯卡尔算法解决最小生成树问题
public class KruskalAlgorithm {

    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;//表示两个顶点不连通

    //初始化
    public KruskalAlgorithm(char[] vertexs, int[][] maxtrix){
        int vlen = vertexs.length;//顶点个数
        //初始化顶点数组
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < maxtrix.length; i++) {
            for (int j = 0; j < maxtrix[0].length; j++) {
                this.matrix[i][j] = maxtrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(maxtrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理
    private void sort(Edata[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if(edges[j].weight > edges[j+1].weight){
                    Edata tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = tmp;
                }
            }
        }
    }

    //返回顶点对应的下标 'A' - 0
    private int getPos(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    //获取图中的边 放到Edata数组中 后边需要遍历该数组
    private Edata[] getEdges(){
        int index = 0;
        Edata[] edges = new Edata[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j] != INF){
                    edges[index++] = new Edata(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //获取下标为i的节点的终点 用于判断两个顶点的终点是否相同 即是否形成回路
    //ends数组是动态变化的逐步形成的 不是一开始就得到的
    private int getEnd(int[] ends, int i){
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    //克鲁斯卡尔算法解决最小生成树问题
    public void Kruskal(){
        int index = 0;//表示最后结果数组的索引
        //创建结果数组 保存最后的最小生成树
        //最小生成树一定有顶点个数-1条边
        Edata[] res =new Edata[vertexs.length - 1];

        int[] ends = new int[vertexs.length];//用于保存已有最小生成树中的每个顶点在最小生成树中的重点

        //获取图中所有边的集合
        Edata[] edges = getEdges();

        //首先按照边的权值大小进行排序
        sort(edges);
        //遍历edges数组 将边添加到最小生成树中时，判断是否构成回路，如果没有，就加入，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的两个顶点
            int p1 = getPos(edges[i].start);
            int p2 = getPos(edges[i].end);

            //获取p1这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends,p1);
            int n = getEnd(ends,p2);

            if(m != n){//待加入的两个边加入后没有构成回路 因此可以加入
                ends[m] = n;//设置m在已有最小生成树中的重点
                res[index++] = edges[i];//这时有一条边加入到结果数组中
            }
        }

        //统计并打印最小生成树
        System.out.println("最小生成树为=" + Arrays.toString(res));
        //System.out.println("各节点的终点：" + Arrays.toString(ends));
    }


    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF,3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0 ,2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexs, matrix);

        kruskalAlgorithm.Kruskal();
    }
}

//创建一个Edata类，对象表示一条边
class Edata {
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public Edata(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString
    @Override
    public String toString() {
        return "< " + start + ", " + end + " > weight=" + weight + "  ";
    }
}


