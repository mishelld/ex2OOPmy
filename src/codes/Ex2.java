package codes;

import api.*;
import gui.*;

import api.*;
import gui.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the main class for Ex2 - your implementation will be tested
 * using this class.
 */

public class Ex2 {
    // = "data/G1.json";


    public static void main(String[] args) {
        String json_file = "data/G3.json"; //= args[0];
        runGUI(json_file);
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     **/
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraphImpl Initialize_graph = new DirectedWeightedGraphImpl();
        DirectedWeightedGraphAlgorithmsImpl algo_graph = new DirectedWeightedGraphAlgorithmsImpl();
        algo_graph.init(Initialize_graph);
        try{
            algo_graph.load(json_file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return algo_graph.getGraph();
    }


    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphImpl Initialize_graph = new DirectedWeightedGraphImpl();
        DirectedWeightedGraphAlgorithmsImpl algo_graph = new DirectedWeightedGraphAlgorithmsImpl();
        algo_graph.init(Initialize_graph);
        try {
            algo_graph.load(json_file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return algo_graph;
    }


    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms graphAlgo = getGrapgAlgo(json_file);
        new frame(graphAlgo);
    }

}