package com.tolani.SegTree;

import java.util.Arrays;

public class SegTree {

    int[] stArr;          // seg tree is nthng but an array of elements : full bin tree impn as an array
                         // root of the binary tree is always 0

    public int[] createSegTree(int[] ipArr, Operation operation)       // u will pass the size of an input array
    {
        // lets first decide the size of the seg tree : so if ip arr size is in pow of 2 then u create a seg tree of size (2*size -1)
        // if size isnt in pow of 2 then u create seg tree of size = 2*( nxt value tht is pow of 2 , aftr the size of ip arr tht is gvn) -1

        // why 2*n - 1 ? : bcz in full binary tree #of leaves are 'n' then # of internal nodes are "n-1" : so it is in total "2n-1"
        // ip array elemnts will be all leaves in our segment tree ok

        //int segTreeSize = nextPowerOf2(size);     // NOTE : we will see this approach later ok

        // another way of doing above is simply finding height and allcting  ( 2^ h+1 -1 ) memory

        // Allocate memory for segment tree
        //Height of segment tree
        int height = (int) (Math.ceil(Math.log(ipArr.length) / Math.log(2)));

        //Maximum size of segment tree
        int segTreeSize = 2 * (int) Math.pow(2, height) - 1;            // or  Math.pow(2 , height+1 ) -1  : same thing ok

        stArr = new int[segTreeSize];       // so we allctd memory for our segemnt tree ok

        buildSegTree(stArr,ipArr,0,ipArr.length-1,0,operation);

        return stArr;

    }

    private void buildSegTree(int segmentTree[], int input[], int low, int high,int pos, Operation operation){
                                                                // low nd high are of ip array , positin is of seg tree starts frm root : 0
        // if it is leaf then store the value drctly frm an array
        if(low == high)
        {
            segmentTree[pos] = input[low];
            return;
        }
        // as it is not a leaf we will do following :
        // u find mid and call recursively on l.c. and r.c as it is full binary tree
        else
        {
            int mid = (low + high)/2;       // to recur in two equal halves we found mid

            // recur for the left child
            buildSegTree(segmentTree,input,low,mid,2*pos+1, operation);       // see here positin is left child : whr we recur
            // recur for the right child
            buildSegTree(segmentTree,input,mid+1,high,2*pos+2, operation);    // here position is r.child : whr we recur

            // this is the internal node fr sure  : so we will perform an op we wnt and store the value in it
            segmentTree[pos] = operation.perform(segmentTree[2*pos+1], segmentTree[2*pos+2]);   // this is currnt pos : whr we do op. we wnt nd store
        }
    }

    public void displaySegTree(int[] segTree)
    {
        System.out.println(Arrays.toString(segTree));
    }

    // variant 1 : two main op in this variant are : 1. update an element , 2. rangeQuery(l,r) like sum of all elemnts frm l to r

    public int rangeQuery(int[] ipArr,int qleft ,int qright,Operation operation)
    {
        return rangeQuery2(ipArr,stArr,qleft,qright,0,ipArr.length-1,0,operation);

        // pos is 0 means we start frm the root of the segment tree
    }

    public int rangeQuery2(int[] ipArr,int[] stArr, int qleft , int qright , int lowIpArr, int highIpArr, int pos , Operation op)
    {
        // if total overlap : if we get readymade overlap like 0 to 4 , 0 to 1 etc is total overlap
        if(qleft <= lowIpArr && qright >= highIpArr){
            return stArr[pos];
        }

        // if no overlap
        if(qleft > highIpArr || qright < lowIpArr){
            return 0;      // in case of sum we return 0 , in case of min we return int.max value
        }

        // partial overlap so we wll do recursion on left n right : means bit on left and bit on right : as ex 0 to 3 , 1 to 4 like this
        int mid = (lowIpArr+highIpArr)/2;

        int val1 = rangeQuery2(ipArr,stArr,qleft,qright,lowIpArr,mid,2*pos + 1,op);
        int val2 = rangeQuery2(ipArr,stArr,qleft,qright,mid+1,highIpArr,2*pos+2,op);

        return op.perform(val1,val2);
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
}


interface Operation{
    int perform(int a, int b);
}

class SumOperation implements Operation{

    @Override
    public int perform(int a, int b) {
        return a+b;
    }
}

class MinOperation implements Operation{
    @Override
    public int perform(int a, int b){
        return Math.min(a,b);
    }
}


