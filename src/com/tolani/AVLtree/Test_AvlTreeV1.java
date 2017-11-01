package com.tolani.AVLtree;

public class Test_AvlTreeV1 {

    public static void main(String[] args)
    {

        AvlTreeV1 at = new AvlTreeV1();

        // take care here tht root also can be changed while rotations so dnt create one root and work on it as u did in bst ! keep ur eyes and mind open

        // debug the insertion and chck the NN follows or not !

        at.root = at.insert(at.root,10);
        at.root = at.insert(at.root,20);
        at.root = at.insert(at.root,30);
        at.root = at.insert(at.root,40);
        at.root = at.insert(at.root,50);
        at.root = at.insert(at.root,25);

        // at.root = at.delete(at.root,50);     // to debug maxgap
        // at.root = at.delete(at.root,25);    // to debug mingap

        at.levelOrderTraversal(at.root);
/*
        // explore below code to debug the deletion

        at.root = at.delete(at.root,50);
        System.out.println();
        at.levelOrderTraversal(at.root);
        at.root = at.delete(at.root,40);
        System.out.println();
        at.levelOrderTraversal(at.root);
        at.root = at.delete(at.root,10);
        System.out.println();
        at.levelOrderTraversal(at.root);
        System.out.println();
        System.out.println(at.root.left.data + " " + at.root.right.data );

 */
        System.out.println();
        at.inOrderTraversal(at.root);       // inorder traversal gives sorted seqn in the increasing order
        System.out.println();
        System.out.println(at.FindRank(at.root,25));

        System.out.println(at.FindElementWithRank(at.root,4).data);

        System.out.println(at.numOfNums(at.root,10,40));

        System.out.println("sum is " + at.prefixSum(at.root,50));

        System.out.println("sum of nums " + at.sumOfNums(at.root,10,50));

        System.out.println("max gap in the tree is "  + at.maxgap(at.root));

        System.out.println("min gap in the tree is " + at.mingap(at.root));

        System.out.println("mingap bw x & y is : " + at.mingap(at.root,25,50));
    }
}
