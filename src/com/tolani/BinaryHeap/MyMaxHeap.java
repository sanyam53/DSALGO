package com.tolani.BinaryHeap;

import java.util.Arrays;

public class MyMaxHeap {


    int heap_size;   // current size of the heap
    int capacity;     // capacity of the heap

    int heap[];        // array to hold the elemnts : we r implmntng heap using array of type ints here specifically

    MyMaxHeap(int capacity) {
        heap_size = 0;                // intialize heapsize ,capacity and the array
        this.capacity = capacity;
        heap = new int[capacity];
    }

    // if indexing is starting frm 0 then for the ith node : parent = i-1/2 , left child = 2i+1 , right child = 2i+2

    public int getParent(int i) {
        return (i - 1) / 2;
    }

    public int getLeftChild(int i) {
        return (2 * i + 1);
    }

    public int getRightChild(int i) {
        return (2 * i + 2);
    }

    public int getMax() {
        return heap[0];        // root of the tree means elemnt at the 0th index in the array
    }

    // two fundamental functions of heap : topDownHeapify & bottomUpHeapify
    public void topDownHeapify(int i)         // we will pass an index of a node on whch we wnt to perform top down heapify
    {
        int lc = getLeftChild(i);
        int rc = getRightChild(i);

        while (rc < heap_size)       // tc : we put this cond bcz rc is the last one in the tree so if it exceeds the heapsize thn outofbounds
        {
            int j = Math.max(heap[lc],heap[rc]);      // finding minimum frm lc and rc and call it 'j'

            // but u wnt an index not an element ok
            if(j == heap[lc]) j= lc;
            else j = rc;

            if (heap[i] < heap[j])       // parent node is bigger than one of the child node then swap it bcz its min heap prprty violated
            {
                swap(i, j);

                i = j;
                lc = getLeftChild(i);
                rc = getRightChild(i);

            } else break;           // no swapping happns means min heap property is satisfying already: so break
        }

        if (lc == (heap_size - 1) && heap[lc] > heap[i])     // this is the one case u need to handle which is not handled by abv logic
        {
            swap(lc, i);
        }

        // two things to note : bcz we ve gvn loop conditin by chckng on the r.c. so l.c can be thr and if it is lesser than the root node
        // then u ve to swap them : so we handled it at last by chckng

    }

    public void bottomUpHeapify(int i) {
        int parent = getParent(i);

        while (i > 0 && heap[i] > heap[parent])       // last node to be compared with parent is the node just below the parent means i > 0
        {
            swap(i, parent);
            i = parent;
            parent = getParent(i);
        }
    }

    public void swap(int a, int b) {
        int tempdata = heap[a];
        heap[a] = heap[b];
        heap[b] = tempdata;
    }

    public void insert(int data) {
        if (heap_size == capacity) {
            System.out.println("heap overflow");
            return;                                    // to terminate program here itself just return
        }

        heap[heap_size] = data;          // heap_size is a ptr whr u insert in the heap , nd u incrmnt it to next location aftr insertion

        bottomUpHeapify(heap_size);

        heap_size++;

    }

    public int deleteMax()
    {
        if(heap_size == 0) {System.out.println("underflow"); return -1;}

        int min = heap[0];
        swap(0,heap_size-1);     // we wrote heapsize-1 bcz arr indexing starts frm 0
        heap_size--;                // decrease the heapsize as u r remving the elemnt frm the heap
        topDownHeapify(0);         // call the topDownHeapify on root node

        return min;
    }

    public int delete(int index)     // delete an arbitrary node
    {
        // if u wnt to delete an arbitrary node then u ve to search for it frst then to delete it and to call heapify on it : this is O(n) overal
        // instead an optimized clever approach is to decrease a key of it to (min-1) and deleting minimum : which is logn time

        changeKey(index,getMax()+1);
        int ans = deleteMax();
        return ans;
    }

    public void changeKey(int index , int newData)
    {
        int oldData = heap[index];
        heap[index] = newData;

        if(newData > oldData)
        {
            bottomUpHeapify(index);
        }
        else if(newData < oldData)        // decreasekey : if key is decreased then in max heap it affects on the below part so u call top down
        {
            topDownHeapify(index);
        }
        else
        {
            return;
        }
    }

    public static MyMaxHeap buildHeap(int[] arr)
    {
        MyMaxHeap heapObj = new MyMaxHeap(arr.length);

        for(int i=0 ; i < arr.length; i++)
        {
            heapObj.heap[heapObj.heap_size++] = arr[i];
        }

       // heapObj.heap_size = arr.length ;  // bcz heap_size is an index in heap so starting frm 0 to 7 :but size we r considering is 8 ok

        for(int i = (arr.length/2) -1 ; i >= 0 ; i--)        // internal nodes are frm 1 to n/2 & leaves r frm (n/2+1) to n
        {
            heapObj.topDownHeapify(i);          // u hv to pass an array here to perfrom topdown on tht array :::
        }

        return heapObj;
    }

    public void displayHeap() {
        System.out.println(Arrays.toString(heap));
    }

}
