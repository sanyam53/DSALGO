/*
*   Problem : Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

    assume tht thr r at least two nodes in a bstree
*
*   Approach 1 : in approach 1 which is impn below : think tht min diff regarding root is whr /
*                > it cn be a root's lst's rst's rightmost node or root's rst's lst's leftmost node
*                > this is the logic tht is used ! think
*
*    // but the simpler app. is to use inorder traversal and finding diff bw adjacent nodes thts it
*
*   Approach 2 : inorder traversal of BST gives u sorted seqn , so just find the abs diff bw
*               consecutive elements which will give u a min abs diff
*
*   O(n) time complexity.
* */

package com.tolani.LeetCode.BST.Easy;
import com.tolani.BST.BST;
import com.tolani.BST.BST.*;
public class p1_absdiff {

    public static void main(String[] args)
    {
        Solution1 s1 = new Solution1();
        BST bs = new BST();
        Node r = new Node(10);
        bs.root = r;
        bs.insert(r,5);bs.insert(bs.root,8);bs.insert(bs.root,11);
        // form a bst and pass the root below
        System.out.println(s1.getMinimumDifference(bs.root));
    }

}

class Solution1 {
    public int getMinimumDifference(Node root) {

        int mindiff = Integer.MAX_VALUE;

        int diff = 0;

        if(root.left != null)       // if lst is not null
        {
            if(root.left.right != null)     // if lst's rst isnt null
            {
                Node t = root.left.right;

                while(t.right != null)
                {
                    t = t.right;
                }

                diff = Math.abs(root.data - t.data);
            }
            else
            {
                diff = Math.abs(root.data - root.left.data);
            }

            if(diff < mindiff) mindiff = diff;

        }

        if(root.right != null)       // if rst is not null
        {
            if(root.right.left != null)     // if rst's lst isn't null
            {
                Node t = root.right.left;

                while(t.left != null)
                {
                    t = t.left;
                }

                diff = Math.abs(root.data - t.data);
            }
            else
            {diff = Math.abs(root.data - root.right.data);}

            if(diff < mindiff) mindiff = diff;
        }

        if(root.left != null )
        {
            int mindiff1 = getMinimumDifference(root.left);
            if(mindiff1 < mindiff) mindiff = mindiff1;
        }
        if(root.right != null)
        {
            int mindiff2 = getMinimumDifference(root.right);
            if(mindiff2 < mindiff) mindiff = mindiff2;
        }

        return mindiff;
    }
}