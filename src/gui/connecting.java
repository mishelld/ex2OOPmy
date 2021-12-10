package gui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class connecting extends JFrame implements ActionListener {
        private JTextField src;
        private JTextField dest;
        private JTextField weight;
        private JButton button;
        private JLabel textS;
        private JLabel textD;
        private JLabel textW;

        private DirectedWeightedGraph graph;
        private gui.panel panel;

        // default constructor
        public connecting(DirectedWeightedGraph graph, gui.panel panel) {
            super("Connect");
            this.graph = graph;
            this.panel = panel;
            textS = new JLabel("write the Src of the node:");
            textD = new JLabel("write the Dest of the node:");
            textW = new JLabel("write the Weight of the node:");
            button = new JButton("click");
            button.addActionListener(this);
           src = new JTextField(8);

            dest = new JTextField(8);

            weight = new JTextField(8);
            JPanel p = new JPanel();
            p.add(textS);
            p.add(src);
            p.add(textD);
            p.add(dest);
            p.add(textW);
            p.add(weight);
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
                int src = Integer.parseInt(this.src.getText());
                int dest = Integer.parseInt(this.dest.getText());
                double weight = Double.parseDouble(this.weight.getText());
                graph.connect(src, dest, weight);
                panel.repaint();
            }
            catch (Exception e) {
                String message = "something went wrong";
                JOptionPane.showMessageDialog(new JFrame(), message, "JOptionPane.ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }

}
