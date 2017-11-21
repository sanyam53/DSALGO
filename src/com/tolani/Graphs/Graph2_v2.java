
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
        ArrayList<AdjListNode> adjList;       // this will contain integer of the vertices that are connected to this vertex object
        Color color;      // Color is an enum type
        int piValue;

        int priority;

        // new parameters for dfs application

        public int getDiscoveryTime() {
            return discoveryTime;
        }

        public void setDiscoveryTime(int discoveryTime) {
            this.discoveryTime = discoveryTime;
        }

        public int getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(int finishTime) {
            this.finishTime = finishTime;
        }

        int discoveryTime;
        int finishTime;


        Vertex(int data)
        {
            this.data = data;
            this.color = Color.GREEN;       // Initalization with green color
            this.piValue = -1;

            adjList = new ArrayList<AdjListNode>();     // intializing

            this.priority = Integer.MAX_VALUE;

            discoveryTime = finishTime = -1;
        }

        public void setColor(Color c)
        {
            this.color = c;
        }

        public Color getColor()
        {
            return this.color;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }


    /*  the thing is in case of the weighted graph when we explore the adjacent vertices , we also want the weight of the correspndng edge
     *   now , thr r some options we can try are : 1. 2d array which contains weight of edges bw (u,v) : this is O(v^2 space) not a good idea
     *                                             2. we can create a class EDGE , and we can create maintain list of all edges : O(2*E)
     *                                             3. we can maintain adjacency list of each vertex which contains Edge Objects : O(2*E)
     *                                             4. for now we can modify the adjacency list node tht is in the adjList of each vertex
     *                                                 , we will create one class adjListNode , and we wr befr storing only an integer reltd
     *                                                 to the vertex we wnt to fetch , but now we will add one more parameter weight into it
     *                                                 this will save some space and this wont tk any search time for an edge weight (u,v)
     *                                                 we will hv it in O(1) : simultaneously
    * */

    public static class AdjListNode
    {
        int vertexId;
        int weight;

        AdjListNode(int vid)
        {
            vertexId = vid;
            weight = -1;         // if it is a non weighted edges then u use this constructor and u define weight as -1 here
        }

        AdjListNode(int vid , int w)
        {
            this.vertexId = vid;
            this.weight = w;
        }

        public int getVertexId()
        {
            return this.vertexId;
        }

        public int getWeight()
        {
            return this.weight;
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

    // below addEdge method is for non weighted graph

    public void addEdge(int u , int v)       // we ve passed a graph obj in case u working with multiple graph objects
    {
        if(this.gtype == GraphType.DIRECTED)
        {
            Vertex uObj =  vertexList.get(u);    // this will return a corresponding vertex object
            AdjListNode adjNode = new AdjListNode(v);
            uObj.adjList.add(adjNode);                // now u access the adjacency list of tht vertex obj and add the correspndng edge in it
        }

        else     // if graph is undirected u add edges twice
        {
           Vertex uObj = vertexList.get(u);
            AdjListNode adjNode = new AdjListNode(v);
           uObj.adjList.add(adjNode);

           Vertex vObj = vertexList.get(v);
           adjNode = new AdjListNode(u);
           vObj.adjList.add(adjNode);
        }

    }

    public void addWeightedEdge(int u, int v, int weight)       // we ve passed a graph obj in case u working with multiple graph objects
    {
        if(this.gtype == GraphType.DIRECTED)
        {
            Vertex uObj =  vertexList.get(u);    // this will return a corresponding vertex object

            AdjListNode adjNode = new AdjListNode(v,weight);       // 'v' is an int represntng vertex 'v' , and weight is (u,v) edge weight

            uObj.adjList.add(adjNode);           // now u access the adjacency list of tht vertex obj and add the correspndng edge in it
        }

        else     // if graph is undirected u add edges twice
        {
           Vertex uObj = vertexList.get(u);
           AdjListNode adjNode = new AdjListNode(v,weight);
           uObj.adjList.add(adjNode);

           Vertex vObj = vertexList.get(v);
           adjNode = new AdjListNode(u,weight);     // u cn use same reference
           vObj.adjList.add(adjNode);
        }

    }

    // note : to travese through the LinkedList objects in java , we cn use for each loop OR u cn use ListIterator
    // for each loop is more easier.

    public void displayGraph()
    {
       for(Vertex v : vertexList.values())         // retrieving all the vertices one by one
       {
           System.out.print(v.data + " => ");

            Iterator i = v.adjList.iterator();          // retrieving adjacency list of the one vertex

            while(i.hasNext())
            {
                AdjListNode n = (AdjListNode) i.next();      // printing the adjList Object

                if(n.weight == -1)
                {
                    System.out.print(n.getVertexId() + " -> ");
                }
                else
                {
                    System.out.print("v:" + n.getVertexId() + "|w:" + n.getWeight() + "  ->  ");
                }
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