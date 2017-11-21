package com.tolani.SegTree;

import java.util.Arrays;

public class Test_SegTree {

    public static void main(String[] args)
    {
        int[] ipArr = new int[]{0,2,3,5};
        Operation sumop = new SumOperation();

        // so u pass an ipArr and the operation on which u wnt to define a seg tree
/*
        SegTree st = new SegTree(ipArr,sumop,false);

        st.displaySegTree();

        System.out.println(st.rangeQuery(ipArr,0,2,sumop));

        st.updateQuery(ipArr,0,9,sumop);

        st.displaySegTree();

        System.out.println(Arrays.toString(ipArr));
*/
        // lazy propagation testing

        SegTree st2 = new SegTree(ipArr,sumop,true);

        st2.rangeUpdateLazy(ipArr,0,2,10,sumop);

        st2.displaySegTree();
        st2.displayLazyTree();

        int ans = st2.rangeQueryLazy(ipArr,1,1,sumop);
        System.out.println(ans);

        st2.displaySegTree();
        st2.displayLazyTree();
    }
}
