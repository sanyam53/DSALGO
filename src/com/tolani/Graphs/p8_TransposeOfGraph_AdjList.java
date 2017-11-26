package com.tolani.Graphs;

import com.tolani.Graphs.Graph2_v2.*;

public class p8_TransposeOfGraph_AdjList {

    public static void main(String[] args)
    {

        Graph2_v2 g = new Graph2_v2(5, GraphType.DIRECTED);       // size 5 means vertices frm 0 to 4 : tc here
        g.addEdge(0, 1);
        g.addEdge(0, 3);
            g.addEdge(1, 2);     // see this two edges carefully if u change the order of adding here then dfs order will change !
        g.addEdge(3, 4);
        g.addEdge(4, 2);
        g.addEdge(4, 1);
            g.addEdge(1, 3);

        g.displayGraph();

        transposeOfGraph(g);
    }

    public static Graph2_v2 transposeOfGraph(Graph2_v2 g)
    {
        // creating an instance of a graph with same #of vertices and graph type
        Graph2_v2 gTranspose = new Graph2_v2(g.noOfVertices,g.gtype);

        // now we ll go thru the adj list of each vertex in G
        for(int i=0; i < g.noOfVertices ; i++)
        {
            Vertex uObj = g.vertexList.get(i);

            // adjList contains adjListNodes in whch we hv vertex id
            for(AdjListNode j : uObj.adjList )
            {
                int v = j.vertexId;

                Vertex vObj = gTranspose.vertexList.get(v);

                AdjListNode an = new AdjListNode(uObj.data);    // with 'u'
                vObj.adjList.add(an);      // adding into adjList of 'v'
            }

        }

        //printing gTranspose graph lets see
        System.out.println();
        gTranspose.displayGraph();
        return gTranspose;
    }
}
