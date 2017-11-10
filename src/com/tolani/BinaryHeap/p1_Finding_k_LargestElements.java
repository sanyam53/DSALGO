/*
*
*   find k largest elements frm the given list of 'n elements'
*
*   > we cn simply think of creating heap of n elemnts which tks O(n) time and then extracting 'k largest elemetnts ' which tks O(k * logn) time
*
*   > but wht if ' n = 10^9 ' : then u cant maintain a heap (means array) of tht size , so think of space efficient approach !
*
* */




package com.tolani.BinaryHeap;

public class p1_Finding_k_LargestElements {

    public static void main(String[] args)
    {
        int[] ipArr = {1,2,3,4,5,6,7,8,9,10};
        int k = 3;
        app1(ipArr,k);
    }

    /*
    *   Approach 1 : we will build a Min heap of first 'k' elements
    *                we will traverse frm k+1 elements to 'n' elemnts and we compare each element with the root min from the heap one by one
    *                if elemnt frm ipArr is larger than we will extract min frm the minheap and insert tht element into the heap
    *
    *                this is how we will get 'k largest elements at last in our heap
    *
    *       t.c. is O(n * 2 * logk)  , and s.c. is O(k) : so this is space efficient
    * */

    public static void app1(int[] ipArr,int k)
    {
        int[] a = new int[k];

        for(int i=0 ; i < k ;i++)
        {
            a[i] = ipArr[i];
        }

        int ptr = k;  // pointing to the loc aftr the first 'k' elements

        MyMinHeap minHeap = MyMinHeap.buildHeap(a);      // max heap of first 'k' elements built

        while(ptr < ipArr.length) {

            if (minHeap.getMin() < ipArr[ptr]) {
                minHeap.deleteMin();
                minHeap.insert(ipArr[ptr]);
            }

            ptr++;
        }

        minHeap.displayHeap();
    }
}
