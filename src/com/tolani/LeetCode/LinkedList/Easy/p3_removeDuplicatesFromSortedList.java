/*
    Given a sorted linked list, delete all duplicates such that each element appear only once.
    Given 1->1->2->3->3, return 1->2->3.

*   // note : it can be done using one pointer only , see the solution.
*       by using one pointer and by using tht pointer.next concept
* */


package com.tolani.LeetCode.LinkedList.Easy;

import com.tolani.LinkedList.MyLinkedList;
import static com.tolani.LinkedList.MyLinkedList.Node;

public class p3_removeDuplicatesFromSortedList {

    public static void main(String[] args)
    {
        MyLinkedList l1 = new MyLinkedList();

        MyLinkedList l2 = new MyLinkedList();

        l1.insertAtBegin(10);
        l1.insertAtEnd(10);
        l1.insertAtEnd(10);
        l1.insertAtEnd(40);

        deleteDuplicates(l1.head);

        l1.displayLinkedList();
    }

    public static Node deleteDuplicates(Node head) {

        if(head == null || head.next == null) return head;      // this will handle both of the cases

        Node p1 = head;
        Node p2 = head.next;

        while(p2 != null)
        {
            if(p1.data == p2.data)     // if node values are equal
            {
                p1.next = p2.next;
                p2 = p2.next;
                continue;
            }

            p1 = p1.next;
            p2 = p2.next;
        }


        return head;
    }
}

