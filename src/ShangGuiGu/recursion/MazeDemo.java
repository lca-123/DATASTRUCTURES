package ShangGuiGu.recursion;


    //使用递归解决迷宫问题
public class MazeDemo {
        public static void main(String[] args) {
            //先创建一个迷宫，二维数组模拟
            //使用1表示墙
            int row = 8;
            int col = 7;
            int[][] map = new int [row][col];

            //设置墙
            for (int i = 0; i < col; i++) {
                map[0][i] = 1;
                map[row - 1][i] = 1;
            }
            for (int i = 0; i < row; i++) {
                map[i][0] = 1;
                map[i][col - 1] = 1;
            }

            //设置挡板
            map[3][1] = 1;
            map[3][2] = 1;

            //输出地图
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            //使用给小球找路
            setWay(map,1,1,6,1);

            System.out.print("\n\n");
            //输出地图
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }

        /**
         * 使用递归回溯来给小球找路
         * 当map[i][j] 为0 表示没有走过 为1表示墙 为2表示通路可以走 为3表示已经走过，但是走不通
         * 策略：下 右 上 左 如果该点走不通，再回溯
         * @param map 表示地图
         * @param startI 起点位置
         * @param startJ 起点位置
         * @param endI 结束位置
         * @param endJ 结束位置
         * @return 如果找到通路返回true， 否则返回false
         */
        public static boolean setWay(int[][] map, int startI, int startJ, int endI, int endJ){
            if(map[endI][endJ] == 2){
                return true;
            }else{
                if(map[startI][startJ] == 0){//如果当前点还没走过
                    //按照策略走 下 右 上 左
                    map[startI][startJ] = 2;//假定该点可以走
                    if(setWay(map,startI + 1,startJ,endI,endJ)){//向下走
                        return true;
                    }else if(setWay(map,startI,startJ + 1,endI,endJ)){//向右走
                        return true;
                    }else if(setWay(map,startI - 1,startJ,endI,endJ)){//向上走
                        return true;
                    }else if(setWay(map,startI,startJ - 1,endI,endJ)){//向左走
                        return true;
                    }else{
                        //说明该点走不通
                        map[startI][startJ] = 3;
                        return false;
                    }
                } else {//map[si][sj] = 1 或 2 或 3
                    return false;
                }
            }
        }
}
