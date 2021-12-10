package codes;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        return null;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * param jasonFile - the file name (may include a relative path).
     * return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        FileWriter jsonFile;
        Map<String, JSONArray> mainMap = new HashMap<>();

        JSONArray edgeArray = new JSONArray();
        Iterator<EdgeData> iterEdges = graph.edgeIter();
        while (iterEdges.hasNext()) {
            EdgeData edge = iterEdges.next();
            Map<String, String> edgeMap = new HashMap<>();
            edgeMap.put("src", edge.getSrc() + "");
            edgeMap.put("w", edge.getWeight() + "");
            edgeMap.put("dest", edge.getDest() + "");
            JSONObject obj = new JSONObject();
            obj.putAll(edgeMap);
            edgeArray.add(obj);
        }
        mainMap.put("Edges", edgeArray);
        JSONArray nodeArray = new JSONArray();
        Iterator<NodeData> iterNodes = graph.nodeIter();
        while (iterNodes.hasNext()) {
            NodeData node = iterNodes.next();
            GeoLocation pos = node.getLocation();
            Map<String, String> nodesMap = new HashMap<>();
            nodesMap.put("pos", pos.x() + "," + pos.y() + "," + pos.z());
            nodesMap.put("id", node.getKey() + "");
            JSONObject obj = new JSONObject();
            obj.putAll(nodesMap);
            nodeArray.add(obj);
        }
        mainMap.put("Nodes", nodeArray);

        try {

            // Constructs a FileWriter given a file name, using the platform's default
            // charset
            jsonFile = new FileWriter(file);
            JSONObject temp = new JSONObject();
            temp.putAll(mainMap);
            jsonFile.write(temp.toJSONString());
            jsonFile.flush();
            jsonFile.close();

        } catch (IOException e) {
            // e.printStackTrace();
            return false;
        }
        // } finally {

        // try {

        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
        return true;
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
            DirectedWeightedGraph newGraph = Ex2.getGrapg(file);
            graph = newGraph;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

}