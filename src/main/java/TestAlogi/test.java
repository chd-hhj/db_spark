package TestAlogi;

import org.junit.Test;

/**
 * @author hhj
 * @create 2021-05-06 20:13
 */
public class test {
    @Test
    public void test1(){
        int[] top=new int[3];
        top[0]=-2;
        top[1]=-1;
        top[2]=0;
        Integer a=Integer.valueOf(1);
        System.out.println(a.intValue());
    }
}


class TripleInOne {
    int[] stack;
    int[] top=new int[3];

    public TripleInOne(int stackSize) {
        top[0]=-2;
        top[1]=-1;
        top[2]=0;
        stack=new int[3*stackSize];
    }

    public void push(int stackNum, int value) {
        if(top[stackNum]+3>stack.length-1)
            return;
        else{
            top[stackNum]=top[stackNum]+3;
            stack[top[stackNum]]=value;
        }
    }

    public int pop(int stackNum) {
        if(top[stackNum]<=0)
            return -1;
        else{
            top[stackNum]+=-3;
            return stack[top[stackNum]+3];
        }
    }

    public int peek(int stackNum) {
        if(top[stackNum]<=0)
            return -1;
        return stack[top[stackNum]];
    }

    public boolean isEmpty(int stackNum) {
        if (top[stackNum]<=0)
            return true;
        else
            return false;

    }
}
