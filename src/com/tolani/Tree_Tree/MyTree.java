package com.tolani.Tree_Tree;

public class MyTree {

    public Node root;

    public static class Node {
        public int data;
        public Node left, right;

        public Node(int x) {
            data = x;
            left = right = null;
        }
    }

    public MyTree() {
        root = null;
    }

    // Tree Traversals

    public void preOrderTraversal(Node root) {
        if (root == null) return;
        else {

            System.out.print(root.data + " ");

            preOrderTraversal(root.left);
            preOrderTraversal(root.right);

        }
    }

    public void inOrderTraversal(Node root) {
        if (root == null) return;
        else {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);

        }
    }

    public void postOrderTraversal(Node root) {
        if (root == null) return;
        else {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }


    // Recursive beauty of a tree : totalNoOfNodes , Height of a node , level of a node , isDescendent or not , areSibling or not ,

    public int totalNoOfNodes(Node root) {
        if (root == null) return 0;

        else      // all other cases will be handled by this : if both children null , if one is null
        {
            return (1 + totalNoOfNodes(root.left) + totalNoOfNodes(root.right));
        }
    }

    public int findLevelOfNode(Node root, Node node, int intialLevel) {
        if (root == null) return -1;             // this means u didnt find a node in the subtree

        else if (node == root)
            return intialLevel;           // which is usually 1   // this comparisin means u found the node

        else       // we have to check that node is in left subtree or right subtree : for tht we ve to recur on both sides
        {
            int leftLevel = findLevelOfNode(root.left, node, intialLevel + 1);   // as we move down : level increases by 1
            int rightLevel = findLevelOfNode(root.right, node, intialLevel + 1);    // same for right subtree

            // the side on which node is not present , frm tht side -1 will be returned

            if (leftLevel == -1) return rightLevel;
            else return leftLevel;
        }
    }


    public int findHeightOfNode(Node n) {
        if (n == null)
            return 0;                  // boundary case we ve to handle in case one link is thr and one is not prsnt

        if (n.left == null && n.right == null)            // if node is a leaf then height is 0
        {
            return 0;
        } else                                             // if node is internal node then height is max(lst , rst)
        {
            return 1 + max(findHeightOfNode(n.left), findHeightOfNode(n.right));
        }
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public boolean isDescendent(Node n1, Node n2) {
        if (n2 == null) return false;            // boundry case u ve to handle

        else if (n2.left == n1 || n2.right == n1)
            return true;        // if the node is in the left or right then yes it is descendent

        else
            return isDescendent(n1, n2.left) || isDescendent(n1, n2.right);     // if not then search in the LST and RST both , as it is boolean we cn do this
    }

    public boolean isSibling(Node root, Node n1, Node n2) {
        if (root == null)
            return false;          // this is the base case which will handle when node is not found in the subtree

        else if ((root.left == n1 && root.right == n2) || (root.left == n2 && root.left == n1))    // chck if both r d child of same parent (node)
        {
            return true;
        } else
            return isSibling(root.left, n1, n2) || isSibling(root.right, n1, n2);         // else recur on lst and rst to check below

    }

    // if both the nodes are on same level and not siblings then they are said to be cousins !

    public boolean isCousin(Node root, Node node1, Node node2) {
        int l1 = findLevelOfNode(root, node1, 1);
        int l2 = findLevelOfNode(root, node2, 1);

        if (l1 == l2 && !isSibling(root, node1, node2)) return true;
        else return false;

    }



    public Node closestCommonAncestor(Node root , Node node1 , Node node2)
    {

        if (root == null)                              // means the list is finished and we didnt find tht node we reached leaf or an end of tree
            return null;

        if (root == node1 || root == node2)           // means we reached on one of the node1 or node 2
            return root;

        Node left = closestCommonAncestor(root.left,node1, node2);        // if abv two cases dnt satisfy means we reach on tht node which not equal to n1,n2 and
        Node right = closestCommonAncestor(root.right, node1 , node2);    // also not an end of tree of leaf, so go to left and right of tht node to serach

        if (left != null && right != null)             // if both children dnt return null , means tht is the lowest comman ancestor
            return root;

        if (left != null)                         // if left is not null then left is lca ! seee the ex for this bcz at the root either left and right both comes then root
            return left;                            // is the ans , and if left cms then left ans , and if only right comes then right one is the ans.
        else
            return right;                          // else right bcz lca exists for sure !

// here after calling recursively on left and right we check for all the psslble cases
        // 1. if both child return null , or both return non null , if one return null and other one not null
        // if both are not null then return root itself , if one is not null then return tht non null reference means either left or right.

    }

}