package com.tolani.AVLtree;

public class Test_avltree {

    public static void main(String[] args)
    {
        AvlTree at = new AvlTree();

        at.root = at.insert(at.root,10);
        at.root = at.insert(at.root,20);
        at.root = at.insert(at.root,30);
        at.root = at.insert(at.root,40);
        at.root = at.insert(at.root,50);
        at.root = at.insert(at.root,25);

        at.levelOrderTraversal(at.root);




    }
}
