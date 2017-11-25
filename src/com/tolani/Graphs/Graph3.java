package com.tolani.Graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph3
{

    int noOfVertices;

    GraphType gt;           // depending on graph type we will fetch an edge frm edgeList
    IsGraphWeighted gw;

    public List<Edge> allEdges;   // array list of all the edges , whch store an edge object

    public static class Edge
    {
        private int u;
        private int v;

        private int edgeWeight;

        Edge(int u , int v)
        {
            this.u = u;
            this.v = v;
            edgeWeight = -1;
        }

        Edge(int u , int v , int weight)
        {
            this.u = u;
            this.v = v;
            this.edgeWeight = weight;
        }

        // getter and setter methods
         public int getU() {
            return u;
        }

        public void setU(int u) {
            this.u = u;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getEdgeWeight() {
            return edgeWeight;
        }

        public void setEdgeWeight(int edgeWeight) {
            this.edgeWeight = edgeWeight;
        }


    }

    // public contructor
    public Graph3(int size,GraphType gt,IsGraphWeighted gw)
    {
        noOfVertices = size;
        this.gt = gt;
        this.gw = gw;

        allEdges = new ArrayList<Edge>();
    }

    public int getN() {
        return noOfVertices;
    }


    // utility methods
    public void addEdge(int u , int v)
    {
        Edge e = new Edge(u,v);

        allEdges.add(e);
    }

    public void addWeightedEdge(int u , int v , int weight)
    {
        Edge e = new Edge(u,v,weight);

        allEdges.add(e);
    }

    public Edge getEdge(int u , int v)
    {
        if(u == v) return null;

        if(this.gt == GraphType.DIRECTED)
        {
            for(Edge e : allEdges)        // u traverse thru all the edges and see
            {
                if(e.getU() == u && e.getV() == v)
                {
                    return e;
                }
            }
        }

        else    // this means graph is undrctd so edge could be added as u,v or v,u
        {
            for(Edge e : allEdges)
            {
                if((e.getU() == u && e.getU() == v)  || (e.getV() == u && e.getU() == v))          // 1st vertex cn be 'u' or 'v'
                {
                    return e;
                }
            }
        }

        return null;     // if edge not found then return null;
    }

    public void displayEdgeList()
    {
        for(Edge e : allEdges)
        {
            System.out.print("(" + e.getU() + "," + e.getV() + ") -> ");
        }
    }
}

