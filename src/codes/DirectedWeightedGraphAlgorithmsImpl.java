package codes;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<NodeData> shortestPath(int src, int dest);
 * 5. NodeData center(); // finds the NodeData which minimizes the max distance to all the other nodes.
 * // Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
 * 6. List<NodeData> tsp(List<NodeData> cities); // computes a list of consecutive nodes which go over all the nodes in cities.
 * // See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
 * 7. save(file); // JSON file
 * 8. load(file); // JSON file
 */

/**
 * Inits the graph on which this set of algorithms operates on.
 */
public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    private final DijkstraAlgo DIJKSTRA = new DijkstraAlgo();

    @Override
    public void init(DirectedWeightedGraph g) {
        graph = g;
    }

    /**
     * Returns the underlying graph of which this class works.
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     */
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy = new DirectedWeightedGraphImpl();
        Iterator<NodeData> node_it = graph.nodeIter();
        if (node_it.hasNext()) {
            while (node_it.hasNext()) {
                NodeData node_c = node_it.next();
                NodeData node_v = new NodeDataImpl(node_c);
                copy.addNode(node_v);
            }
        }
        Iterator<EdgeData> Edge_it = graph.edgeIter();
        if(Edge_it.hasNext()) {
            while (Edge_it.hasNext()) {
                EdgeData edge_c = Edge_it.next();
                copy.connect(edge_c.getSrc(), edge_c.getDest(), edge_c.getWeight());
            }
        }
        return copy;
    }



    /**
     * Returns true if and only if (iff) there is a valid path from each node to each other node.
     * NOTE: assume directional graph (all n*(n-1) ordered pairs).
     */


    @Override
    public boolean isConnected() {
        if (isStronglyConnected(graph, graph.nodeSize())) {
            return true;
        }
        return false;
    }

    private static void DFS(DirectedWeightedGraph graph, NodeData n, boolean[] visited) {
        visited[n.getKey()] = true;

        Iterator<EdgeData> iter = graph.edgeIter(n.getKey());
        for (Iterator<EdgeData> it3 = iter; it3.hasNext(); ) {
            EdgeData edge = iter.next();
            NodeData nodeSon = graph.getNode(edge.getDest());

            if (!visited[nodeSon.getKey()]) {
                DFS(graph, nodeSon, visited);

            }
        }
    }

    public static boolean isStronglyConnected(DirectedWeightedGraph graph, int n) {
        Iterator<NodeData> iterNode = graph.nodeIter();
        for (Iterator<NodeData> it = iterNode; it.hasNext(); ) {
            NodeData node = iterNode.next();
            boolean[] visited = new boolean[n];
            DFS(graph, node, visited);
            for (boolean b : visited) {
                if (!b) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        DIJKSTRA.doAlgo(graph, graph.getNode(src));
        if (DIJKSTRA.isTherePath(this.graph, dest)) {
            HashMap<NodeData, Double> pathDistFromSrc = DIJKSTRA.shortestDistFromVertex;
            return pathDistFromSrc.get(graph.getNode(dest));
        }
        return -1;
    }

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        DIJKSTRA.doAlgo(graph, graph.getNode(src));
        HashMap<NodeData, NodeData> pathDistFromSrc = DIJKSTRA.previeusVertex;
        if (DIJKSTRA.isTherePath(this.graph, dest)) {
            Stack<NodeData> pathOppositeDirection = new Stack<>();
            List<NodeData> path = new LinkedList<>();
            NodeData currNode = this.graph.getNode(dest);
            while (currNode.getKey() != src) {
                pathOppositeDirection.push(currNode);
                currNode = pathDistFromSrc.get(currNode);
            }
            while (!pathOppositeDirection.isEmpty())
                path.add(pathOppositeDirection.pop());
            return path;
        }
        return null;
    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     * return the Node data to which the max shortest path to all the other nodes
     * is minimized.
     */
    @Override
    public NodeData center() {
        if (isConnected()) {
            double min = Double.MAX_VALUE;
            for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
                double max = 0;
                NodeData n = it.next();
                for (Iterator<NodeData> it2 = graph.nodeIter(); it2.hasNext(); ) {
                    NodeData e = it2.next();
                    double dist = shortestPathDist(n.getKey(), e.getKey());
                    if (dist > max) {
                        max = dist;
                    }
                }
                if (min > max) {
                    min = max;
                }
            }
            for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
                NodeData n = it.next();
                for (Iterator<NodeData> it2 = graph.nodeIter(); it2.hasNext(); ) {
                    NodeData e = it2.next();
                    double dist = shortestPathDist(n.getKey(), e.getKey());

                    if (dist == min) {
                        return n;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 1. we want to check if the graph is connect and we know the short path to from v to other one;
     * 2. we search the vertex that need minimum Radios to get to all the others vertical.
     * 2. we will be move about every vertical and search who have the min wight(Radious) for the biggest distance;
     * 3. save this index and return him.
     * if the graph not connect it will retun NUll.
     */

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is
     * the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(this.isConnected()){
            List<NodeData> unVisited = new LinkedList<>();
            Iterator<NodeData> nodes = this.graph.nodeIter();
            List<NodeData> path = new LinkedList<>();
            if(nodes.hasNext()){
                double totalDistance = tsp(unVisited, nodes.next(), path, new LinkedList<>());
                System.out.println("Distance: " + totalDistance);
                return path;
            }
            return null;
        }
        System.out.print("There is no path such that go through all the cities");
        return null;
    }

    public Double tsp(List<NodeData> unVisited, NodeData currNode, List<NodeData> path, List<NodeData> bestPath) {
        if(unVisited.isEmpty()){
            List<NodeData> toStart = this.shortestPath(currNode.getKey(), path.get(0).getKey());
            for(NodeData node : toStart){
                path.add(node);
            }
            return this.shortestPathDist(currNode.getKey(), path.get(0).getKey());
        }
        double min = DijkstraAlgo.INFINITY;
        Iterator<EdgeData> outEdges = this.graph.edgeIter(currNode.getKey());
        while(outEdges.hasNext()){
            EdgeData currEdge = outEdges.next();
            NodeData destNode = this.graph.getNode(currEdge.getDest());
            if(unVisited.contains(destNode)){
                path.add(currNode);
                unVisited.remove(destNode);
                currNode = destNode;
                double isMin = tsp(unVisited, currNode, path, bestPath) + currEdge.getWeight();
                if(isMin < min){
                    min = isMin;
                    copyPath(path, bestPath);
                }
            }
        }
        copyPath(bestPath, path);;
        return min;
    }

    public void copyPath(List<NodeData> from, List<NodeData> to){
        to.clear();
        for(NodeData node : from){
            to.add(node);
        }
    }


    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * param jasonFile - the file name (may include a relative path).
     * return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        boolean b = load(file);
        if(b){
            try (Writer writer_graph = new FileWriter(file)) {
                Gson json_file = new GsonBuilder().create();
                json_file.toJson(graph, writer_graph);
                return true;
            }
            catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return b;
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            DirectedWeightedGraphImpl load_graph = new DirectedWeightedGraphImpl();
            JSONParser parser = new JSONParser();
            Object str = parser.parse(new FileReader(file));
            JSONObject json_file = (JSONObject) str;
            JSONArray graph_nodes = (JSONArray) json_file.get("Nodes");
            JSONArray graph_edges = (JSONArray) json_file.get("Edges");
            double arr[] = {0,0,0};
            for (Object obj : graph_nodes) {
                JSONObject temp = (JSONObject) obj;
                boolean b = true;
                if ((temp.get("pos") == null) || (temp.get("id") == null)) b = false;
                if(b) {
                    arr = splitPoint(temp.get("pos").toString());
                    GeoLocationImpl p1 = new GeoLocationImpl(arr[0], arr[1], arr[2]);
                    int key = Integer.parseInt(temp.get("id").toString());
                    NodeData n = new NodeDataImpl(p1, key);
                    load_graph.addNode(n);
                }
            }
            for (Object obj : graph_edges) {
                JSONObject temp = (JSONObject) obj;
                boolean b = true;
                if ((temp.get("src") == null) || (temp.get("dest") == null) ||(temp.get("w") == null)) b = false;
                if (b) {
                    int src = Integer.parseInt(temp.get("src").toString());
                    int dest = Integer.parseInt(temp.get("dest").toString());
                    double w = Double.parseDouble(temp.get("w").toString());
                    load_graph.connect(src, dest, w);
                }
            }
            init(load_graph);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double[] splitPoint(String s) {
        double arr[] = {0,0,0};
        String[] arrOfStr = s.split(",");
        arr[0] = Double.parseDouble(arrOfStr[0]);
        arr[1] = Double.parseDouble(arrOfStr[1]);
        arr[2] = Double.parseDouble(arrOfStr[2]);
        return arr;
    }

}
