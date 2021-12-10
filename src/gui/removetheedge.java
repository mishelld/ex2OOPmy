package gui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class removetheedge extends JFrame implements ActionListener {
        private JTextField src;
        private JTextField dest;
        private JButton button;
        private JLabel textS;
        private JLabel textD;

        private DirectedWeightedGraph graph;
        private gui.panel panel;
        public removetheedge(DirectedWeightedGraph graph, gui.panel panel) {
            super("Remove the Edge");
            this.graph = graph;
            this.panel = panel;
            textS = new JLabel("write the Src node of the edge:");
            textD = new JLabel("write the dest node of the edge:");
            button = new JButton("click");
            button.addActionListener(this);
            src = new JTextField(16);

            dest = new JTextField(16);
            JPanel p = new JPanel();
            p.add(textS);
            p.add(src);
            p.add(textD);
            p.add(dest);
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
                remove();
            }
        }

        private void remove() {
            setVisible(false);
            try {
                int src = Integer.parseInt(this.src.getText());
                int dest = Integer.parseInt(this.dest.getText());
                if (graph.removeEdge(src, dest) != null) {
                    graph.removeEdge(src, dest);
                    panel.repaint();
                }
                else{
                    String message = "edge not found";
                    JOptionPane.showMessageDialog(new JFrame(), message, "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }

         }
            catch (Exception e) {
                String message = "something went wrong";
                JOptionPane.showMessageDialog(new JFrame(), message, "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
    }


