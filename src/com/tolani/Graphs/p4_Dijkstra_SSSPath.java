package com.tolani.Graphs;

import java.util.*;
import com.tolani.Graphs.Graph2_v2.*;
import static com.tolani.Graphs.Color.*;

public class p4_Dijkstra_SSSPath {

    public static void main(String[] args) {
        Graph2_v2 g = new Graph2_v2(5, GraphType.DIRECTED);       // size 5 means vertices frm 0 to 4 : tc here

        // weighted graph example

        g.addWeightedEdge(0, 1, 3);
        g.addWeightedEdge(0, 3, 6);
        g.addWeightedEdge(1, 3, 2);
        g.addWeightedEdge(1, 2, 9);
        g.addWeightedEdge(3, 4, 4);
        g.addWeightedEdge(4, 1, 2);
        g.addWeightedEdge(4, 2, 1);

        g.displayGraph();

        DijkstraSSSP(g, 0);

        // printing result of dijsktra : edges thr in the graph will be (pi(v) , v) , and priority(v) is weight of shortes path frm src to 'v'
        for (Vertex v : g.vertexList.values()) {
            System.out.print(v.piValue + " " + v.data + " " + v.getPriority());
            System.out.println();
        }

    }


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

}
