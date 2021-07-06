package ShangGuiGu.stack;

    /*
    用栈结构实现中缀表达式的计算
    e.g. 3*6 - 5 + 9 = 22
    思路：
        1.通过一个index遍历表达式，同时准备两个栈：数栈（存放数字）和符号栈（存放运算符）
        2.如果index发现是数字，则直接入数栈
        3.如果发现是操作符
            3.1如果符号栈为空，则直接入栈
            3.2如果当前符号优先级小于等于栈顶符号，则先不入栈，从符号栈中pop出一个符号，
               从数栈中pop出两个数字进行计算，将结果再存入数栈中后，再将index的符号存入符号栈中
            3.3如果当前符号优先级大于栈顶符号，则直接入栈
        4.遍历完成后，按顺序从数栈中pop出两个数字，从符号栈中pop出一个操作符进行计算，将结果存入数栈中，
          重复上述操作，直到数栈中只剩一个数字，即为运算结果
     */

import java.util.Stack;

public class CalculatorDemo {
    public static void main(String[] args) {
        String expression = "((1+4*4 - 1-1) /5+(2+3)*2)";


        System.out.println(Calculatorplus(expression));
    }

    //计算带有括号的表达式
    public static int Calculatorplus(String expression){
        //创建一个栈空间存放 '('的坐标
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {//从头遍历表达式
            if(expression.charAt(i) == '('){
                //如果是'(' 则将坐标存入栈中
                nums.push(i);
            }else if(expression.charAt(i) == ')'){
                //出现')'则从栈中pop出最近出现的'('的坐标，并将其中的不带有括号的表达式进行计算
                int l = nums.pop();
                String res = String.valueOf(Calculator(expression.substring(l + 1,i)));
                int count = i - l - String.valueOf(Calculator(expression.substring(l + 1,i))).length();
                //为了防止表达式长度发生变化，变化的长度用" "填补
                for (int j = 0; j < count; j++) {
                    res +=" ";
                }
                //将运算完成的结果与原先的表达式进行替换
                expression = expression.substring(0,l) + res + expression.substring(i);
            }
        }
        return Calculator(expression);

    }

    //计算不带有括号的表达式
    public static int Calculator(String expression) {
        //创建两个栈：数栈和符号栈
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operStack = new Stack<>();
        //定义扫描索引
        int index = 0;//用于扫描
        int num = 0;
        char cur = ' '; // cur保存每次扫描的结果
        String keepNum = "";//用于拼接数字
        //开始遍历
        do {
            //得到一个字符
            cur = expression.charAt(index);
            //判断cur是什么，然后做相应的处理
            if (isOper(cur)) {//如果是操作符
                //判断当前的符号栈是否为空
                if (operStack.isEmpty()) {
                    //如果为空直接入栈
                    operStack.push(cur);
                } else {
                    //处理 判断优先级
                    if (priority(cur) <= priority(operStack.peek())) {
                        /*
                        如果当前符号优先级小于等于栈顶符号，则先不入栈，从符号栈中pop出一个符号，
                        从数栈中pop出两个数字进行计算，将结果再存入数栈中后，再将index的符号存入符号栈中
                         */
                        numStack.push(cal(numStack.pop(), numStack.pop(), operStack.pop()));
                        operStack.push(cur);
                    } else {
                        operStack.push(cur);
                    }
                }
            } else if(isNum(cur)){//如果是数字
                //处理多位数
                keepNum += cur;
                //如果cur是expression的最后一位，则直接入栈
                if(index + 1 == expression.length()){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字，如果是就继续扫描，不是则直接入栈
                    if(!isNum(expression.charAt(index + 1))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //后移index并判断是否扫描结束
            index++;
        } while (index < expression.length());
        while (!operStack.isEmpty()) {
            //如果符号栈为空，则计算结束，此时数栈只有一个数字，即结果
            int num1 = numStack.pop(), num2 = numStack.pop();
            numStack.push(cal(num1, num2, operStack.pop()));
        }
        return numStack.pop();
    }

    //判断字符是否为数字字符
    private static boolean isNum(char cur) {
        return cur >= '0' && cur <= '9';
    }

    /**
     * 返回运算符的优先级 程序员确定 数字来表示 数字越大 优先级越高
     * @param oper 操作符 ‘+’  ‘-’  ‘*’  ‘/’
     * @return 返回优先级
     */
    public static int priority(char oper){
        if(oper == '*' || oper == '/'){
            return 2;
        }else if(oper == '-'){
            return 1;
        } else if(oper == '+'){
            return 0;
        } else{
            return -1;
        }
    }

    /**
     *
     * @param val 遍历表达式字符串
     * @return 返回此字符是否为操作符
     */
    public static boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //两数计算方法
     public static int cal(int num1, int num2, char oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序 后出栈的数 减 先出栈的数
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
     }
}
