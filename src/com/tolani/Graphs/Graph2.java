package com.tolani.Graphs;

import java.util.LinkedList;

public class Graph2 {

    int n;                                   // 'n' is the no of vertices in the graph
    LinkedList<Integer> adjList[];          // array of linked lists

    Graph2(int size)
    {

        this.n = size;
        adjList = new LinkedList[n];

        for(int i=0 ; i < n ; i++)
        {
            adjList[i] = new LinkedList<>();      // creating a linked list at each vertex
        }
    }

    public void addEdge(int u , int v)       // we ve passed a graph obj in case u working with multiple graph objects
    {
        this.adjList[u].addFirst(v);      // we are adding at the head of the linked list

        // if it is undirected graph then u ve to add an edge to the verte v's linked list also
        this.adjList[v].addFirst(u);
    }

    // note : to travese through the LinkedList objects in java , we cn use for each loop OR u cn use ListIterator
    // for each loop is more easier.

    public void displayGraph()
    {
        for(int i =0 ; i < this.n; i++)
        {
            System.out.print("vertex " + i + "=> ");

           for(Integer x : this.adjList[i])
           {
               System.out.print( x + " -> ");
           }

           System.out.println();
        }
    }

}