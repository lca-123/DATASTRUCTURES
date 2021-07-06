package TenAlgorithm.floyd;

import java.util.Arrays;

/*
弗洛伊德算法可以处理负权重
    弗洛伊德算法求最短路径 可求出图中任意两点的最短路径
    求v1与v2间的最短路径 遍历v1与v2的所有中间节点vi v1v2最短距离为min{v12, v1vi + viv2}
    具体实现：
        中间节点：[A, B, C, D, ....]
        开始节点：[A, B, C, D, ....]
        结束节点：[A, B, C, D, ....] 遍历所有情况 按照上述思路处理
        不断更新邻接矩阵和前驱节点表
        遍历完成后的邻接矩阵就是所有图内任意两节点的最短路径表

 */
public class FloydAlgorithm {

    private static final int INF = 65535;//表示两个顶点不连通

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 5, 7, INF, INF, INF, 2},
                {5, 0, INF, 9, INF, INF, 3},
                {7, INF, 0, INF, 8, INF, INF},
                {INF, 9, INF, 0, INF, 4, INF},
                {INF, INF,8, INF, 0, 5, 4},
                {INF, INF, INF, 4, 5, 0, 6},
                {2, 3, INF, INF, 4, 6, 0},
        };

        Graph graph = new Graph(matrix, vertex);
        graph.floyd();
        graph.show();

    }
}

class Graph {
    private char[] vertex;//顶点数组
    private int[][] dis;//邻接矩阵 最后结果保存在此数组
    private int[][] pre;//保存到达目标节点的前驱节点

    //构造方法
    public Graph(int[][] matrix, char[] vertex){
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[vertex.length][vertex.length];

        //对pre数组初始化 存放前驱节点的下标
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show(){
        for (int k = 0; k < vertex.length; k++) {
//            for (int i = 0; i < vertex.length; i++) {
//                System.out.print(vertex[pre[k][i]] + " ");
//            }
//            System.out.println();
            for (int i = 0; i < vertex.length; i++) {
                System.out.print("(" + vertex[k] + "->" + vertex[i] + " " + dis[k][i] + ") ");
            }
            System.out.println();
        }
    }

    public void floyd(){
        int len = 0;
        for (int k = 0; k < vertex.length; k++) {//从中间节点遍历 k为中间节点的下标
            for (int i = 0; i < vertex.length; i++) {//遍历起始节点
                for (int j = 0; j < vertex.length; j++) {
                    len = dis[i][k] + dis[k][j];//求出从i节点出发 经过k节点 到达j节点的距离
                    if(len < dis[i][j]){//更新ij之间的距离 并 更新ij之间的中间节点
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }

        }
    }
}