package codes;

import java.util.*;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;


/**
 * represents a Directional Weighted Graph,
 */
public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<ArrayList<Integer>, EdgeData> edges;
    private HashMap<Integer, Degree> edgeByNode;
    private int mc;
    private int edgesSize;

    public DirectedWeightedGraphImpl() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        edgeByNode = new HashMap<>();
        mc = 0;
        this.edgesSize = 0;

    }
    public DirectedWeightedGraphImpl(DirectedWeightedGraphImpl g){
        g.nodes = this.nodes;
        g.edgeByNode = this.edgeByNode;
        g.edges = this.edges;
        g.mc = 0;
    }

    public HashMap<Integer, NodeData> getNodes() {
        return nodes;
    }

    public HashMap<ArrayList<Integer>, EdgeData> getEdges() {
        return edges;
    }



    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        ArrayList<Integer> vector = edge(src, dest);
        return edges.get(vector);
    }

    public Collection<EdgeData> getEdgev() {
        return edges.values();
    }


    //done
    @Override
    public void addNode(NodeData n) {

        if(nodes.get(n.getKey()) == null){
            this.getEdgev();
            if(n instanceof NodeDataImpl){
                nodes.put(n.getKey(), (NodeDataImpl) n);
                edgeByNode.put(n.getKey(), new Degree());
                ++mc;
            }
            else System.out.println("Invalid node class");
            return;
        }
        System.out.println("This node alrady in the graph");

    }




    //done 50;
    @Override
    public void connect(int src, int dest, double w) {
        if(nodes.get(src) != null){
            if(nodes.get(dest) != null){
                EdgeDataImpl edge = new EdgeDataImpl(src, dest, w);
                ArrayList<Integer> vector = edge(src, dest);
                removeEdge(src, dest);
                edges.put(vector, edge);
                edgeByNode.get(src).outEdges.put(dest, edge);
                edgeByNode.get(dest).inEdges.put(src, edge);
                ++mc;
                return;
            }
            System.out.println("Dest node not exists");
            return;
        }
        System.out.println("Src node not exists");
    }
    //done
    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }
    //done
    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.values().iterator();
    }
    //done
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return edgeByNode.get(node_id).outEdges.values().iterator();
    }
    //fix
    @Override
    public NodeData removeNode(int key) {
        ++mc;
        NodeData node = nodes.remove(key);
        if (node == null) {
            return null;
        }
        Iterator<EdgeData> iter = edgeByNode.get(key).outEdges.values().iterator();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            edges.remove(edge(key, edge.getDest()));
            edgeByNode.get(edge.getDest()).inEdges.remove(key);
        }
        iter = edgeByNode.get(key).inEdges.values().iterator();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            edges.remove(edge(edge.getSrc(), key));
            edgeByNode.get(edge.getSrc()).outEdges.remove(key);
        }
        edgeByNode.remove(key);
        return node;

    }

    //fix
    @Override
    public EdgeData removeEdge(int src, int dest) {
        ArrayList<Integer> vector = edge(src, dest);

        if (edgeByNode.get(src) != null) {
            edgeByNode.get(src).outEdges.remove(dest);
        }
        if (edgeByNode.get(dest) != null) {
            edgeByNode.get(dest).inEdges.remove(src);
        }
//        edgeByNode.get(src).fromMe.remove(dest);
//        edgeByNode.get(dest).toMe.remove(src);
        ++mc;
        return edges.remove(vector);
    }

    //done
    @Override
    public int nodeSize() {
        return nodes.size();
    }
    //done
    @Override
    public int edgeSize() {
        return edges.size();
    }

    //done
    public int edgesSize() {
        return this.edgesSize;
    }


    //done
    @Override
    public int getMC() {
        return mc;
    }


    //done
    public ArrayList<Integer> edge(int src, int dest){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(src);
        list.add(dest);

        return list;
    }

    private void stam() {
        Degree temp = new Degree();

    }

    private class Degree {
        public HashMap<Integer, EdgeData> outEdges;
        public HashMap<Integer, EdgeData> inEdges;

        public Degree() {
            this.outEdges = new HashMap<>();
            this.inEdges = new HashMap<>();
        }
    }



}