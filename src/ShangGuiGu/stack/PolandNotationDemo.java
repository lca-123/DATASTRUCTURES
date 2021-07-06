package ShangGuiGu.stack;


import java.util.*;


//使用逆波兰表达式完成计算
public class PolandNotationDemo {

    public static void main(String[] args) {
        //先给出一个逆波兰表达式
        //(3+4)*5-6  ——> 3 4 + 5 * 6 -
        //从左至右遍历表达式 遍历到数字则直接入栈 若遍历到符号则从栈中弹出两个数计算后再入栈
        //为了说明方便，逆波兰表达式的数字和符号使用空格隔开
//            System.out.println(PolandNotationCal("3 4 + 5 * 6 -"));

        /*  迷你计算器功能：
        *   + 加 - 减 * 乘 / 除 ^ 指数
        *   过滤空格，回车，tab
        *   pi 圆周率 e  自然对数底
        *   !  阶乘 |  开跟 
        *   支持负数 e.g.3+(-1)*2 (注意加括号)
        *   支持三角函数 sin cos tan asin acos atan (注意加括号)
        *   支持对数运算 ln log  (注意加括号)
         */

        String expression = "(-1.1)+(pi - e*ln(log(sin(cos(tan(asin(acos(atan(2)))))))))*3!+(|4)/3";
        //System.out.println(toInfixExpressionList(expression));
        System.out.println(expression + " = " + PolandCalculator(expression));

    }

    //中缀表达式转集合
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//遍历字符串
        StringBuilder str;//用于对多位数的拼接
        char c = ' ';//每遍历到一个字符，就存入c
        do {
            //如果c是非数字，就需要加入到ls中
            c = s.charAt(i);
            //处理冗余符号
            if(c == ' ' || c == '\n' || c == '\t'){
                i++;
            } else //处理操作符 和 字符数字pi e
                if((c > '9' || c  < '0') && c != '.'){
                if(c=='p'&&s.charAt(i+1)=='i'){//处理圆周率pi
                    ls.add("pi");
                    i+=2;
                }else {
//               !0 阶乘 (规定在写阶乘符号后加一个0，方便运算
//               |0 开跟 (规定在写开跟符号前加一个0，方便运算
                    if(c=='!'){//处理阶乘
                        ls.add("" + c);
                        ls.add("0");
                        i++;
                    }else if (c=='|'){//处理开根号
                        ls.add("0");
                        ls.add(""+c);
                        i++;
                    }else if(c=='-'){
                        //处理负数
                        if(i==0||s.charAt(i-1)=='('){
                            str = new StringBuilder("-");
                            i++;
                            //如果是是数，需要考虑多位数
                            while(i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')){
                                str.append(s.charAt(i));//拼接
                                i++;
                            }
                            ls.add(str.toString());
                        }else{
                            //排除普通减号情况
                            ls.add(""+c);
                            i++;
                        }
                    }else
                        if(c=='s'||c=='c'||c=='t'||c=='a'){
                            //处理三角函数
                        if(c=='s'){
                            ls.add("0");
                            ls.add("sin");
                            i+=3;
                        }
                        if(c=='c'){
                            ls.add("0");
                            ls.add("cos");
                            i+=3;
                        }
                        if(c=='t'){
                            ls.add("0");
                            ls.add("tan");
                            i+=3;
                        }
                        if(c=='a'){
                            char cc= s.charAt(i+1);
                            if(cc=='s'){
                                ls.add("0");
                                ls.add("asin");
                                i+=4;
                            }
                            if(cc=='c'){
                                ls.add("0");
                                ls.add("acos");
                                i+=4;
                            }
                            if(cc=='t'){
                                ls.add("0");
                                ls.add("atan");
                                i+=4;
                            }
                        }
                    } else if(c=='l'){
                            //处理取对数操作符ln log
                            if(s.charAt(i+1)=='n') {
                                ls.add("0");
                                ls.add("ln");
                                i += 2;
                            }else if(s.charAt(i+1)=='o'){
                                ls.add("0");
                                ls.add("log");
                                i+=3;
                            }
                        }else{
                            //处理普通运算符 + - * / ^ 和自然对数e
                        ls.add("" + c);
                        i++;
                    }
                }
            }else{
                str = new StringBuilder("");
                //如果是是数，需要考虑多位数
                while(i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')){
                    str.append(s.charAt(i));//拼接
                    i++;
                }
                ls.add(str.toString());
            }
        }while(i < s.length());
        return ls;
    }

    //逆波兰表达式转集合
    public static List<String> getList(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    //逆波兰表达式计算
    public static double PolandNotationCal(List<String> ls){
        //创建一个栈，且只需要一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //使用正则表达式来取出数
            if(item.equals("pi")||item.equals("e")||item.equals("0")||item.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")||item.matches("(-[1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")){//匹配的是多位数
                stack.push(item);
            }else{
                //pop出两个数并运算再入栈
                double num2 = outnum(stack.pop());
                double num1 = outnum(stack.pop());
                double res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    case "^":
                        res = Math.pow(num1,num2);
                        break;
                    case "!":
                        res = FAC(num1);
                        break;
                    case "|":
                        res = SQRT(num2);
                        break;
                    case "sin":
                        res = Math.sin(num2);
                        break;
                    case "cos":
                        res = Math.cos(num2);
                        break;
                    case "tan":
                        res = Math.tan(num2);
                        break;
                    case "asin":
                        res = Math.asin(num2);
                        break;
                    case "acos":
                        res = Math.acos(num2);
                        break;
                    case "atan":
                        res = Math.atan(num2);
                        break;
                    case "ln":
                        res = Math.log(num2);
                        break;
                    case "log":
                        res = Math.log10(num2);
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        //最后留在stack中的就是结果
        return outnum(stack.pop());
    }

    //中缀表达式转后缀表达式
    /*
    思路：
    1.初始化两个栈：运算符栈s1 和 存储中间结果的栈s2
    2.从左至右扫描中缀表达式
    3.遇到操作数时，直接压入s2
    4.遇到运算符时，比较其与栈顶运算符的优先级：
        4.1如果s1为空，或栈顶运算符为'(',则直接入栈
        4.2否则，若优先级比栈顶运算符高，则也直接入栈
        4.3否则，将s1栈顶运算符弹出并压入s2中，再次转到 4. 与s1中新栈顶运算符进行比较
    5.遇到括号时
        5.1如果是'(',则直接入栈
        5.2如果是')',则依次弹出s1栈顶运算符直到遇到'('，并将这对括号抛弃
    6.重复2-5步骤直到扫描完毕
    7.将s1中剩余运算符依次弹出并压入s2
    8.依次弹出s2中元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义栈 因为s2在整个过程中没有pop操作且最后需要逆序输出 比较麻烦 因此用ArrayList代替它
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for(String item:ls){
            //如果是一个数，则加入s2
            //正则表达式 整数\\d+
            //小数 如下 为什么？ 不知道...
            if(item.equals("pi")||item.equals("e")||item.equals("0")||item.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")||item.matches("(-[1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是')',则依次弹出s1栈顶运算符直到遇到'('，并将这对括号抛弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将此小括号'('弹出
            }else {
        //当item的优先级小于等于栈顶的运算符 将s1栈顶运算符弹出并压入s2中，再次转到 4. 与s1中新栈顶运算符进行比较
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入s1
                s1.push(item);
            }
        }
        //将s1中剩余运算符依次弹出并压入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //完整的逆波兰计算器
    public static double PolandCalculator(String expression){
        return PolandNotationCal(parseSuffixExpressionList(toInfixExpressionList(expression)));
    }

    //将字符串转为double数字 包括pi e
    public static double outnum(String item){
        if(item.equals("0")||item.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")||item.matches("(-[1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")){
            return Double.parseDouble(item);
        }else if(item.equals("pi")){
            return Math.PI;
        }else if(item.equals("e")){
            return Math.E;
        }
        return 0;
    }
    //阶乘
    public static double FAC(double num){
        if(num<0){
            System.out.println("负数不能阶乘");
            System.exit(0);
        }
        if(Math.abs(num-(int)num)<=1e-8){
            double res = 1;
            for (int i = 0; i < num; i++) {
                res *= i+1;
            }
            return res;
        }else{
            System.out.println("小数不能阶乘");
            System.exit(0);
        }
        return 0;
    }
    //开根号
    public static double SQRT(double num){
        if(num<0){
            System.out.println("负数不能开跟");
            System.exit(0);
        }else{
            return Math.sqrt(num);
        }
        return 0;
    }
}

//编写一个Operation类 返回一个运算符的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 2;
    private static int MUL = 3;
    private static int DIV = 3;
    private static int POW = 4;
    private static int FAC = 5;
    private static int ROOT = 5;
    private static int TRI = 6;
    private static int LOG = 7;


    //写一个方法 返回对应优先级数字
    public static int getValue(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            case "^":
                res = POW;
                break;
            case "!":
                res = FAC;
                break;
            case "|":
                res = ROOT;
                break;
            case "sin":
                res = TRI;
                break;
            case "cos":
                res = TRI;
                break;
            case "tan":
                res = TRI;
                break;
            case "asin":
                res = TRI;
                break;
            case "acos":
                res = TRI;
                break;
            case "atan":
                res = TRI;
                break;
            case "ln":
                res = LOG;
                break;
            case "log":
                res = LOG;
                break;
            default:
                break;
        }
        return res;
    }

}
