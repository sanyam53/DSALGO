package com.tolani.Graphs;

import java.util.*;

import com.tolani.Graphs.Graph2_v2.*;

import static com.tolani.Graphs.Color.*;

public class GenericAlgo {

    public static void main(String[] args) {
        Graph2_v2 g = new Graph2_v2(5, GraphType.DIRECTED);       // size 5 means vertices frm 0 to 4 : tc here
/*
        non weighted graph example : use for dfs ,bfs

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
        //DFS(g,0);

        //DFSrec(g,0);

*/

        // weighted graph example

        g.addWeightedEdge(0, 1, 3);
        g.addWeightedEdge(0, 3, 6);
        g.addWeightedEdge(1, 3, 2);
        g.addWeightedEdge(1, 2, 9);
        g.addWeightedEdge(3, 4, 4);
        g.addWeightedEdge(4, 1, 2);
        g.addWeightedEdge(4, 2, 1);

        g.displayGraph();

        DijkstraSSSP(g,0);

        // printing result of dijsktra : edges thr in the graph will be (pi(v) , v) , and priority(v) is weight of shortes path frm src to 'v'
        for(Vertex v : g.vertexList.values())
        {
            System.out.print(v.piValue + " " + v.data + " " + v.getPriority());
            System.out.println();
        }

    }

    // Graph Traversals : bfs , dfs , dfs recursive
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

    public static void DFSrec(Graph2_v2 g , int source)
    {
        Vertex src = g.vertexList.get(source);

        DFSrecursive(g,src);
    }

    public static void DFSrecursive(Graph2_v2 g , Vertex u)        // v is the vertex which U WNT to add onto the stack right now
    {
        u.setColor(BLACK);                // u mark this vertex as added to the DS and print it
        System.out.print(u.data + " ");    // u r printing it as u r popping it and visiting its neighbours : meanss u r exploring this vertex

        for(AdjListNode x : u.adjList)         // so u will call func recursively for all the adjacent vertices if they are nt added to d DS yet
        {
            Vertex vObj = g.vertexList.get(x.getVertexId()) ;

          if(vObj.getColor() == GREEN)
            {
                vObj.piValue = u.data;
                DFSrecursive(g,vObj);
            }
        }

        u.setColor(RED);   // u ve finished visiting all of the neighbours so u r out of the loop
    }

    // Dijkstra's shortest path algorithm

    public static void DijkstraSSSP(Graph2_v2 g , int source)
    {
       Vertex src = g.vertexList.get(source);

//You should create your priority Queue a little bit different by specifying how its elements should be compared. That is done by passing an anonymous Comparator for the Vertex class:

       PriorityQueue<Vertex> minHeap = new PriorityQueue<Vertex>(g.noOfVertices, new Comparator<Vertex>() {
           @Override
           public int compare(Vertex v1, Vertex v2) {
               return Integer.compare(v1.getPriority(),v2.getPriority());
           }
       });

       src.setPriority(0);
       minHeap.add(src);
       visitDijkstra(g,minHeap,src);

    }

    public static void visitDijkstra(Graph2_v2 g,PriorityQueue minHeap,Vertex v)
    {
      while(!minHeap.isEmpty())
      {

          Vertex u =(Vertex) minHeap.remove();

          u.setColor(RED);

          for(AdjListNode adjNode : u.adjList)
          {
                Vertex vObj = g.vertexList.get(adjNode.vertexId);

                if(vObj.getColor() == GREEN)
                {
                    vObj.setPriority( u.getPriority() + adjNode.getWeight());        // P(v) = P(u) + W(u,v);
                    minHeap.add(vObj);
                    vObj.setColor(BLACK);
                    vObj.piValue = u.data;
                }

                else if(vObj.getColor() == BLACK)        // if node is already thr in the data structure
                {
                    if(vObj.getPriority() > ( u.getPriority() + adjNode.getWeight()))    // then u calculate the priority if > then this then update
                    {
                        vObj.setPriority(u.getPriority() + adjNode.getWeight());       // this is actually a decrease key function
                        // so now to change into the data structure we dnt hv in built function in java so we remove then change priority then reinsert it into the heap

                        minHeap.remove(vObj);
                        minHeap.add(vObj);

                        vObj.piValue = u.data;
                    }
                }
          }
      }

    }

    // Minimum Spanning Tree : Prim's Algo

}
