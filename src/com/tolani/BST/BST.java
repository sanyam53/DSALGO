package com.tolani.BST;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

    Node root;

    public static class Node
    {
        int data;
        Node left , right;

        Node(int d)
        {
           data = d;
           left = right = null;
        }
    }

    public Node insert(Node root , int key)
    {
        Node t = new Node(key);

        if(root == null) {
            root = t;
            return root;      // so we r returning a reference of a node we wnt to insert
        }

        else if(root.data < key)
        {
            root.right = insert(root.right,key);  // so ref of the node we linked will b returnd here
        }
        else if(root.data > key)
        {
            root.left = insert(root.left,key);
        }

        return root;    // returning a pointer to the unchanged node

    }

    public Node search(Node root,int key)
    {
        if(root == null) return null;

        if(root.data == key) return root;

        else if (root.data < key) return search(root.right,key);

        else     // tc : if u put everythng as an elif then u ve to explicitly gv return statemnt. !
        {
            return search(root.left,key);
        }

    }

    public Node inorderPredecessor(Node root,int k)
    {
        Node t;
        Node key = search(root,k);    // we are returning a ptr to tht node in a tree so we cn traverse ahead

        if(key.left != null)
        {
            t = key.left;

            while(t.right != null)
            {
                t = t.right;
            }

            return t;
        }

        return null;
    }

    public Node delete(Node root,int key)     // u dnt wnt to return a ptr in case of deletin but it ll be useful for recursin within d functin
    {
        // if u use search functin here and drctly get to tht node then problem with the deletion
        // problem is as u dnt hv parent ptr in ur impn , how u reach to d parent , so impn delete fun carefully
        Node treeRoot = root;
        if(root == null) return null;

        else if(root.data < key)
        {
            root.right = delete(root.right,key);
        }
        else if(root.data >key)
        {
            root.left = delete(root.left,key);
        }

        else           // this means u r reached at the node u wnt to delete means root.data == key , now chck fr the 3 cases of deletion
        {
            if(root.left == null)      // if left ptr is null  OR  if bth will be null then it is returning a right ptr means returning null
            {
                // in this case u delete the node and just join the right child
                return root.right;
            }

            else if(root.right == null)   // if right ptr is null
            {
                return root.left;
            }

            else                      // both r not null
            {
                // so u find the inorder predecesor of a node , u swap the values of both and delete the predecsr node

                Node prede = inorderPredecessor(treeRoot, root.data);
                swap(prede, root);

                // deleting inorder predecessor now : as it is a recursive call
                root.left = delete(root.left, prede.data);
            }
        }
        return root;
    }

    private void swap(Node a , Node b) {
        int tempdata = a.data;
        a.data = b.data;
        b.data = tempdata;
    }

    // we cn implement a level order traversal using a queue data structure

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

