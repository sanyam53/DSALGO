package com.tolani.Tree_Tree;

import com.tolani.Tree_Tree.MyTree.*;

public class Test_MyTree {

    public static void main(String[] args)
    {
        MyTree btree = new MyTree();

        Node n1 = new Node(1);

        btree.root = n1;

        Node n2 = new Node(2);

        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.left = n2;
        n1.right = n3;

        n2.left = n4;

        n3.left = n5;
        n3.right =n6;
        n5.left = n7;

        System.out.println("postorder traversal : ");
        btree.postOrderTraversal(btree.root);
        System.out.println('\n' + "preorder traversal : ");
        btree.preOrderTraversal(btree.root);
        System.out.println('\n' + "inorder traversal : ");
        btree.inOrderTraversal(btree.root);

        int a = btree.totalNoOfNodes(btree.root);
        System.out.println();
        System.out.println("total no of nodes in a tree are : " + a);

        System.out.println("level of a node is : " + btree.findLevelOfNode(btree.root,n7,1));
        System.out.println("height of a node is : " + btree.findHeightOfNode(n1));

        System.out.println("isDescendant or not ? :" + btree.isDescendent(n7,n1));

        System.out.println("areSibling or not ? : " + btree.isSibling(btree.root,n5,n6));

        System.out.println("LCA problem : " + btree.closestCommonAncestor(btree.root,n5,n6).data);

        int[] in = {4,2,1,7,5,3,6};
        int[] pre ={1,2,4,3,5,7,6};

        Node rootoftree = btree.constructTree(in,pre,0,in.length-1);

        MyTree mt = new MyTree();
        mt.root = rootoftree;

        mt.postOrderTraversal(mt.root);

    }
}
