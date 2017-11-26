package com.tolani.Graphs;


import java.util.*;

import com.tolani.Graphs.Graph2_v2.*;
import static com.tolani.Graphs.Color.*;

public class p6_Topological_Sorting {

    static int t =1;

    static LinkedList<Vertex> topoSortList = new LinkedList<Vertex>();

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

        DFSrec(g,0);

        System.out.println();

        for(Vertex v : g.vertexList.values())
        {
            System.out.print(v.piValue + " : " + v.data + " : "  + v.getDiscoveryTime() + "/" + v.getFinishTime());
            System.out.println();
        }

        // printing the list we made which ll give us the topo sorted list

        Iterator i = topoSortList.iterator();

        while(i.hasNext())
        {
            Vertex v = (Vertex) i.next();
            System.out.print(v.data + ":" + v.getFinishTime() + " , ");
        }

    }

    public static void DFSrec(Graph2_v2 g , int source)
    {
        Vertex src = g.vertexList.get(source);

        DFSrecursive(g,src);
    }

    public static void DFSrecursive(Graph2_v2 g , Graph2_v2.Vertex u)
    {
        u.setColor(BLACK);
        System.out.print(u.data + " ");

        // Setting the discovery time as u are gonna explore this node
        u.setDiscoveryTime(t++);

        for(AdjListNode x : u.adjList)
        {
            Vertex vObj = g.vertexList.get(x.getVertexId()) ;

          if(vObj.getColor() == GREEN)
            {
                vObj.piValue = u.data;
                DFSrecursive(g,vObj);
            }
        }

        u.setColor(RED);

        // setting the finish time as u finished visiting all ur neighbours
        u.setFinishTime(t++);

        // so we need a list which is in the decreasing order of the FINISH TIME : whch is essentially a topological sorting

        topoSortList.addFirst(u);
    }


}
