package com.tolani.Graphs;

import com.tolani.Graphs.Graph2_v2.*;
import static com.tolani.Graphs.Color.*;

public class p3_DFS_Recursive {

    public static void main(String[] args)
    {
        Graph2_v2 g = new Graph2_v2(5, GraphType.DIRECTED);       // size 5 means vertices frm 0 to 4 : tc here

        //non weighted graph example : use for dfs ,bfs

        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 2);
        g.addEdge(4, 3);
        g.addEdge(1, 2);

        g.displayGraph();

        DFSrec(g,0);
    }


    public static void DFSrec(Graph2_v2 g , int source)
    {
        Vertex src = g.vertexList.get(source);

        DFSrecursive(g,src);
    }

    public static void DFSrecursive(Graph2_v2 g , Vertex u)        // v is the vertex which U WNT to add onto the stack right now
    {
        u.setColor(BLACK);                // u mark this vertex as added to the DS and print it
        System.out.print(u.data + " ");    // u r printing it as u r popping it and visiting its neighbours : meanss u r exploring this vertex

        // so u will call func recursively for all the adjacent vertices if they are nt added to d DS yet
        for(AdjListNode x : u.adjList)
        {
            Vertex vObj = g.vertexList.get(x.getVertexId()) ;

          if(vObj.getColor() == GREEN)
            {
                vObj.piValue = u.data;
                DFSrecursive(g,vObj);
            }
        }

        // u ve finished visiting all of the neighbours so u r out of the loop
        u.setColor(RED);
    }

}
