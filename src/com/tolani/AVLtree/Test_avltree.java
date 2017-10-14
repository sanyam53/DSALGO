package com.tolani.AVLtree;

public class Test_avltree {

    public static void main(String[] args)
    {
        AvlTree at = new AvlTree();

        // take care here tht root also can be changed while rotations so dnt create one root and work on it as u did in bst ! keep ur eyes and mind open

        at.root = at.insert(at.root,10);
        at.root = at.insert(at.root,20);
        at.root = at.insert(at.root,30);
        at.root = at.insert(at.root,40);
        at.root = at.insert(at.root,50);
        at.root = at.insert(at.root,25);

        at.levelOrderTraversal(at.root);

        at.root = at.delete(at.root,50);
        System.out.println();
        at.levelOrderTraversal(at.root);
        at.root = at.delete(at.root,40);
        System.out.println();
        at.levelOrderTraversal(at.root);
        at.root = at.delete(at.root,10);
        System.out.println();
        at.levelOrderTraversal(at.root);




    }
}
