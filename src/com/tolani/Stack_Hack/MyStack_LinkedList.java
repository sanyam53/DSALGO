package com.tolani.Stack_Hack;

public class MyStack_LinkedList {

    public Node head;

    public static class Node{

        int data;
        Node next;

        public Node(int x)
        {
            data = x;
            next = null;
        }

    }

    public MyStack_LinkedList()
    {
        head = null;
    }

    public void push(int x)    // pushing the element on the top of the stack
    {
       Node n = new Node(x);

       if(head == null)
       {
           head = n;
       }
       else
       {
           n.next = head;      // insert at the beginning of d linked list which will tk constant time
           head = n;
       }
    }

    public int pop()         // removes the element frm the top of the stack
    {
        if(head == null)
        {
            System.out.println("undeflow");
            return -1;
        }

        Node t = head;
        head = head.next;

        return t.data;
    }

    public int peek()         // returns the contents of the top of the stack
    {
        return head.data;
    }

    public void displayStack()
    {
        Node t = head;

        if(t == null) {System.out.println("stack is empty"); return;}

        StringBuilder sb = new StringBuilder("");

        System.out.print("top -> ");
        while(t != null)
        {
            sb.append(t.data + " ");
            t = t.next;
        }

        System.out.println(sb);
    }
}
