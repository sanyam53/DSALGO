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

    public void DisplayLinkedList(Node head) {
        Node t = head;

        while (t != null) {
            System.out.print(t.data + " -> ");
            t = t.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList ll = new MyLinkedList();

        // System.out.println(ll.size);    0 by default

        Node n1 = new Node(1);

        ll.head = n1;      // head -> n1

        Node n2 = new Node(2);

        Node n3 = new Node(3, n2);       // n3 -> n2

        n1.next = n3;     // head -> n1 -> n3 -> n2 -> null

        ll.DisplayLinkedList(ll.head);
    }
}