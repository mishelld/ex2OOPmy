package codes;

import api.*;

import java.awt.*;
import java.util.HashMap;

/**
 * represents the set of operations applicable on a
 * node (vertex) in a (directional) weighted graph.
 */
public class NodeDataImpl implements NodeData {

    private String info;
    private int key;
    private int tag;
    private double weight;
    private GeoLocation location;
    private HashMap<Integer, EdgeData> outEdges; // The edges that going out from this node.
    private HashMap<Integer, EdgeData> inEdges;  // The edges that going in to this node.

    public NodeDataImpl(int key, String loc) {
        this.info = null;
        this.key = key;
        this.tag = tag;
        this.weight = weight;
        location = new GeoLocationImpl(loc);
        this.outEdges = new HashMap<>();
        this.inEdges = new HashMap<>();
    }

    public NodeDataImpl(GeoLocation location, int key){
        this.info = null;
        this.key = key;
        this.tag = 0;
        this.weight = 0;
        this.location = location;
    }

    public NodeDataImpl(NodeData other) {
        this.key = other.getKey();
        //   this.location = new GeoLocationImpl(other.getLocation());
        this.outEdges = new HashMap<>();
        this.inEdges = new HashMap<>();
    }
    /*
       public EdgeData getOutEdge(int dest) throws Exception{
           if(this.outEdges.get(dest) != null)
               return this.outEdges.get(dest);
           throw new Exception("Invalid out edge!");
       }
       public EdgeData getInEdge(int src) throws Exception{
           if(this.inEdges.get(src) != null)
               return this.inEdges.get(src);
           throw new Exception("Invalid in edge!");
       }
       public int getOutEdgesSize(){
           return this.outEdges.size();
       }
       public int getInEdgesSize(){
           return this.inEdges.size();
       }
       public void setOutEdge(EdgeData edge){ // O(1)
           this.outEdges.put(edge.getDest(), edge);
       }
       public void setInEdge(EdgeData edge){ // O(1)
           this.inEdges.put(edge.getSrc(), edge);
       }
       */
    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }
    /*
    public HashMap<Integer, EdgeData> getOutEdgesHash(){
        return this.outEdges;
    }
    public HashMap<Integer, EdgeData> getInEdgesHash(){
        return this.inEdges;
    }
    */
}