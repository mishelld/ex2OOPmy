package codes;

import java.util.*;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

public class  DirectedWeightedGraphImpl implements DirectedWeightedGraph{

    private final HashMap<Integer, NodeData> nodes; // id --> node
    private final HashMap<Integer, Degree> edgesByNode; // id --> degree(inEdges + outEdges)
    private final HashMap<LinkedList<Integer>, EdgeData> edges; // (src, dest) --> edge
    private int mc;

    public DirectedWeightedGraphImpl(){
        this.nodes =  new HashMap<>();
        this.edgesByNode = new HashMap<>();
        this.edges = new HashMap<>();
        this.mc = 0;
    }

    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return this.edges.get(createList(src, dest));
    }

    @Override
    public void addNode(NodeData node) {
        if (this.nodes.get(node.getKey()) == null){
            this.nodes.put(node.getKey(), node);
            this.edgesByNode.put(node.getKey(), new Degree());
            this.mc += 1;
        }
        else System.out.println("Node " + node.getKey() + "already exists");
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(this.getNode(src) != null){
            if(this.getNode(dest) != null){
                EdgeData edge = new EdgeDataImpl(src, dest, w);
                this.edgesByNode.get(src).addOutEdge(edge);
                this.edgesByNode.get(dest).addInEdge(edge);
                this.edges.put(createList(src,dest), edge);
                this.mc += 1;
            }
            else System.out.println("Dest node: " + dest + " not exists");
        }
        else System.out.println("Src node: " + src + " not exists");
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return this.nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return this.edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.edgesByNode.get(node_id).outEdges.values().iterator();
    }

    @Override
    public int edgeSize() {
        return this.edges.size();
    }

    @Override
    public int getMC() {
        return this.mc;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        LinkedList<Integer> key = createList(src, dest);
        EdgeData edge = this.edges.get(key);
        if(edge != null){
            this.edgesByNode.get(src).removeOutEdge(edge);
            this.edgesByNode.get(dest).removeInEdge(edge);
            this.mc += 1;
            return this.edges.remove(key);
        }
        else System.out.println("Edge from " + src + " to " + dest + " not exists");
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        if(this.getNode(key) != null){
            Degree degree = this.edgesByNode.get(key);
            int count = degree.outEdges.size();
            LinkedList<EdgeData> edges = new LinkedList<>(degree.outEdges.values());
            count += degree.inEdges.size();
            edges.addAll(degree.inEdges.values());
            for (int i = 0; i < count; i++) {
                EdgeData edge = edges.get(i);
                removeEdge(edge.getSrc(), edge.getDest());
            }
            this.edgesByNode.remove(key);
            this.mc += 1;
            return this.nodes.remove(key);
        }
        else System.out.println("Node " + key + " not exists");
        return null;
    }

    private LinkedList<Integer> createList(int src, int dest){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(src);
        list.add(dest);
        return list;
    }

    public static class Degree {

        public final HashMap<Integer, EdgeData> outEdges;
        public final HashMap<Integer, EdgeData> inEdges;

        public Degree() {
            this.outEdges = new HashMap<>();
            this.inEdges = new HashMap<>();
        }

        public void addOutEdge(EdgeData edge){
            this.outEdges.put(edge.getDest(), edge);
        }

        public void addInEdge(EdgeData edge){
            this.inEdges.put(edge.getSrc(), edge);
        }

        public void removeOutEdge(EdgeData edge){
            this.outEdges.remove(edge.getDest());
        }

        public void removeInEdge(EdgeData edge){
            this.inEdges.remove(edge.getSrc());
        }
    }
}