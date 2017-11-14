package com.tolani.SegTree;

public class Test_SegTree {

    public static void main(String[] args)
    {
        SegTree st = new SegTree();

        Operation sumop = new SumOperation();

        int[] iparr = new int[]{1,2,3,4};

        st.createSegTree(iparr , sumop);

        st.displaySegTree(st.stArr);

        System.out.println(st.rangeQuery(iparr,2,3,sumop));
    }
}
