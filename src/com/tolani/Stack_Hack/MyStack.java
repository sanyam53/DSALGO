package com.tolani.Stack_Hack;

public class MyStack {

    public int TOS;
    int size;
    int stck[];

    public MyStack(int s)
    {
        this.size = s;
        stck = new int[s];
        TOS = -1;
    }

    public void push(int i)
    {
        if(TOS >= size-1)
        {
            System.out.println("overflow");
        }
        else {
            stck[++TOS] = i;
        }
    }

    public int pop() {
        if (TOS == -1) {
            System.out.println("underflow");
                }
        else {
            int temp = stck[TOS--];
            return temp;
        }

        return -1;
    }

    public boolean isStackEmpty()
    {
        if(TOS == -1) return true;
        else return false;
    }

    public int pick()
    {
        return stck[TOS];       // returns the content of the top of d stack
    }
}
