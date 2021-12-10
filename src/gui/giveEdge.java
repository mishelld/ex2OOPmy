package gui;

import api.DirectedWeightedGraph;
import api.EdgeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class giveEdge extends JFrame implements ActionListener {
    private JTextField inputS;
    private JTextField inputD;
    private JButton button;
    private JLabel textS;
    private JLabel textD;

    private DirectedWeightedGraph graph;

    public giveEdge(DirectedWeightedGraph graph) {
        super("give Edge");
        this.graph = graph;
        textS = new JLabel("write the Src node:");
        textD = new JLabel("write the Dest node:");
        button = new JButton("click");
        button.addActionListener(this);
        inputS = new JTextField(8);
        inputD = new JTextField(8);
        JPanel p = new JPanel();
        p.add(textS);
        p.add(inputS);
        p.add(textD);
        p.add(inputD);
        p.add(button);

        p.setPreferredSize(new Dimension(140, 100));
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
            giveedge();
        }
    }

    private void giveedge() {
        setVisible(false);
        try {
            EdgeData edge = graph.getEdge(Integer.parseInt(inputS.getText()), Integer.parseInt(inputD.getText()));
            if (edge != null) {
                String f = "Edge:\nSrc: " + edge.getSrc() + "\nDest: " + edge.getDest() + "\nWeight: " + edge.getWeight();
                JOptionPane.showMessageDialog(new JFrame(), f, "Edge", JOptionPane.DEFAULT_OPTION);
            }
            else {
                String m = "there is not such edge";
                JOptionPane.showMessageDialog(new JFrame(), m, "error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e) {
            String message = "something went wrong";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
