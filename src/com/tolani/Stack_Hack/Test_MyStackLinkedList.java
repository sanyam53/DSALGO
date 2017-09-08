package com.tolani.Stack_Hack;


public class Test_MyStackLinkedList {

    public static void main(String[] args)
    {
        MyStack_LinkedList stcklist= new MyStack_LinkedList();

        stcklist.push(1);
        stcklist.push(2);
        stcklist.push(3);
        stcklist.push(5);

        stcklist.displayStack();

        System.out.println(stcklist.peek());

        stcklist.pop();
        stcklist.pop();

        stcklist.displayStack();
    }
}
