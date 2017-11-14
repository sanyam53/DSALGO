
/*
*   this is essentially a graph with a nested structure of a vertex or a node ,
*   the structure of a node contains integer data and a color < this color is useful while implementing BFS, DFS, Dijkstra, Min. spanning tree
* */


package com.tolani.Graphs;
import java.util.*;

public class Graph2_v2 {

    int noOfVertices;                               // 'n' is the no of vertices in the graph
    Map<Integer,Vertex> vertexList;                 // we are creating a hashmap of vertices in the graph
    GraphType gtype;

    public static class Vertex
    {
        int data;
        ArrayList<Integer> adjList;       // this will contain integer of the vertices that are connected to this vertex object
        Color color;      // Color is an enum type
        int piValue;

        Vertex(int data)
        {
            this.data = data;
            this.color = Color.GREEN;       // Initalization with green color
            this.piValue = -1;

            adjList = new ArrayList<>();     // intializing
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

        vertexList = new HashMap<>(noOfVertices);

        for(int i=0 ; i < noOfVertices ; i++)
        {
            Vertex v = new Vertex(i);       // we r creating a vertex object with indices
            vertexList.put(i,v);            // now we are adding it to the hashmap (vertexList)
        }
    }

    public void addEdge(int u , int v)       // we ve passed a graph obj in case u working with multiple graph objects
    {
        if(this.gtype == GraphType.DIRECTED)
        {
            Vertex uObj =  vertexList.get(u);    // this will return a corresponding vertex object
            uObj.adjList.add(v);                // now u access the adjacency list of tht vertex obj and add the correspndng edge in it
        }

        else     // if graph is undirected u add edges twice
        {
           Vertex uObj = vertexList.get(u);
           uObj.adjList.add(v);

           Vertex vObj = vertexList.get(v);
           vObj.adjList.add(u);
        }

    }

    // note : to travese through the LinkedList objects in java , we cn use for each loop OR u cn use ListIterator
    // for each loop is more easier.

    public void displayGraph()
    {
       for(Vertex v : vertexList.values())
       {
           System.out.print(v.data + " => ");

            Iterator i = v.adjList.iterator();

            while(i.hasNext())
            {
                System.out.print(i.next() + " -> ");
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