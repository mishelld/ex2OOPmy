package guigraph;

import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class giveNode extends JFrame implements ActionListener {
    private JTextField key;
    private JButton button;
    private JLabel text;
    private DirectedWeightedGraph g;
    public giveNode(DirectedWeightedGraph graph) {
        super("give Node");
        this.g = graph;
        text = new JLabel("write the Node:");
        button = new JButton("click");
        button.addActionListener(this);
        key = new JTextField(10);
        JPanel p = new JPanel();
        p.add(text);
        p.add(key);
        p.add(button);
        add(p);
        pack();
        setResizable(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("click")) {
            givenode();
        }
    }
    private void givenode() {
        setVisible(false);
        try {
            NodeData node = g.getNode(Integer.parseInt(key.getText()));
            if (node != null) {
                JOptionPane.showMessageDialog(null, "Node in Key: " + node.getKey() + "\n GEO Location: " + node.getLocation().x()+","+node.getLocation().y()+","+node.getLocation().z());
            }
            else {
                JOptionPane.showMessageDialog(null, "node not found");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "something went wrong");
        }
        this.dispose();
    }
}
