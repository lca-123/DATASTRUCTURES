package TenAlgorithm.kmp;

//暴力匹配字符串
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你~";
        System.out.println(ViolenceMatchMethod(str1,str2));
    }

    public static int ViolenceMatchMethod(String str1, String str2){

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int len1 = s1.length;
        int len2 = s2.length;

        int i = 0, j = 0;//i指向str1， j指向str2

        while(i < len1 && j < len2){//保证匹配时不越界
            if(s1[i] == s2[j]){
                i++;
                j++;
            }else{
                i = i -(j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        if(j == len2){
            return i - j;
        }else{
            return -1;
        }
    }
}
