package TenAlgorithm.kmp;

import java.util.Arrays;

public class KMPDemo {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //System.out.println(Arrays.toString(kmpNext(str2)));
        System.out.println(KMP(str1,str2));
    }
    public static int KMP(String str1, String str2){
        int[] next = kmpNext(str2);//获取子串的部分匹配表
        System.out.println(Arrays.toString(next));
        //遍历str1
        for (int i = 0, j = 0; i < str1.length(); i++) {

            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){//找到了
                return i - j + 1;
            }
        }
        return -1;

    }

    //获取字串的部分匹配表
    //自己匹配自己
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1 其部分匹配值为0
        for (int i = 1, j = 0;i < dest.length(); i++) {

            while (j > 0 && dest.charAt(i) != dest.charAt(j)){//kmp算法的核心
                j = next[j - 1];//如果当前不匹配 则返回到前一个字符的能够匹配的最初位置 即上一个字符能够匹配的前缀子串
            }
            if(dest.charAt(i) == dest.charAt(j)){//这个条件满足时 部分匹配值加一
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
