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

        n.height = updateHeight(n);
        temp.height = updateHeight(temp);

        return temp;      // bcz 'n' is now changed after the rotation so u return tht node which came up bcz of rotation
    }

    public Node rightRotate(Node n)
    {
        Node temp = n.left;
        Node t2 = temp.right;
        temp.right = n;
        n.left = t2;

        n.height = updateHeight(n);        // optimization can be done here lets see it later.
        temp.height = updateHeight(temp);

        return temp;
    }

    public int updateHeight(Node n)
    {
        return Math.max(height(n.left),height(n.right)) + 1;
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

       root.height = updateHeight(root);

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
        else if(root.data > key)
        {
            root.left = delete(root.left,key);
        }

        else           // this means u r reached at the node u wnt to delete means root.data == key , now chck fr the 3 cases of deletion
        {
            if(root.left == null)      // if left ptr is null  OR  if bth will be null then it is returning a right ptr means returning null
            {
                // in this case u delete the node and just join the right child if right child is not null
                return root.right;
            }

            else if(root.right == null)   // if right ptr is null
            {
                return root.left;
            }

            else                      // both r not null
            {
                // so u find the inorder predecesor of a node , u swap the value of it wid the node we wnt to delete and delete the predecsr node

                Node prede = inorderPredecessor(treeRoot,root.data);
                swap(prede, root);

                // deleting inorder predecessor now : we knw tht it is on the left side so we drctly call root.left for efficiency

                root.left = delete(root.left, prede.data);
            }
        }


        root.height = updateHeight(root);

        int balance = checkBalance(root);

       // balance is > 1 means lst height is more than rst height , means we deletd smthng from rst , bcz we r chckng balance aftr deletion
        // now so two cases in this case : LL case (rotate right) and LR case ( rotate left then right )
        // now to chck tht we will check the left subtree : if it has balance factor >=0 means (0 or 1) means it is LL CASE
        // If it is < 0 (means -1 : here it cn be -1 only bcz we searched for first imbalanced node cmng frm the bottom so
        // balance factor of root.left cnt be -2 , so if it is -1 means rst has more and lst has less now if we drctly rotate right on root
        // then imbalance will happen : try to do it , so first rotate left on root.left and then rotate right on root

        if(balance > 1 && checkBalance(root.left) >=0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && checkBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && checkBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && checkBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
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

    private void swap(Node a , Node b) {
        int tempdata = a.data;
        a.data = b.data;
        b.data = tempdata;
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
