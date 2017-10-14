package com.tolani.BST;

import com.tolani.Tree_Tree.MyTree;

public class Test_BST {

    public static void main(String[] args)
    {
        BST bst = new BST();

        BST.Node r = new BST.Node(6);
        bst.root = r;
        System.out.println(bst.insert(r,1).data);   // 6 wl be returnd we lnked 1 as a chld of 6
        bst.insert(r,3);
        bst.insert(r,8);
        bst.insert(r,-4);
        bst.insert(r,-1);
        bst.insert(r,100);
        bst.insert(r,7);
        bst.insert(r,-100);
        bst.insert(r,0);

        System.out.println("search result in bst " + bst.search(bst.root,6).data);
    
        bst.levelOrderTraversal(bst.root);
        System.out.println();

     // finding inorder predecessor means : largest in a lst
        System.out.println("inorder predecessor of node with value 1 : " + bst.inorderPredecessor(bst.root,1).data);

        // deletion in a bst

        bst.delete(bst.root,1);
        bst.levelOrderTraversal(bst.root);

    // lets see the inorder traversal of bst : whch shuld be increasing sorted seqn
        System.out.println();
        bst.inorderTraversal(r);
    }
}
