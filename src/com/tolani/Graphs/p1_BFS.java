package com.tolani.Graphs;

import java.util.*;
import com.tolani.Graphs.Graph2_v2.*;
import static com.tolani.Graphs.Color.*;

public class p1_BFS {

    public static void main(String[] args) {
        Graph2_v2 g = new Graph2_v2(5, GraphType.DIRECTED);       // size 5 means vertices frm 0 to 4 : tc here

        //non weighted graph example : use for dfs ,bfs

        g.addEdge(0,1);
        g.addEdge(0,3);
        g.addEdge(1,3);
        g.addEdge(3,4);
        g.addEdge(4,2);
        g.addEdge(4,3);
        g.addEdge(1,2);

        g.displayGraph();

        BFS(g,0);
    }

    public static void BFS(Graph2_v2 g ,int source)
    {
        // Java Note : Queue is an interface so u cant instantiate a queue , so it is usually implemented as a priority queue or a linkedlist
        // FIFO order , here queue is internally implemented using a linked list in 1st case

        Queue<Graph2_v2.Vertex> q = new LinkedList<Vertex>();

        // color and pi value are intialized in the nodes itslf , so we dnt hv to do it here

        Vertex srcV = g.vertexList.get(source);
        srcV.piValue = source;

        q.add(srcV);                  // so u first add source node to the queue and u set its color to be black
        srcV.setColor(Color.BLACK);

        visitBFS(g,q,srcV);          // call visit function on the source vertex

        for(int i=0 ; i < g.noOfVertices ; i++)
        {
            Collection<Vertex> c = g.vertexList.values();

            Iterator itr = c.iterator();

            while(itr.hasNext())
            {
                Vertex v = (Vertex)itr.next();

                if(v.getColor() == GREEN)
                {
                    visitBFS(g,q,v);
                }
            }
        }


    }

    public static void visitBFS(Graph2_v2 g, Queue q, Vertex v)
    {
        while(!q.isEmpty())
        {
            Vertex u = (Vertex) q.remove();      // delete a vertex frm queue AND set its color as red one
            u.setColor(RED);

            System.out.print(u.data + " ");

            for(AdjListNode x : u.adjList)
            {
                Vertex vObj = g.vertexList.get(x.getVertexId());

                if(vObj.getColor() == GREEN)
                {
                    q.add(vObj);
                    vObj.setColor(BLACK);
                    vObj.piValue = u.data;     // bcz 'v' is added to the queue bcz of 'u' , so we set its pi val to be 'u'
                }

                else if(vObj.getColor() == BLACK)
                {
                    // WE WILL DO SOMETHING HERE
                }
            }

        }
    }

}
