package TenAlgorithm.prim;


import java.util.Arrays;

//运用普利姆算法解决修路问题 即一个图的最小生成树问题
//
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        //创建图对象
        MGraph mGraph = new MGraph(verxs);
        //创建最小生成树对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
//        //输出一下
//        minTree.showGraph(mGraph);
        minTree.prim(mGraph,0);

    }
}

// 创建最小生成树
class MinTree {
    //创建图的邻接矩阵
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight){
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim算法 得到最小生成树
     * @param graph 图
     * @param v 表示从图的哪一个节点开始生成
     */
    public void prim(MGraph graph, int v){
        int[] visited = new int[graph.verxs];//标记节点是否被访问过 默认为0 表示没有访问过
        //把当前节点标记为以访问
        visited[v] = 1;
        //h1 h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//初始化为一个比较大的值 遍历过程中会被替换
        for (int k = 1; k < graph.verxs; k++) {
            //确定每一次生成的子图 和哪个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) {//i表示已经被访问过的节点
                for (int j = 0; j < graph.verxs; j++) {//j表示没有被访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        //寻找已经访问过的节点和未访问过的节点间权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //找到了最小边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            //将当前节点设置为以访问
            visited[h2] = 1;
            //重置minWeight
            minWeight = 10000;

        }

    }
}
// 创建图
class MGraph {

    int verxs;//保存图的节点个数
    char[] data;//存放节点信息
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int vertxs){
        this.verxs = vertxs;
        data = new char[vertxs];
        weight = new int[vertxs][vertxs];
    }
}