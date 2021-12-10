package gui;


import api.DirectedWeightedGraphAlgorithms;
import codes.Ex2;
import gui.pop.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class frame extends JFrame implements ActionListener, MouseListener {
    private gui.panel panel;
    private JMenuBar mb;

    private JMenu graphOp;
    private JMenuItem getNode;
    private JMenuItem getEdge;
    private JMenuItem addNode;
    private JMenuItem connect;
    private JMenuItem removeNode;
    private JMenuItem removeEdge;
    private JMenuItem nodeSize;
    private JMenuItem edgeSize;
    
    private JMenu grapgAlgoOp;
    private JMenuItem isConnected;
    private JMenuItem shortestPathDist;
    private JMenuItem shortestPath;
    private JMenuItem center;
    private JMenuItem tsp;//need to do
    private JMenuItem save;
    private JMenuItem load;
    static JButton button;
    static JButton button1;
    static JButton button2;
    static JButton button3;
    static JButton button4;
    static JButton button5;


    private boolean needToAddNode = false;
    private DirectedWeightedGraphAlgorithms graphAlgo;

    public frame(DirectedWeightedGraphAlgorithms ans) {
        super();

        graphAlgo = ans;
        panel = new panel(ans.getGraph());
          test1();
      //  buildBar();
      // this.add(panel);
        this.addMouseListener(this);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
    }

    private void test1(){
        button = new JButton("show graph G3");
        button1 = new JButton("show graph G2");
        button2 = new JButton("show graph G1");
        button3 = new JButton("graphG1");
        button4 = new JButton("graphG2");
        button5 = new JButton("graphG3");



        // addActionListener to button
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);



        JPanel p = new JPanel();


        p.add(button);
        p.add(button1);
        p.add(button2);
        p.add(button3);
        p.add(button4);
        p.add(button5);


        // add panel to frame
        add(p);

    }


    private void buildBar() {
        graphOp = new JMenu("Graph");
        getNode = new JMenuItem("Get Node");
        getNode.addActionListener(this);
        getEdge = new JMenuItem("Get Edge");
        getEdge.addActionListener(this);
        addNode = new JMenuItem("Add Node");
        addNode.addActionListener(this);
        connect = new JMenuItem("Connect Nodes");
        connect.addActionListener(this);
        removeNode = new JMenuItem("Remove Node");
        removeNode.addActionListener(this);
        removeEdge = new JMenuItem("Remove Edge");
        removeEdge.addActionListener(this);
        nodeSize = new JMenuItem("Size Of Nodes");
        nodeSize.addActionListener(this);
        edgeSize = new JMenuItem("Size Of Edges");
        edgeSize.addActionListener(this);

        grapgAlgoOp = new JMenu("Algorithms");
        isConnected = new JMenuItem("Is Connected");
        isConnected.addActionListener(this);
        shortestPathDist = new JMenuItem("Shortest Path Dist");
        shortestPathDist.addActionListener(this);
        shortestPath = new JMenuItem("Shortest Path");
        shortestPath.addActionListener(this);
        center = new JMenuItem("Center");
        center.addActionListener(this);
        tsp = new JMenuItem("TSP");
        tsp.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        load = new JMenuItem("Load");
        load.addActionListener(this);


        graphOp.add(getNode);
        graphOp.add(getEdge);
        graphOp.add(addNode);
        graphOp.add(connect);
        graphOp.add(removeNode);
        graphOp.add(removeEdge);
        graphOp.add(nodeSize);
        graphOp.add(edgeSize);
        
        grapgAlgoOp.add(isConnected);
        grapgAlgoOp.add(shortestPathDist);
        grapgAlgoOp.add(shortestPath);
        grapgAlgoOp.add(center);
        grapgAlgoOp.add(tsp);
        grapgAlgoOp.add(save);
        grapgAlgoOp.add(load);


        mb = new JMenuBar();
        mb.add(graphOp);
        mb.add(grapgAlgoOp);
        setJMenuBar(mb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getNode) {
            new giveNode(graphAlgo.getGraph());
        }
        else if(e.getSource() == getEdge) {
            new giveEdge(graphAlgo.getGraph());
        }
        else if(e.getSource() == addNode) {
            needToAddNode = true;
        }
        else if(e.getSource() == connect) {
            new connecting(graphAlgo.getGraph(), panel);
        }

        else if (e.getSource() == nodeSize) {
            String message = "The Size Of The Nodes In The Graph is: " + graphAlgo.getGraph().nodeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);
        }
        else if(e.getSource() == edgeSize) {
            String message = "The Size Of The Edges In The Graph is: " + graphAlgo.getGraph().edgeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Edges", JOptionPane.DEFAULT_OPTION);
        }
        else if(e.getSource() == isConnected) {
            String message;
            if(graphAlgo.isConnected()) {
                message = "The Graph Is Connected :)";
            }
            else {
                message = "The Graph Isn't Connected :(";
            }
            JOptionPane.showMessageDialog(new JFrame(), message, "Is The Graph Connected", JOptionPane.DEFAULT_OPTION);
        }
        else if(e.getSource() == shortestPathDist) {
            new ShortestPathDistance(graphAlgo);
        }
        else if(e.getSource() == shortestPath) {
            new ShortestPathgraph(graphAlgo);
        }
        else if(e.getSource() == center) {
            String message = "The Center Node In The Graph is: " + graphAlgo.center().getKey();
            JOptionPane.showMessageDialog(new JFrame(), message, "Center In Graph", JOptionPane.DEFAULT_OPTION);
        }
        else if(e.getSource() == tsp) {
            new graphtsp(graphAlgo);
        }
        else if(e.getSource() == save) {
            new Savethegraph(graphAlgo);
        }
        else if(e.getSource() == load) {
            new Loadthegraph(graphAlgo, panel);
        }


        String s = e.getActionCommand();
        if (s.equals("show graph G3")) {
            G3 m = new G3(Ex2.getGrapgAlgo("data/G3.json"));

        }
        else if(s.equals("show graph G2")){
           G2 P = new G2(Ex2.getGrapgAlgo("data/G2.json"));

        }
        else if(s.equals("show graph G1")){
            G1 m = new G1(Ex2.getGrapgAlgo("data/G1.json"));
        }
        else if(s.equals("graphG1")){
            graphG1 m = new graphG1(Ex2.getGrapgAlgo("data/G1.json"));

        }
        else if(s.equals("graphG2")){
            graphG2 m = new graphG2(Ex2.getGrapgAlgo("data/G2.json"));

        }
        else if(s.equals("graphG3")){
            graphG3 m = new graphG3(Ex2.getGrapgAlgo("data/G3.json"));

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (needToAddNode) {
            new AddNode(graphAlgo.getGraph(), panel, e.getX(),e.getY());
            needToAddNode = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
