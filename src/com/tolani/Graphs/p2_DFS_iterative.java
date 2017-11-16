package com.tolani.Graphs;

import java.util.*;
import com.tolani.Graphs.Graph2_v2.*;
import static com.tolani.Graphs.Color.*;

public class p2_DFS_iterative {

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

        DFS(g, 0);
    }

    public static void DFS(Graph2_v2 g, int source) {

        Stack<Graph2_v2.Vertex> s = new Stack<>();

        // color and pi value are intialized in the nodes itslf , so we dnt hv to do it here

        Vertex srcV = g.vertexList.get(source);
        srcV.piValue = source;

        s.push(srcV);                 // so u first add source node to the stack and u set its color to be black
        srcV.setColor(Color.BLACK);

        visitDFS(g, s, srcV);          // call visit function on the source vertex

        for (int i = 0; i < g.noOfVertices; i++) {
            Collection<Vertex> c = g.vertexList.values();

            Iterator itr = c.iterator();

            while (itr.hasNext()) {
                Vertex v = (Vertex) itr.next();

                if (v.getColor() == GREEN) {
                    visitDFS(g, s, v);
                }
            }
        }
    }

    public static void visitDFS(Graph2_v2 g, Stack s, Vertex v)
    {
        while(!s.empty())       // s.empty() returns true if stack contains no items
        {
            Vertex u = (Vertex) s.pop();      // delete a vertex frm stack AND set its color as red one

            if(u.getColor() == BLACK) {
                System.out.print(u.data + " ");        // u r printing this bcz u r gonna explore this vertex next : means explrng neighbrs of it
            }

            u.setColor(RED);

            for(AdjListNode x : u.adjList)
            {
                Vertex vObj = g.vertexList.get(x.getVertexId());

                if(vObj.getColor() == GREEN)
                {
                    s.push(vObj);
                    vObj.setColor(BLACK);
                    vObj.piValue = u.data;     // bcz 'v' is added to the queue bcz of 'u' , so we set its pi val to be 'u'
                }

                else if(vObj.getColor() == BLACK)
                {
                    // so we didnt do anything in case of queue here bcz if elemnt is already thr in ds , and in case of queue if u add it again
                    // then it wont help you , but in case of stack priority will change on adding again , so we are adding an element again

                    s.push(vObj);
                    vObj.setColor(BLACK);
                    vObj.piValue = u.data;
                }
            }

        }
    }

}
