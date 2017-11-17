package com.tolani.SegTree;

import java.util.Arrays;

public class SegTree {

    int[] segArr;          // seg tree is nthng but an array of elements : full bin tree impn as an array
                            // root of the binary tree is always 0
    int segTreeRangeLow;
    int segTreeRangeHigh;

    /*
       // lets first decide the size of the seg tree : so if ip arr size is in pow of 2 then u create a seg tree of size (2*size -1)
        // if size isnt in pow of 2 then u create seg tree of size = 2*( nxt value tht is pow of 2 , aftr the size of ip arr tht is gvn) -1

        // why 2*n - 1 ? : bcz in full binary tree #of leaves are 'n' then # of internal nodes are "n-1" : so it is in total "2n-1"
        // ip array elemnts will be all leaves in our segment tree ok

        //int segTreeSize = nextPowerOf2(size);     // NOTE : we will see this approach later ok

        // another way of doing above is simply finding height and allcting  ( 2^ h+1 -1 ) memory

        // Allocate memory for segment tree
        //Height of segment tree*/

// constructor in which we allocate the size and call buildSegTree method

    SegTree(int[] ipArr, Operation operation)       // u will pass the ip arr & and object of an Operation by which segTree is defined
    {
        // height of a full binary tree in terms of no of leaves 'N' : which are elements of an ip arr

        int height = (int) (Math.ceil(Math.log(ipArr.length) / Math.log(2)));      // height = log(base2)N =  log(baseE)N / log(baseE)2

        // so now segTree size will be  : 2^(h+1) - 1
        int segTreeSize = 2 * (int) Math.pow(2, height) - 1;            // or  Math.pow(2 , height+1 ) -1  : same thing ok

        segArr = new int[segTreeSize];       // so we allctd memory for our segemnt tree ok

        buildSegTree(segArr,0,operation,ipArr,0,ipArr.length-1);
        // this is called on full range of ip arr frm ( 0 to N-1 ) , and index of a root node from where we will divide the range and go to l.c. , r.c

        // storing the interval tht segemnt tree representing of an ip array
        segTreeRangeLow =0;
        segTreeRangeHigh = (ipArr.length - 1);
    }

    // method for building a segment tree

    private void buildSegTree(int segArr[],int pos,Operation operation, int ipArr[], int low, int high)
    {

        if(low == high)             // if it is leaf then store the value drctly frm an array
        {
            segArr[pos] = ipArr[low];
            return;
        }
        else
        {
            // divide the interval in two halves for next level , and recursively call on both intervals with left child nd right child

            int mid = (low + high)/2;

            // recur for the left child
            buildSegTree(segArr,2*pos + 1, operation ,ipArr,low,mid);       // see here positin is left child : whr we recur
            // recur for the right child
            buildSegTree(segArr,2*pos+2 , operation ,ipArr,mid+1,high);    // here position is r.child : whr we recur

            // this is the internal node (merge here) : so we will perform an op we wnt and store the value in it : MERGING operation
            segArr[pos] = operation.perform(segArr[2*pos+1], segArr[2*pos+2]);   // this is currnt pos : whr we do op. we wnt nd store
        }
    }

    public void displaySegTree()
    {
        System.out.println(Arrays.toString(segArr));
    }

    // variant 1 : two main op in this variant are : 1. update an element , 2. rangeQuery(l,r) like sum of all elemnts frm l to r

    // Query(arr,low,high) : so we can calculate range tht seg tree represnt frm iparr size or we can store in our data strucutre

    public int rangeQuery(int[] ipArr, int qlow ,int qhigh ,Operation operation)
    {
        // we dnt need an ip array now , we ve segTree ranges and query ranges : so we can work on our seg tree array directly

        return rangeQuery2(qlow,qhigh,this.segTreeRangeLow,this.segTreeRangeHigh,0,operation);

    }

    public int rangeQuery2(int qlow , int qhigh ,int segRangeLow, int segRangeHigh, int pos , Operation op)
    {
        // if total overlap : means segTreeNode's range is completely inside the Query Range

        if(qlow <= segRangeLow && qhigh >= segRangeHigh){
            return segArr[pos];
        }

        // if no overlap : means segTreeNode's range is completely outside the Query Range

        if(qlow > segRangeHigh || qhigh < segRangeLow){
            return op.returnValue();      // in case of sum we return 0 , in case of min we return int.max value
        }

        // partial overlap : means segTreeNode's range is paritally inside and/or partially outside

        else
        {
            int mid = (segRangeLow + segRangeHigh)/2;

            int val1 = rangeQuery2(qlow,qhigh ,segRangeLow,mid ,2*pos + 1,op);
            int val2 = rangeQuery2(qlow,qhigh ,mid+1,segRangeHigh ,2*pos+2,op);

            return op.perform(val1,val2);
        }
    }


}


interface Operation{
    int perform(int a, int b);
    int returnValue();
}

class SumOperation implements Operation{
    @Override
    public int perform(int a, int b) {
        return a+b;
    }

    public int returnValue()
    {
        return 0;
    }
}

class MinOperation implements Operation{
    @Override
    public int perform(int a, int b){
        return Math.min(a,b);
    }

    public int returnValue()
    {
        return Integer.MAX_VALUE;
    }
}


/*
    left to learn

    public int nextPowerOf2(int num){
        if(num ==0){
            return 1;
        }
        if(num > 0 && (num & (num-1)) == 0){
            return num;
        }
        while((num & (num-1)) > 0){
            num = num & (num-1);
        }
        return num<<1;
    }
*/
