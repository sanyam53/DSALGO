
/*
*   this is essentially a graph with a nested structure of a vertex or a node ,
*   the structure of a node contains integer data and a color < this color is useful while implementing BFS, DFS, Dijkstra, Min. spanning tree
* */


package com.tolani.Graphs;
import java.util.LinkedList;

public class Graph2_v2 {

    int noOfVertices;                                   // 'n' is the no of vertices in the graph
    LinkedList<Vertex> adjList[];          // array of linked lists : which holds vertices as nodes : Vertex is a class defined by us
    GraphType gtype;

    public static class Vertex
    {
        int data;
        Vertex next;
        Color color;      // Color is an enum type
        int piValue;

        Vertex(int data)
        {
            this.data = data;
            this.color = Color.GREEN;       // Initalization with green color
            this.piValue = -1;
        }

        public void setColor(Color c)
        {
            this.color = c;
        }

        public Color getColor()
        {
            return this.color;
        }
    }

    Graph2_v2(int size,GraphType type)
    {
        this.noOfVertices = size;
        this.gtype = type;

        adjList = new LinkedList[noOfVertices];

        for(int i=0 ; i < noOfVertices ; i++)
        {
            adjList[i] = new LinkedList<Vertex>();      // creating a linked list at each vertex
        }
    }

    public void addEdge(int u , int v)       // we ve passed a graph obj in case u working with multiple graph objects
    {
        if(this.gtype == GraphType.DIRECTED)
        {
            Vertex vObj = new Vertex(v);

            adjList[u].addFirst(vObj);
        }

        else     // if graph is undirected u add edges twice
        {
            Vertex vObj = new Vertex(v);
            Vertex uObj = new Vertex(u);

            this.adjList[u].addFirst(vObj);      // we are adding at the head of the linked list

            // if it is undirected graph then u ve to add an edge to the verte v's linked list also
            this.adjList[v].addFirst(uObj);
        }

    }

    // note : to travese through the LinkedList objects in java , we cn use for each loop OR u cn use ListIterator
    // for each loop is more easier.

    public void displayGraph()
    {
        for(int i =0 ; i < this.noOfVertices; i++)
        {
            System.out.print("vertex " + i + "=> ");

            for(Vertex x : this.adjList[i])
            {
                System.out.print( x.data + " -> ");
            }

            System.out.println();
        }
    }

}

enum Color
{
    GREEN,BLACK,RED;
}

enum GraphType
{
    DIRECTED, UNDIRECTED;
}