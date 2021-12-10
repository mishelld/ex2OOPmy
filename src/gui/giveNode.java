package gui;

import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class giveNode extends JFrame implements ActionListener {
    private JTextField key;
    private JButton button;
    private JLabel text;

    private DirectedWeightedGraph graph;
    public giveNode(DirectedWeightedGraph graph) {
        super("give Node");
        this.graph = graph;
        text = new JLabel("write the Node:");
        button = new JButton("click");
        button.addActionListener(this);
        key = new JTextField(8);
        JPanel p = new JPanel();
        p.add(text);
        p.add(key);
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
            closeWindow();
        }
    }
    
    private void closeWindow() {
        setVisible(false);
        try {
            NodeData node = graph.getNode(Integer.parseInt(key.getText()));
            if (node != null) {
                String m = "Node:\nKey: " + node.getKey() + "\nLocation: " + node.getLocation();
                JOptionPane.showMessageDialog(new JFrame(), m, "Node", JOptionPane.DEFAULT_OPTION);
            }
            else {
                String m = "node not found";
                JOptionPane.showMessageDialog(new JFrame(), m, "error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e) {
            String message = "something went wrong";
            JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
        }
        
        this.dispose();
    }
}
