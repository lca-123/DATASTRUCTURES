package ShangGuiGu.sparsearray;


import java.io.*;
import java.util.*;


/*  实例：编写五子棋程序，实现存盘和读档的功能（棋盘中落子不多）
    稀疏数组：当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
    稀疏数组处理方法：1.记录数组一共有几行几列，有多少个不同的值
                     2.把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
    二维数组 转 稀疏数组的思路 1.遍历原始的二维数组，得到有效数据的个数sum
                              2.根据sum就可以创建稀疏数组 sparseArr int[sum + 1][3]
                              3.将二维数组的有效数据存入到稀疏数组
    稀疏数组 转 二维数组的思路 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
                              2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
*/
public class SparseArray {
    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();

        int row = 500;
        int col = 500;

        //创建棋盘->二维数组
        int[][] chessArr1 = new int[row][col];
        chessArr1[1][2] = 1;
        chessArr1[4][5] = 2;

        //将二维数组转为稀疏数组
        int[][] spaArr = ArrayToSparseArray(chessArr1);

        //将稀疏数组存入文件
        WriteSparseArray(spaArr, "稀疏数组输出.txt");

        //从文件中读取稀疏数组
        spaArr = ReadSparseArray("稀疏数组输出.txt");

        //将稀疏数组转为二维数组
        int[][] chessArr2 = SparseArrayToArray(spaArr);

        //输出最终数组
        for (int i = 0; i < chessArr2.length; i++) {
            for (int j = 0; j < chessArr2[0].length; j++) {
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }

        long t2 = System.currentTimeMillis();
        //耗时时长
        System.out.println(t2 - t1);
    }

    //二维数组转稀疏数组
    public static int[][] ArrayToSparseArray(int[][] array){
        int row = array.length;
        int col = array[0].length;
        int sum = 0;
        //遍历二维数组得到非零数据
        for (int[] ints : array) {
            for (int j = 0; j < col; j++) {
                if (ints[j] != 0) sum++;
            }
        }
        //创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = row;
        sparseArr[0][1] = col;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零值存入稀疏数组
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(array[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }
        return sparseArr;
    }

    //稀疏数组转二维数组
    public static int[][] SparseArrayToArray(int[][] SparseArray){
        //创建二维数组
        int[][] array = new int[SparseArray[0][0]][SparseArray[0][1]];
        //将非零值存入二维数组
        for (int i = 1; i < SparseArray.length; i++) {
            array[SparseArray[i][0]][SparseArray[i][1]] = SparseArray[i][2];
        }
        return array;
    }

    //将稀疏数组写入文件
    public static void WriteSparseArray(int[][] SparseArray, String name) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(name));
        for (int[] row : SparseArray) {
            fw.write(row[0] + " " + row[1] + " " + row[2]);
            fw.newLine();
        }

        fw.close();
    }

    //从文件中读取稀疏数组
    public static int[][] ReadSparseArray(String name) throws IOException {
        BufferedReader fr = new BufferedReader(new FileReader(name));
        String str;
        ArrayList<String> List = new ArrayList<>();
        while ((str = fr.readLine()) != null) {
            List.add(str);
        }
        fr.close();
        int[][] spaArr = new int[List.size()][3];
        int count = 0;
        for(String s:List){
            String[] ss = s.split(" ");
            spaArr[count][0] = Integer.parseInt(ss[0]);
            spaArr[count][1] = Integer.parseInt(ss[1]);
            spaArr[count][2] = Integer.parseInt(ss[2]);
            count++;
        }
        return spaArr;
    }


}
