package com.tolani.BinaryHeap;

public class Test_MinHeap {

    public static void main(String[] args)
    {

        MyMinHeap heap = new MyMinHeap(10);

        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        heap.displayHeap();

        heap.insert(2);
        heap.insert(15);
        heap.insert(1);

        //
        heap.insert(99);
        heap.insert(35);
        heap.insert(22);
        heap.insert(21);
       // heap.insert(222);       : this will give heap overflow
        //


        heap.displayHeap();
        System.out.println("current heap size " + heap.heap_size);      // heap size is 10 means from indices 0 to 9 thr r elements in heap
        int ans = heap.deleteMin();
        System.out.println("min elemnt we extracted is " + ans);
        heap.displayHeap();
        System.out.println("heap size aftr extracting minimum " + heap.heap_size);

        heap.changeKey(3,8);
        heap.displayHeap();

        heap.delete(2);
        heap.displayHeap();
        System.out.println("heapsize " + heap.heap_size);


        MyMinHeap heapobj = MyMinHeap.buildHeap(new int[]{1,5,7,0,-2,100,50,-8});

        heapobj.displayHeap();

        System.out.println("heap size is : " + heapobj.heap_size);
    }
}
