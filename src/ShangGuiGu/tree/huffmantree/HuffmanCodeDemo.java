package ShangGuiGu.tree.huffmantree;

import java.io.*;
import java.util.*;
/*
定长编码：将需要传输的数据的每一个字符转为Ascii码再转为二进制传输
	传输数据很长、

变长编码：将需要传输的数据按照字符重复次数排序 并给每一个重复字符赋值
    原则按照重复次数最多的字符赋0 其次赋1 再其次赋10（二进制） 再按照赋值将原数据转为二进制编码
	可能会产生歧义

赫夫曼编码(无损压缩)：统计字符串中各个字符出现的次数 将次数作为权值构建赫夫曼树
    根据赫夫曼树 给各个字符规定编码 向左路径为0 向右路径为1
	好处：没有一个编码是另一个编码的前缀 不会误读 编码长度也减小了

用赫夫曼编码压缩视频，ppt等文件效率不高
赫夫曼编码是按照字节处理的，因此可以处理任何文件
如果文件中重复的内容不多，压缩效果也不明显
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) {
        //测试压缩字符串
//        String str= "I LOVE NJU";
//        byte[] huffmanCodeBytes = huffmanZip(str);
//        String s = decodeString(huffmanCodes,huffmanCodeBytes);
//        System.out.println(s);

//        //测试压缩文件
//        String srcFile = "D:\\Adobe\\ps\\刻章.psd";
//        String dstFile = "D:\\ab编程\\ideaWorkspace\\DataStructures\\压缩文件.hzip";
//
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩成功");

//        //测试解压文件
//        String zipFile = "D:\\ab编程\\ideaWorkspace\\DataStructures\\压缩文件.hzip";
//        String dstFile = "D:\\ab编程\\ideaWorkspace\\DataStructures\\解压文件.psd";
//        unZipFile(zipFile,dstFile);
//        System.out.println("解压成功");
    }

    //通过byte数组创建节点集合
    private static List<HuffmanNode> getNodes(byte[] bytes){

        //创建ArrayList
        ArrayList<HuffmanNode> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数 map[K, V]
        Map<Byte, Integer> counts = new HashMap<>();
        for(byte b : bytes){
            Integer count = counts.get(b);
            if(count == null){
                counts.put(b, 1);
            }else{
                counts.put(b, count + 1);
            }
        }

        //把每个键值对转成一个HuffmanNode对象并加入nodes
        for(Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new HuffmanNode(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }

    //构建赫夫曼树 具体实现见前代码
    private static HuffmanNode createHuffman(List<HuffmanNode> nodes){
        while(nodes.size() > 1){
            Collections.sort(nodes);

            HuffmanNode left = nodes.get(0);
            HuffmanNode right = nodes.get(1);
            //非叶子节点没有data 只有权值
            HuffmanNode parent = new HuffmanNode(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }





    //生成赫夫曼树对应的赫夫曼编码表
    //将赫夫曼编码表存放在map<Byte, String>中 形如[32: "01", 97:"100"]
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //在生成赫夫曼编码表时要拼接路径 创建一个StringBuilder存储叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     *  将传入的node节点的所有叶子节点的赫夫曼编码得到，并存放如HuffmanCodes集合中
     * @param node 传入节点
     * @param code 路径：左子节点为0 右子节点为1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(HuffmanNode node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if(node != null){//如果node == null 不处理
            //判断当前节点是否为叶子节点
            if(node.data == null){//非叶子节点
                //递归处理
                //向左
                getCodes(node.left,"0",stringBuilder1);
                //向右
                getCodes(node.right,"1",stringBuilder1);
            }else{//叶子节点
                //表示找到了叶子节点最后
                huffmanCodes.put(node.data,stringBuilder1.toString());
            }

        }
    }
    //为了调用方便 重载getCodes
    private static Map<Byte, String> getCodes(HuffmanNode root){
        if(root == null){
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",stringBuilder);
        //处理右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }


    //在解压时bytes数组的最后一个元素的位数不确定 因此在此存储这个位数
    public static int lastdiv;
    /**
     *将字符串对应的byte[]数组，通过赫夫曼编码表，返回压缩后的byte[]
     * @param bytes 原始字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码表
     * @return 返回生成的字符串对应的byte[]，即8位对应一个byte(二进制转十进制)
     * e.g.  1010100010111111 => 10101000 10111111 => -88 -193
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //先利用赫夫曼编码表将bytes数组 转成 赫夫曼编码后的字符串(01串)
        StringBuilder s = new StringBuilder();
        //遍历bytes数组
        for(byte b : bytes){
            s.append(huffmanCodes.get(b));
        }
//        System.out.println(s.toString());
        //将对应的字符串转为bytes[]
        //统计返回的s的长度
        int len;
        if(s.length()%8 == 0){
            len = s.length()/8;
        }else{
            len = s.length()/8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录huffmanCodeBytes中的下标
        for (int i = 0; i < s.length(); i += 8) {//每8位对应一个byte 因此步长为8
            String strByte;
            if(i + 8 > s.length()){
             strByte = s.substring(i, s.length());
             lastdiv = s.length() - i;
            }else {
                //接受8位字符串
                strByte = s.substring(i, i + 8);
                //将字符串转成byte存入huffmanCodeBytes
            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        lastdiv = Math.min(lastdiv, 8);
//        System.out.println(lastdiv);
        return huffmanCodeBytes;
    }


    //数据压缩
    //将一个字符串转为赫夫曼编码的bytes数组(以上代码方法的整合封装)
    //完整的赫夫曼编码压缩方法
    private static byte[] huffmanZip(String str){
        //将字符串转为原始的bytes数组
        byte[] strbytes = str.getBytes();
        return huffmanZip(strbytes);
    }
    private static byte[] huffmanZip(byte[] strbytes){

        //通过byte数组创建节点集合
        List<HuffmanNode> nodes = getNodes(strbytes);

        //构建赫夫曼树
        HuffmanNode root = createHuffman(nodes);

        //生成哈夫曼编码表
        huffmanCodes = getCodes(root);

        //根据赫夫曼编码表压缩bytes数组并返回
        return zip(strbytes,huffmanCodes);
    }



    //数据解压
    //先将bytes数组转成赫夫曼编码对应的二进制字符串
    //然后对照赫夫曼编码重新转成字符串对应的bytes数组


    /**
     * 将一个byte转成一个二进制字符串
     * @param flag 标志是否需要补高位 true补 (如果是二进制字符串的最后几位 不需要补高位)
     * @param b 传入的一个byte值
     * @return 该byte对应的二进制字符串，(按照补码返回)
     */
    private static String byteToBitString(Boolean flag, byte b){
        //使用一个变量保存b
        int temp = b;
        //如果是正数 需要补高位
        temp |= 256;//按位与 256 1 0000 0000
        String str = Integer.toBinaryString(temp);
        if(flag) {
            return str.substring(str.length() - 8);//如果是负数需要截取一部分
        }else {
            if(lastdiv == 0){
                return str.substring(str.length() - 8);
            }
            return str.substring(str.length() - lastdiv);
        }
    }



    /**
     * 编写一个方法，完成对数据的解码
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来字符串对应的数组
     */
    private static String decodeString(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            stringBuilder.append(byteToBitString(i != huffmanBytes.length - 1, huffmanBytes[i]));
        }
//        System.out.println(stringBuilder.toString());

        //把字符串按照制定的赫夫曼编码解码
        //把赫夫曼编码进行调换，反向查找
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {//i可以理解为一个索引，扫描stringbuilder
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while(flag){
                //取出一个'1'或'0'
                String key = stringBuilder.substring(i,i + count);//i不动让count增长，直到匹配到一个字符
                b = map.get(key);
                if(b == null){//说明没有匹配到
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i+= count;
        }

        //把list中的数据放入byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }

        return new String(b);
    }
    private static byte[] decodeByte(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            stringBuilder.append(byteToBitString(i != huffmanBytes.length - 1, huffmanBytes[i]));
        }
//        System.out.println(stringBuilder.toString());

        //把字符串按照制定的赫夫曼编码解码
        //把赫夫曼编码进行调换，反向查找
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {//i可以理解为一个索引，扫描stringbuilder
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while(flag){
                //取出一个'1'或'0'
                String key = stringBuilder.substring(i,i + count);//i不动让count增长，直到匹配到一个字符
                b = map.get(key);
                if(b == null){//说明没有匹配到
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i+= count;
        }

        //把list中的数据放入byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }

        return b;
    }


    /**
     * 编写一个方法，将一个文件压缩
     * @param srcFile 传入的压缩文件路径
     * @param dstFile 压缩到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件输入流
        FileInputStream is = null;
        try{

            is = new FileInputStream(srcFile);
            //创建一个跟源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            is.read(b);

            //获取到文件对应的赫夫曼编码
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件输出流 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutPutStream
            oos = new ObjectOutputStream(os);

            //以对象流将赫夫曼编码写入文件
            oos.writeObject(huffmanBytes);

            //以对象流的方式写入赫夫曼编码，是为了恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
            oos.writeObject(lastdiv);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                is.close();
                os.close();
                oos.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * 将文件解压
     * @param zipFile 准备解压的文件
     * @param dstFile 解压文件存储的路径
     */
    public static void unZipFile(String zipFile, String dstFile){
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件输出流
        OutputStream os = null;
        try{
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建文件对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            huffmanCodes = (Map<Byte, String>)ois.readObject();
            //读取lastdiv
            lastdiv = (int)ois.readObject();


            //解码
            byte[] bytes = decodeByte(huffmanCodes,huffmanBytes);
            //将byte写入目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
           try{
               is.close();
               ois.close();
               os.close();
           }catch (Exception e2){
               e2.getMessage();
           }
        }
    }



}

//创建Node，存储数据和权值
class HuffmanNode implements Comparable<HuffmanNode>{
    Byte data;//存放数据本身 字符对应的ascii码
    int weight;//权值，字符出现的次数
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" + "data=" + data + ", weight=" + weight + '}';
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight - o.weight;
    }

    public void prelist(){
        System.out.println(this);
        if(this.left != null) this.left.prelist();
        if(this.right != null) this.right.prelist();
    }
}
