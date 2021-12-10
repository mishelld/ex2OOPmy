package codes;
import api.EdgeData;

/**
 * represents the set of operations applicable on a
 * directional edge(src,dest) in a (directional) weighted graph.
 */
public class EdgeDataImpl implements EdgeData {

    private int dest;
    private String info;
    private int src;
    private int tag;
    private double weight;

    public EdgeDataImpl(int src,int Dest,double weight){
        this.src = src;
        this.weight = weight;
        this.dest = Dest;
        this.info = null;
        this.tag = 0;

    }
    public EdgeDataImpl(EdgeDataImpl other){
        this.src = other.src;
        this.weight = other.weight;
        this.dest = other.dest;
        this.info= null;
        this.tag = other.tag;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public int getSrc() {
        return this.src;
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
    public void setTag(int t) {
        this.tag = t;
    }

}