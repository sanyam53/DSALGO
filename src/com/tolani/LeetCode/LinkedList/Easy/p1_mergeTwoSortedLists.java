package com.tolani.LeetCode.LinkedList.Easy;

import com.tolani.LinkedList.MyLinkedList;
import static com.tolani.LinkedList.MyLinkedList.Node;

public class p1_mergeTwoSortedLists {

    public static void main(String[] args)
    {
        MyLinkedList l1 = new MyLinkedList();

        MyLinkedList l2 = new MyLinkedList();

        l1.insertAtBegin(10);
        l1.insertAtEnd(20);
        l1.insertAtEnd(30);
        l1.insertAtEnd(40);

        l2.insertAtBegin(11);
        l2.insertAtEnd(21);
        l2.insertAtEnd(31);
        l2.insertAtEnd(41);

        l1.displayLinkedList();
        l2.displayLinkedList();

       Node newlist = mergeTwoLists(l1.head,l2.head);

        l1.head = newlist;

        l1.displayLinkedList();
    }

    public static Node mergeTwoLists(Node l1, Node l2) {

        Node p1 = l1;      // pointer to the first list
        Node p2 = l2;      // scnd one

        Node newListDummy = new Node(-1);   // head  of the new linked list created : we took one dummy node ok

        Node temp = newListDummy;

        while((p1 != null) && (p2!= null))
        {
            if(p1.data < p2.data)
            {
                temp.next = p1;

                temp = p1;

                p1 = p1.next;
            }

            else
            {
                temp.next = p2;
                temp = p2;
                p2 = p2.next;
            }

        }

        if(p1 == null)
        {
            temp.next = p2;
        }
        else
        {temp.next = p1;}


        return newListDummy.next;
    }

}