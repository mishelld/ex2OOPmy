package gui;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphG3 extends JFrame implements ActionListener {
    static JFrame f1;
    static JButton button;
    static JButton button1;
    static JButton button2;
    static JButton button3;
    static JButton button4;
    static JButton button5;
    static JButton button6;
    static JButton button7;
    static JButton button8;
    static JButton button9;
    static JButton button10;




    static JLabel text;
    private gui.panel panel;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    graphG3(DirectedWeightedGraphAlgorithms ans){
        super();
        graphAlgo = ans;
        panel = new panel(ans.getGraph());
        panel.init(graphAlgo.getGraph());
        panel.repaint();
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        button = new JButton("get node");
        button1 = new JButton("get edge");
        button2 = new JButton("size of nodes");
        button3= new JButton("is graph connected");
        button4= new JButton("load graph");
        button5= new JButton("remove edge");
        button6= new JButton("remove node");
        button7= new JButton("save");
        button8= new JButton("shortest path");
        button9= new JButton("shortest path dist");
        button10= new JButton("TSP");






        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        button10.addActionListener(this);







        JPanel p1 = new JPanel();

        p1.add(button);
        p1.add(button1);
        p1.add(button2);
        p1.add(button3);
        p1.add(button4);
        p1.add(button5);
        p1.add(button6);
        p1.add(button7);
        p1.add(button8);
        p1.add(button9);
        p1.add(button10);


        add(p1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("get node")) {
            new giveNode(graphAlgo.getGraph());
        }
        else if(s.equals("get edge")){
            new ShortestPathDistance(graphAlgo);

        } else if(s.equals("size of nodes")){
            String message = "The Size Of The Nodes In The Graph is: " + graphAlgo.getGraph().nodeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);


        } else if(s.equals("size of edges")) {
            String message = "The Size Of The Nodes In The Graph is: " + graphAlgo.getGraph().edgeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);
        }
        else if(s.equals("is graph connected")){
            String m;

            if(graphAlgo.isConnected()) {
                m = "The graph is Connected";
            }
            else {
                m = "The graph Isn't Connected ";
            }
            JOptionPane.showMessageDialog(new JFrame(), m, "Connected", JOptionPane.DEFAULT_OPTION);

        }
        else if(s.equals("load graph")){
            new Loadthegraph(graphAlgo, panel);
        }

        else if(s.equals("save")){
            new Savethegraph(graphAlgo);
        }
        else if(s.equals("shortest path")){
            new ShortestPathgraph(graphAlgo);

        }

        else if(s.equals("shortest path dist")) {
            new ShortestPathDistance(graphAlgo);
        }
        else if(s.equals("TSP")){
            new graphtsp(graphAlgo);

        }
    }

}
