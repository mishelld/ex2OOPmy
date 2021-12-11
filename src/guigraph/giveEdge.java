package guigraph;

import api.DirectedWeightedGraph;
import api.EdgeData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class giveEdge extends JFrame implements ActionListener {
    private JTextField inputS;
    private JTextField inputD;
    private JButton button;
    private JLabel textS;
    private JLabel textD;

    private DirectedWeightedGraph g;

    public giveEdge(DirectedWeightedGraph graph) {
        super("give Edge");
        this.g = graph;
        textS = new JLabel("write the Src node:");
        textD = new JLabel("write the Dest node:");
        button = new JButton("click");
        button.addActionListener(this);
        inputS = new JTextField(10);
        inputD = new JTextField(10);
        JPanel p = new JPanel();
        p.add(textS);
        p.add(inputS);
        p.add(textD);
        p.add(inputD);
        p.add(button);
        add(p);
        pack();
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
            EdgeData thedge = g.getEdge(Integer.parseInt(inputS.getText()), Integer.parseInt(inputD.getText()));
            if (thedge != null) {
                JOptionPane.showMessageDialog(null, "the edge info:Src: " + thedge.getSrc() + ", dest: " + thedge.getDest() + ", weight: " + thedge.getWeight());
            }
            else {
                JOptionPane.showMessageDialog(null, "there is not such edge");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "something went wrong");
        }
        this.dispose();
    }
}
