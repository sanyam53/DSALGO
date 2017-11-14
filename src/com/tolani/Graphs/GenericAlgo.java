package com.tolani.Graphs;

import java.util.*;

import com.tolani.Graphs.Graph2_v2.*;

import static com.tolani.Graphs.Color.*;

public class GenericAlgo {

    public static void main(String[] args)
    {
        Graph2_v2 g = new Graph2_v2(5,GraphType.DIRECTED);       // size 5 means vertices frm 0 to 4 : tc here

        g.addEdge(0,1);
        g.addEdge(0,3);
        g.addEdge(1,3);
        g.addEdge(3,4);
        g.addEdge(4,2);
        g.addEdge(4,3);
        g.addEdge(1,2);

        g.displayGraph();

        //BFS(g,0);
        System.out.println();      // if u call BFS first and then DFS then colors will change so take care dnt call both together here
        DFS(g,0);
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

            for(Integer x : u.adjList)
            {
                Vertex vObj = g.vertexList.get(x);

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
            u.setColor(RED);

            System.out.print(u.data + " ");

            for(Integer x : u.adjList)
            {
                Vertex vObj = g.vertexList.get(x);

                if(vObj.getColor() == GREEN)
                {
                    s.push(vObj);
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
