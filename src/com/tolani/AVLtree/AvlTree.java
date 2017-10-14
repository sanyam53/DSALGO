package com.tolani.AVLtree;

import com.tolani.BST.BST;
import com.tolani.Tree_Tree.MyTree;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTree {

    Node root;

    public static class Node {
        int data;
        Node left, right;
        int height;


        Node(int d) {
            data = d;
            left = right = null;
            height = 1;          // we put this here '1' bcz suppse a node has one l.child then we ll put balance factor as 1-0 = 1; means
                                 // we count height of null as 0 : how u define height is very imp here : so tc
        }
    }

    int height(Node n)
        {
            if(n == null) return 0;       // see we r returning height 0 for the null side
            else return n.height;
        }


  // one thing to take care in rotate function is that u dnt have to update the link in the rotate function but it will be updated in the
  // insertion function where u r returning a node from below level to above level one by one , so just return the new node tht replaced the
  // old node after the rotation , dnt update any link

    public Node leftRotate(Node n)
    {
        Node temp = n.right;
        Node t2 = temp.left;
        temp.left = n;
        n.right = t2;

        n.height = Math.max(height(n.left),height(n.right)) + 1;
        temp.height =Math.max(height(temp.left),height(temp.right)) + 1;

        return temp;      // bcz 'n' is now changed after the rotation so u return tht node which came up bcz of rotation
    }

    public Node rightRotate(Node n)
    {
        Node temp = n.left;
        Node t2 = temp.right;
        temp.right = n;
        n.left = t2;

        n.height = Math.max(height(n.left),height(n.right)) + 1;        // optimization can be done here lets see it later.
        temp.height = Math.max(height(temp.left),height(temp.right)) + 1;

        return temp;
    }

    public int checkBalance(Node n)
    {
        if(n == null) return 0;
        else
        return (height(n.left) - height(n.right));          // height(lst) - h(rst)
    }

    public Node insert(Node root,int key)
    {
        Node n = new Node(key);

       if(root == null)
           return n;

       else if(key < root.data)
       {
            root.left = insert(root.left,key);
       }

       else if(key > root.data)
       {
           root.right = insert(root.right,key);
       }
       else   // duplicate key not allwd
       {
           return root;
       }

       // balancing logic will go here bcz in insertion while calling recursively it will return
        // a parent node one by one starting frm insertion point , whch we ve to chck ultimately
        // for imbalance , and if imbalance then we will do rotations : and in rotatins also we
        // update the heights

       root.height = Math.max(height(root.left),height(root.right)) + 1;

       int balance;

       balance = checkBalance(root);

       // RR case
       if(balance > 1 && key < root.left.data)  // scnd cndtn is to chck if key is r.c. of r.child
       {
           return rightRotate(root);
       }

       // LL case
       if(balance < -1 && key > root.right.data)
       {
           return leftRotate(root);           // we are rotating and we r returning the new node which replaces the old node at tht pos
       }

       // RL case
       if(balance > 1 && key > root.left.data)
       {
           root.left = leftRotate(root.left);
           return rightRotate(root);
       }

       // LR case
       if(balance < -1 && key < root.right.data)
       {
           root.right = rightRotate(root.right);
           return leftRotate(root);
       }

      // here logic of avltree rotatins finishes and as we return the next node abv

       return root;

    }


    public void levelOrderTraversal(Node root)
    {
        Queue<Node> q = new LinkedList<Node>();   // refer geeksforgeeks for understndng Queue in java

        q.add(root);

        while(!q.isEmpty())
        {
            Node temp = q.poll();            // dequeue frm the front of the queue (or head of q)
            System.out.print(temp.data + " ");     // print data

            if(temp.left != null) q.add(temp.left);    // enqueue left child if prsnt
            if(temp.right != null) q.add(temp.right);   // enqueue right child if prsnt
        }
    }

}
