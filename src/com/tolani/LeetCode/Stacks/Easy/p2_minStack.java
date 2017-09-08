/*
*   in addition to the normal functions of stack : push,pop and peek
*   in this problem we ve to implement the function called getMin() : which shuld return min in O(1) time
*   (this prob also given in ctci book)
*
*   Logic here is : MInimum element doesn't change oftenly , it only changes when smaller elemnt thn it comes
*
*    if 1st element is min in the whole list of input then no other element can be the min ever , bcz we cant pop 1st elemnt frm the stack without
*    popping elements above it ! and this is the trick which u need to analyze in this problm
*
*    naive approach : pick one var 'min' and when u pop min frm the stack search for min in the stack again : this is unefficient code
*
*    approach 1 : we can insert one field in the our mystack class , which will store current min of the stack
*                   (this is linked list kind approach) , but as we said min doesnt change oftenly , we can maintain an another stack contains mins only which leads to the approach no 2
*
*     approach 2 : another stack which maintains mins only of the list , now if we pop an element frm the stack then we will check if it is top of the
*                   mins stack then we will pop it frm there also
*
* */

package com.tolani.LeetCode.Stacks.Easy;

// this is the impn of the approach 1

class MinStack {

        Node head;         // head of the list or we can say top of the list

        static class Node{

                int data;
                int min;
                Node next;

                Node(int x)
                {
                        this.data = x; this.next = null;
                }
        }

        public MinStack() {
                head = null;
        }

// push fun is imp here

        public void push(int x) {

                Node n = new Node(x);

                if (head == null)         // when only one node on the stack then it is the min only
                {
                        head = n;
                        n.min = x;
                }
                else                           // else we r comparing it with min field of the node below it in the stack
                {
                        if(head.min > x)
                        {
                                n.min = x;
                        }
                        else
                        {
                                n.min = head.min;
                        }

                        n.next = head;
                        head = n;
                }
        }

        public void pop() {

                if(head != null)
                {
                        head = head.next;
                }
        }

        public int top() {
                return head.data;
        }

        public int getMin() {                // return the cur min field of the top of the stack to return the min at this point of time in O(1)
                return head.min;
        }
}

