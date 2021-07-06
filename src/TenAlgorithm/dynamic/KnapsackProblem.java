package TenAlgorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3, 5, 6};//存储物品的重量
        int[] v = {1500, 30000, 32000, 10000, 20000};//存储物品的价值
        int m = 10;//背包的容量i
        int n = v.length;//物品的个数

        //创建二维数组，表示每种情况下的结果
        //maxV[i][j]表示前i个物品中能够装入容量为j的背包的最大价值
        int[][] maxV = new int[n + 1][m + 1];
        //记录商品存放到背包的情况
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列
        for (int i = 0; i < maxV.length; i++) {
            maxV[i][0] = 0;//将i个物品装入容量为0的背包中 最大价值为0
        }
        for (int i = 0; i < maxV[0].length; i++) {
            maxV[0][i] = 0;//将0个物品放入容量为i的背包中 最大价值为0
        }

        //开始动态规划
        for (int i = 1; i < maxV.length; i++) {
            for (int j = 1; j < maxV[0].length; j++) {
                if(w[i - 1] > j){
                    maxV[i][j] = maxV[i -1][j];
                }else{
                    //如果一种物品只能存放一个 公式如下
                    //maxV[i][j] = Math.max(maxV[i - 1][j], v[i - 1] + maxV[i-1][j - w[i - 1]]);
                    //如果一种物品可以存放多次 公式如下
                    //maxV[i][j] = Math.max(maxV[i - 1][j], v[i - 1] + maxV[i][j - w[i - 1]]);
                    //为了记录商品存放到背包的情况(可以存放多次)
                    if(maxV[i - 1][j] < v[i - 1] + maxV[i][j - w[i - 1]]){
                        maxV[i][j] = v[i - 1] + maxV[i][j - w[i - 1]];
                        //把当前情况记录当path
                        path[i][j] = 1 + path[i][j - w[i - 1]];
                    }else{
                        maxV[i][j] = maxV[i - 1][j];
                    }
//                    //为了记录商品存放到背包的情况(只能存放一次)
//                    if(maxV[i - 1][j] < v[i - 1] + maxV[i - 1][j - w[i - 1]]){
//                        maxV[i][j] = v[i - 1] + maxV[i - 1][j - w[i - 1]];
//                        //把当前情况记录当path
//                        path[i][j] = 1 + path[i][j - w[i - 1]];
//                    }else{
//                        maxV[i][j] = maxV[i - 1][j];
//                    }
                }
            }
        }

        //输出maxV
//        for (int[] ints : maxV) {
//            for (int j = 0; j < maxV[0].length; j++) {
//                System.out.print(ints[j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(maxV[n][m]);

//        //输出path
//        for (int[] ints : path) {
//            for (int j = 0; j < maxV[0].length; j++) {
//                System.out.print(ints[j] + " ");
//            }
//            System.out.println();
//        }

        //输出背包放置情况
        int i = maxV.length - 1;
        int j = maxV[0].length - 1;
        while(i > 0 && j > 0){
            if(path[i][j] > 0){
                System.out.printf("%d个第%d个商品放入到背包\n", path[i][j], i);
                j -= path[i][j]*w[i -1];
            }
            i--;
        }
    }
}
