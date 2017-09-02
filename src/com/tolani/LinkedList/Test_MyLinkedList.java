/*  diff bw import and static import :

    import : it allws us to use classes of a pacakge without package qualification
    static import : it allws us to use static members of the CLASS without class qualification

    now here Node falls in both d categories , so anything is fine to write
*
* */


package com.tolani.LinkedList;

import com.tolani.LinkedList.MyLinkedList.*;   // this will import all static members of MyLinkedList

//import static com.tolani.LinkedList.MyLinkedList.Node;   // Node is a static member of class MyLinkedList
                                                        // so we ve to do static import else we ve to use it by classname.static member
                                                                                                                // MyLinkedList.Node
public class Test_MyLinkedList {

    public static void main(String[] args) {
        MyLinkedList ll = new MyLinkedList();

        // System.out.println(ll.size);    0 by default

        Node n1 = new Node(1);

        ll.head = n1;      // head -> n1

        Node n2 = new Node(2);

        Node n3 = new Node(3, n2);       // n3 -> n2

        n1.next = n3;     // head -> n1 -> n3 -> n2 -> null

        ll.displayLinkedList();

        ll.insertAtBegin(23);
        ll.displayLinkedList();

        ll.insertAtEnd(50);
        ll.displayLinkedList();

        ll.deleteFromBegin();
        ll.displayLinkedList();

        ll.deleteFromEnd();
        ll.displayLinkedList();

        ll.deleteFromBegin();ll.deleteFromBegin();
        ll.displayLinkedList();

        ll.deleteFromEnd();
        ll.displayLinkedList();   // blank output bcz thr is nothing to display
    }
}
