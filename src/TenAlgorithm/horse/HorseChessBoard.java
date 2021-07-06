package TenAlgorithm.horse;

import java.awt.*;
import java.util.*;

public class HorseChessBoard {

    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数

    //创建一个数组 标记棋盘的各个位置是否被访问过
    private static boolean[] visited;
    //使用一个属性 标记棋盘的所有位置是否都被访问
    private static boolean finished;//成功表示true

    /**
     * 根据当前位置 计算马儿还能走哪些位置 并放在一个ArrayList中
     * @param curPoint 当前位置
     * @return 返回当前位置可以走的下一步
     */
    public static ArrayList<Point> hasNext(Point curPoint){
        ArrayList<Point> points = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        //判断马儿能否走左下位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){
            points.add(new Point(p1));
        }
        //判断马儿能否走左上位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){
            points.add(new Point(p1));
        }
        //判断马儿能否走右上位置
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){
            points.add(new Point(p1));
        }
        //判断马儿能否走右下位置
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){
            points.add(new Point(p1));
        }
        //判断马儿能否走下左位置
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){
            points.add(new Point(p1));
        }
        //判断马儿能否走下右位置
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){
            points.add(new Point(p1));
        }
        //判断马儿能否走上右位置
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){
            points.add(new Point(p1));
        }
        //判断马儿能否走上左位置
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){
            points.add(new Point(p1));
        }
        return points;
    }

    public static void sort(ArrayList<Point> points){
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return hasNext(o1).size() - hasNext(o2).size();
            }
        });
    }
    /**
     * 完成骑士周游问题的主算法
     * @param chessboard 棋盘
     * @param row 马儿在棋盘的第几行
     * @param col 第几列
     * @param step 是第几步，初始为第一步
     */
    public static void TraversalChessBoard(int[][] chessboard, int row, int col, int step) {

//        for(int[] rows : chessboard){
//            for(int step1 : rows){
//                System.out.print(step1 +"\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
        //Thread.sleep(500);
        chessboard[row][col] = step;
        visited[row * X + col] = true;//标记该位置为以访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> points = hasNext(new Point(col, row));
        sort(points);
        //遍历points所有位置
        while(!points.isEmpty()){
            Point p = points.remove(0);//取出下一个可以走的位置
            //判断该点是否已经访问过
            if(!visited[p.y * X + p.x]){
                TraversalChessBoard(chessboard, p.y, p.x,step + 1);
            }

        }

        //判断马儿是否完成任务
        if(step < X * Y && !finished){
            //1.棋盘到目前为止仍没有完
            //2.棋盘处于一个回溯状态
            chessboard[row][col] = 0;
            visited[row * X + col] = false;
        }else{
            finished = true;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始程序");
        X = 8;
        Y = 8;
        int row = 4, col = 4;
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        //测试耗时
        long d1 = System.currentTimeMillis();
        TraversalChessBoard(chessboard,row - 1,col - 1,1);
        long d2 = System.currentTimeMillis();
        System.out.println("耗时： " + (d2 - d1) + "ms");

        for(int[] rows : chessboard){
            for(int step : rows){
                System.out.print(step +"\t");
            }
            System.out.println();
        }

    }
}
