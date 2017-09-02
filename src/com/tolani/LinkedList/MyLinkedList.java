package com.tolani.LinkedList;

public class MyLinkedList {

    Node head;   // head of the linked list which points to the type Node
    int size;     // size of the linked list means : #of nodes

    static class Node            // static nested class : so tht we cn drctly access it without creting outer class obj in main method
    {
        int data;     // data
        Node next;    // link to the next node

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node link) {
            this.data = data;
            this.next = link;
        }

    }

    public void displayLinkedList() {
        Node t = head;

        if(t == null){System.out.println("Linked List is empty :p");}

        while (t != null) {

            if(t.next == null) { System.out.println(t.data + " -> " + "NULL");}
            else System.out.print(t.data + " -> ");
            t = t.next;
        }
    }

    public void insertAtBegin(int data)
    {
        Node n = new Node(data);

        if(this.head == null){ head =n;}
        else
        {
            n.next = head;
            head = n;
        }
    }

    public void insertAtEnd(int data)
    {
        Node n = new Node(data);

        Node t = this.head;

        while(t.next != null)
        {
            t = t.next;
        }

        t.next = n;
        // n.next is null by default
    }

    public void insertAtPos(int data)
    {

    }

    public void deleteFromBegin()
    {
        // u dnt hv to free in java : garbage collector is thr for u man ! :>

        if(head == null){ System.out.println("underflow :p");}
        else {
            head = head.next;
        }
    }

    public void deleteFromEnd()
    {

        if(head == null){ System.out.println("underflow :p");}

        else if(head.next == null){ head = null;}

        else
        {
            Node t = head;
            while(t.next.next != null)
            {
                t = t.next;
            }

            t.next = null;
        }
    }

}