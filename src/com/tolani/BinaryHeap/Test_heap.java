package com.tolani.BinaryHeap;

import com.tolani.Tree_Tree.MyTree;

public class Test_heap {

    public static void main(String[] args)
    {
        MyHeap heap = new MyHeap(10);

        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        heap.displayHeap();

        heap.insert(2);
        heap.insert(15);
        heap.insert(1);

        heap.displayHeap();
        System.out.println("current heap size " + heap.heap_size);
        int ans = heap.deleteMin();
        System.out.println("min elemnt we extracted is " + ans);
        heap.displayHeap();
        System.out.println("heap size aftr extracting minimum " + heap.heap_size);
    }
}
