package gui;

import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class graphtsp extends JFrame implements ActionListener {
    private JTextField inputName;
    private JButton selectAll;
    private JButton enter;
    private JLabel textName;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    public graphtsp(DirectedWeightedGraphAlgorithms graphAlgo) {
        super("TSP");
        this.graphAlgo = graphAlgo;
        textName = new JLabel("write Nodes: \n (For Example: 1,2,3)");
        selectAll = new JButton("Select All");
        enter = new JButton("click");
        selectAll.addActionListener(this);
        enter.addActionListener(this);
        inputName = new JTextField(8);
        inputName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    readNodes();
                }
            }
        });

        // create a panel to add buttons and textfield
        JPanel p = new JPanel();
        // add buttons and textfield to panel
        p.add(textName);
        p.add(inputName);
        p.add(selectAll);
        p.add(enter);

        //p.setPreferredSize(new Dimension(125, 100));
        // add panel to frame
        add(p);

        // set the size of frame
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(true);
        setVisible(true);
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("click")) {
            readNodes();
        }
        else if(s.equals("Select All")) {
            selectAllNode();
        }
    }
    private void readNodes() {
        try {
            String[] nodesId = inputName.getText().split(",");
            List<NodeData> nodes = new ArrayList<>();
            int id;
            NodeData node;
            for (String idT : nodesId) {
                id = Integer.parseInt(idT);
                node = graphAlgo.getGraph().getNode(id);
                if(node != null) {
                    nodes.add(node);
                }
                else {
                    String message = "The Node " + id + " not Found ";
                    JOptionPane.showMessageDialog(new JFrame(), message, "The Node not Found", JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    return;
                }
            }
            tsp(nodes);
        }
        catch (Exception e) {
            e.printStackTrace();
            String message = "Something is Wrong ";
            JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void selectAllNode() {
        List<NodeData> nodes = new ArrayList();
        Iterator<NodeData> iter = graphAlgo.getGraph().nodeIter();
        while (iter.hasNext()) {
            nodes.add(iter.next());
        }
        tsp(nodes);
    }
    private void tsp(List<NodeData> nodes) {
        setVisible(false);
        nodes = graphAlgo.tsp(nodes);
        if (nodes == null) {
            String message = "There Is No Path :(";
            JOptionPane.showMessageDialog(new JFrame(), message, "There Is No Path", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        String m = "";
        for (NodeData nodeData : nodes) {
            m += nodeData.getKey() + "----> ";
        }
        JOptionPane.showMessageDialog(new JFrame(), m, "TSP", JOptionPane.DEFAULT_OPTION);
        this.dispose();
    }
}
