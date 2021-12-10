package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.*;
import codes.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MyDirectedWeightedGraphTest {
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithmsImpl algoGraph;

    MyDirectedWeightedGraphTest() throws IOException, ParseException {
        graph = Ex2.getGrapg("data/G1.json"); // enter here the path for G1 json

        algoGraph = new DirectedWeightedGraphAlgorithmsImpl();
        algoGraph.init(graph);
    }

    public static void main(String[] args) throws IOException, ParseException {
        MyDirectedWeightedGraphTest test = new MyDirectedWeightedGraphTest();
        System.out.println(test.graph.nodeSize());
        System.out.println(test.algoGraph.isConnected());
        System.out.println(test.algoGraph.shortestPathDist(1,7));
        //System.out.println(test.algoGraph.shortestPath(0,10));
        System.out.println(test.graph.getNode(3).getLocation().y());
        System.out.println(test.graph.getEdge(3,4).getWeight());



//        for (NodeData node:test.algoGraph.shortestPath(0,10)) {
//            System.out.println(node.getKey());
//        }
//        System.out.println(test.algoGraph.center().getKey());





    }
//1
     /*@Test
     void testConnect() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(true, test1.algoGraph.isConnected());
         MyDirectedWeightedGraphTest test2 = new MyDirectedWeightedGraphTest();
         for (int i = 0; i <1 ; i++) {
             for (int j = 1; j <= test2.graph.nodeSize(); j++) {
                 test2.graph.removeEdge(i,j);
                 test2.graph.removeEdge(j,i);
             }
         }
         assertEquals(false, test2.algoGraph.isConnected());

     }
//2
    @Test
     void testCenter() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        assertEquals(8, test1.algoGraph.center().getKey());

    }

    @Test
    void testShortestPath() {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        assertEquals(1.0631605142699874, test1.algoGraph.shortestPathDist(2,3));

    }
//4
     @Test
     void testEdgeSize() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(36, test1.graph.edgeSize());
     }
//5
     @Test
     void testGetEdge() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(1.8015954015822042,test1.graph.getEdge(1,2).getWeight());

     }
//6
     @Test
     void testGetMC() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         test1.graph.removeEdge(0,1);
         test1.graph.removeEdge(1,0);
         test1.graph.removeEdge(2,1);
         assertEquals(92, test1.graph.getMC());

     }
//7
     @Test
     void testGetNode() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         GeoLocation g = new GeoLocationImpl("35.19589389346247,32.10152879327731,0.0");
         assertEquals(g.toString(), test1.graph.getNode(0).getLocation().toString());
     }
//8
     @Test
     void testNodeSize() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         assertEquals(17, test1.graph.nodeSize());
     }
//9
     @Test
     void testRemoveEdge() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         test1.graph.removeEdge(0,1);
         assertEquals(null, test1.graph.getEdge(0,1));

     }
//10
     @Test
     void testRemoveNode() {
         MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
         test1.graph.removeNode(1);
         assertEquals(16, test1.graph.nodeSize());

     }*/
    @Test
    void all() throws IOException, ParseException {
        MyDirectedWeightedGraphTest test1 = new MyDirectedWeightedGraphTest();
        assertEquals(true, test1.algoGraph.isConnected());
        MyDirectedWeightedGraphTest test2 = new MyDirectedWeightedGraphTest();
        for (int i = 0; i <1 ; i++) {
            for (int j = 1; j <= test2.graph.nodeSize(); j++) {
                test2.graph.removeEdge(i,j);
                test2.graph.removeEdge(j,i);
            }
        }
        //1
        assertEquals(false, test2.algoGraph.isConnected());
//2
        assertEquals(8, test1.algoGraph.center().getKey());
//3
        assertEquals(1.0631605142699874, test1.algoGraph.shortestPathDist(2,3));
//4
        assertEquals(36, test1.graph.edgeSize());
//5
        assertEquals(1.8015954015822042,test1.graph.getEdge(1,2).getWeight());
//6
        test1.graph.removeEdge(0,1);
        test1.graph.removeEdge(1,0);
        test1.graph.removeEdge(2,1);
        assertEquals(92, test1.graph.getMC());
//7
        GeoLocation g = new GeoLocationImpl("35.19589389346247,32.10152879327731,0.0");
        assertEquals(g.toString(), test1.graph.getNode(0).getLocation().toString());
//8
        assertEquals(17, test1.graph.nodeSize());
//9
        test1.graph.removeNode(1);
        assertEquals(16, test1.graph.nodeSize());


        //10
        test1.graph.removeEdge(0,1);
        assertEquals(null, test1.graph.getEdge(0,1));






    }



}
