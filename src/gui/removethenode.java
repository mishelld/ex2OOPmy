package gui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class removethenode extends JFrame implements ActionListener {
    private JTextField input;
    private JButton button;
    private JLabel text;

    private DirectedWeightedGraph graph;
    private gui.panel panel;

    public removethenode(DirectedWeightedGraph graph, gui.panel panel) {
        super("Remove the Node");
        this.graph = graph;
        this.panel = panel;
        text = new JLabel("the node you want to remove:");
        button = new JButton("click");
        button.addActionListener(this);
        input = new JTextField(5);

        JPanel p = new JPanel();
        p.add(text);
        p.add(input);
        p.add(button);

        add(p);
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(true);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("click")) {
            remove();
        }
    }

    private void remove() {
        // set the text of the label to the text of the field
        setVisible(false);
        try {
            int num = Integer.parseInt(input.getText());
            if (graph.removeNode(num) != null) {
                graph.removeNode(num);
                panel.repaint();
            } else {
                String m = "node not found";
                JOptionPane.showMessageDialog(new JFrame(), m, "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e) {
            String message = "something is wrong";
            JOptionPane.showMessageDialog(new JFrame(), message, "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();

    }
}