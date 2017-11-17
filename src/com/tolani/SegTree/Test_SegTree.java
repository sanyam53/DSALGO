package com.tolani.SegTree;

public class Test_SegTree {

    public static void main(String[] args)
    {
        int[] ipArr = new int[]{0,2,3,5};
        Operation sumop = new SumOperation();

        // so u pass an ipArr and the operation on which u wnt to define a seg tree

        SegTree st = new SegTree(ipArr,sumop);

        st.displaySegTree();

        System.out.println(st.rangeQuery(ipArr,0,2,sumop));
    }
}
