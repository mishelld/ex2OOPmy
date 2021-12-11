package gui;

import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class graphtsp extends JFrame implements ActionListener {
    private JTextField name;
    private JButton button;
    private JLabel texy;

    private DirectedWeightedGraphAlgorithms GAlgo;

    public graphtsp(DirectedWeightedGraphAlgorithms graphAlgo) {
        super("tsp algo");
        this.GAlgo = graphAlgo;
        texy = new JLabel("write the cities like this 1:2:3..and so on: ");
        button = new JButton("click");
        button.addActionListener(this);
        name = new JTextField(8);
        JPanel p = new JPanel();
        p.add(texy);
        p.add(name);
        p.add(button);
        add(p);
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("click")) {
            readNodes();
        }
    }
    private void readNodes() {
        try {
            String[] key = name.getText().split(":");
            List<NodeData> nodes = new ArrayList<>();
            int id;
            NodeData n;
            for (String idT : key) {
                id = Integer.parseInt(idT);
                n = GAlgo.getGraph().getNode(id);
                if(n != null) {
                    nodes.add(n);
                }
                else {
                    String message = "this node " + id + "does not exist in this graph";
                    JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    return;
                }
            }
            tsp(nodes);
        }
        catch (Exception e) {
            e.printStackTrace();
            String message = "there is a problem";
            JOptionPane.showMessageDialog(new JFrame(), message, "bad", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tsp(List<NodeData> n) {
        setVisible(false);
        n = GAlgo.tsp(n);
        if (n != null) {
            String m = "";
            for (NodeData j : n) {
                m = m+ j.getKey() + "----> ";
            }
            JOptionPane.showMessageDialog(new JFrame(), m, "tsp", JOptionPane.DEFAULT_OPTION);
            this.dispose();
        }
        else{
            String m = "no path,try again";
            JOptionPane.showMessageDialog(new JFrame(), m, "tsp", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }

    }
}
