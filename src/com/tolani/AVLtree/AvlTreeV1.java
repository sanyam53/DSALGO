package com.tolani.AVLtree;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTreeV1 {

    Node root;

    public static class Node {
        int data;
        Node left, right;
        int height;

        // additional info we wnt to store within a node
        int NoOfNodes;   // this is : NN(Lst) + NN(Rst) + 1
        int sum;         // this is : sum(lst) + sum(rst) + this.data ;

        // additional info to calculate : mingap & maxgap
        int max,min;


        Node(int d) {
            data = d;
            left = right = null;
            height = 1;// we put this here '1' bcz suppse a node has one l.child then we ll put balance factor as 1-0 = 1; means
            // we count height of null as 0 : how u define height is very imp here : so tc height of leaf is 1 nd not 0

            NoOfNodes = 1;
            sum = d ;

            max = min = d;
        }
    }

    int getHeight(Node n)
    {
        if(n == null) return 0;       // see we r returning height 0 for the null side
        else return n.height;
    }

    // addtional method of NN
    int getNoOfNodes(Node n)
    {
        if(n == null) return 0;
        else return n.NoOfNodes;
    }

    int getSum(Node n)
    {
        if(n == null) return 0;
        else
            return n.sum;
    }

    int getMax(Node n)
    {
        if(n == null) return -1;
        else return n.max;
    }

    int getMin(Node n)
    {
        if(n == null) return -1;
        else return n.min;
    }



    /*
        2 rotate functions ( sonu & monu of avl trees ) : rotation is nothing but some pointer changes ! thts it

      one thing to take care in rotate functions is that u dnt have to update the link in the rotate function but it will be updated in the
      insertion function where u r returning a node from below level to above level one by one , so just return the new node tht replaced the
      old node after the rotation , dnt update any link
    */
    public Node leftRotate(Node n)
    {
        Node temp = n.right;
        Node t2 = temp.left;
        temp.left = n;
        n.right = t2;

        n.height = updateHeight(n);
        temp.height = updateHeight(temp);

        // additional code for NN
        n.NoOfNodes = updateNoOfNodes(n);
        temp.NoOfNodes = updateNoOfNodes(temp);

        n.sum = updateSum(n);
        temp.sum = updateSum(temp);

        // additional code for min-max gap
        n.min = updateMin(n);
        temp.min = updateMin(temp);

        n.max = updateMax(n);
        temp.max = updateMax(temp);


        return temp;      // bcz 'n' is now changed after the rotation so u return tht node which came up bcz of rotation
    }

    public Node rightRotate(Node n)
    {
        Node temp = n.left;
        Node t2 = temp.right;
        temp.right = n;
        n.left = t2;

        n.height = updateHeight(n);
        temp.height = updateHeight(temp);

        // additional code
        n.NoOfNodes = updateNoOfNodes(n);
        temp.NoOfNodes = updateNoOfNodes(temp);

        n.sum = updateSum(n);
        temp.sum = updateSum(temp);

        // additional code for min-max gap
        n.min = updateMin(n);
        temp.min = updateMin(temp);

        n.max = updateMax(n);
        temp.max = updateMax(temp);

        return temp;
    }

    public int updateHeight(Node n)
    {
        return Math.max(getHeight(n.left),getHeight(n.right)) + 1;
    }


    // additional method for updating NN
    public int updateNoOfNodes(Node n) {
       return getNoOfNodes(n.left) + getNoOfNodes(n.right) + 1;
    }

    public int updateSum(Node n) { return getSum(n.left) + getSum(n.right) + n.data ; }

    public int updateMin(Node n)
    {

        if(n.left == null)
        {
            if(n.right == null) return n.data;

            else return n.right.min < n.data ? n.right.min : n.data;
        }

        else if(n.right == null)          // here we came means left link is not null
        {
            return n.left.min < n.data ? n.left.min : n.data;
        }

        else
        {
            int j=0;

            if(n.left.min > n.right.min) j = n.right.min;
            else j= n.left.min;

            if(n.data < j) return n.data;
            else return j;
        }
    }

    public int updateMax(Node n)          // tk cr while updating u compare max of subtree with key val of root , not d max of root , bcz whn
                                                // it rotates then max n min of root are invalidated so dnt use them
    {
        if(n.left == null)
        {
            if(n.right == null) return n.data;

            else return n.right.max > n.data ? n.right.max : n.data;
        }

        else if(n.right == null)          // here we came means left link is not null
        {
            return n.left.max > n.data ? n.left.max : n.data;
        }

        else
        {   int j=0;
            if(n.left.max < n.right.max) j = n.right.max;
            else j= n.left.max;

            if(n.data > j) return n.data;
            else return j;
        }
    }

    public int checkBalance(Node n)
    {
        if(n == null) return 0;
        else
            return (getHeight(n.left) - getHeight(n.right));          // height(lst) - h(rst)
    }

    public Node insert(Node root,int key)
    {
        // standard BST Insertion starts from here
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
        else   // duplicate key not allwd in bst by defn so
        {
            return root;
        }

       /* balancing logic will go here bcz in insertion while calling recursively it will return
         a parent node one by one starting frm insertion point , whch we ve to chck ultimately
         for imbalance , and if imbalance then we will do rotations : and in rotatins also we
         update the heights bcz heights will change of the nodes we rotate */

        root.height = updateHeight(root);

        // additional code for NN
        root.NoOfNodes = updateNoOfNodes(root);

        root.sum = updateSum(root);

        // add code for min max gap

        root.min = updateMin(root);
        root.max = updateMax(root);

        int balance;

        balance = checkBalance(root);

        // LL case
        if(balance > 1 && key < root.left.data)  // scnd cndtn is to chck if key is r.c. of r.child
        {
            return rightRotate(root);
        }

        // RR case
        if(balance < -1 && key > root.right.data)
        {
            return leftRotate(root);           // we are rotating and we r returning the new node which replaces the old node at tht pos
        }

        // LR case
        if(balance > 1 && key > root.left.data)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL case
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

        // additional code for NN
        root.NoOfNodes = updateNoOfNodes(root);

        root.sum = updateSum(root);

        // add code for min max gap

        root.min = updateMin(root);
        root.max = updateMax(root);

        int balance = checkBalance(root);

       /* balance is > 1 means lst height is more than rst height , means we deletd smthng from rst , bcz we r chckng balance aftr deletion
        now so two cases in this case : LL case (rotate right) and LR case ( rotate left then right )
        now to chck tht we will check the left subtree : if it has balance factor >=0 means (0 or 1) means it is LL CASE
        If it is < 0 (means -1 : here it cn be -1 only bcz we searched for first imbalanced node cmng frm the bottom so
        balance factor of root.left cnt be -2 , so if it is -1 means rst has more and lst has less now if we drctly rotate right on root
        then imbalance will happen : try to do it , so first rotate left on root.left and then rotate right on root
        */
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

    public void inOrderTraversal(Node root)
    {
        if(root != null)
        {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
        else return;
    }

    // addtional operations we wnt to do using our addtional info we put in the nodes : which is ( 1. no of nodes , 2. sum )

    // method to find a rank of a given element , in the data set

    public int FindRank(Node root, int key)
    {
        int rank = 0;
        Node t = root;

        while(t != null)
        {
            if(t.data < key)        // if key is > root then we go on RHS
            {
                    t = t.right;
            }

            else // means t.data > or = key        // key < root.data : we ll go on LHS
            {
                if(t.right != null) {
                    rank = rank + (t.right.NoOfNodes + 1);
                }
                else{ rank = rank + 1;}

                    t = t.left;
            }
        }

        return rank;

    }

    // method to find element with the given rank , in the data set

    public Node FindElementWithRank(Node root,int rank)
    {
        int rankOfRoot =0;

        if(root.right != null){
            rankOfRoot = rankOfRoot + root.right.NoOfNodes + 1;
        }
        else rankOfRoot = rankOfRoot + 1;

        if( rankOfRoot == rank)
        {
            return root;
        }

        else if(rankOfRoot < rank)         // rank is grtr means elemnt is smaller so go on lhs
        {
            return FindElementWithRank(root.left,rank-(root.right.NoOfNodes+1));    // so u going on lhs so u ignr the larger elements
                                                                                        // which affects the rank
        }
        else
            return FindElementWithRank(root.right,rank);
    }

    // iterative version of findELementWithRank
/*

    public Node FindElementWithRank(Node root,int rank)
    {
        Node t = root;
        Node x = null;
        int rankOfRoot = 0;

        while(t != null)
        {
            if(t.right != null)
                rankOfRoot = root.right.getNoOfNodes + 1;
            else
                rankOfRoot = 1;

            if(rankOfRoot == rank ) { x = t; break; }

            else if(rankOfRoot < rank)
            {
                t = t.left;
                rank = rank - rankOfRoot;
            }
            else
                t = t.right;
        }

    return x;
    }

*/

    // numOfNums(x,y) gives the count of all the nums in the data set which are strictly > x and < y :      x < #s < y

    public int numOfNums(Node root,int x , int y)
    {
        return FindRank(root,x) - FindRank(root,y) - 1;
    }

    // prefixSum() method gives sum of all the numbers in the data set which are <= key value

    public int prefixSum(Node root ,int key) {

        int sum = 0;
        Node t = root;

        while (t != null) {

            if (t.data == key)       // When u reach tht node ,add the sum field of LST bcz they all are smaller
            {
                if(t.left != null) sum = sum + t.left.sum + t.data;        // so u do tht by adding sum field of left node + data of curr node
                else sum = sum + t.data;
                break;                      // u need to brk frm here bcz u r not moving anywhr so infinite loop caught u here !
            }

            else if (t.data < key)     // we ll go on RHS side , so add all elements tht r on left + the node data u r on currently
            {
                if(t.left != null) sum = sum + t.left.sum + t.data;
                else sum = sum + t.data;

                t = t.right;
            }

            else t = t.left;         // when u go left means current node's data is bigger , so do nothing
        }

        return sum;
    }

   // sumOfNums give the sum of all the numbers between x and y from the data set (excluding x nd y)

    public int sumOfNums(Node root,int x , int y)
    {
        return prefixSum(root,y) - prefixSum(root,x) - y ;     // p(y) gvs sum of all <= y , p(x) gvs sum of all <= x , and we deduct y
    }

    public int maxgap(Node root)
    {
        return root.max - root.min;
    }
}

