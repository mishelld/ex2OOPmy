package guigraph;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class removetheedge extends JFrame implements ActionListener {
        private JTextField src;
        private JTextField dest;
        private JButton button;
        private JLabel textS;
        private JLabel textD;

        private DirectedWeightedGraph g;
        private guigraph.panel p;
        public removetheedge(DirectedWeightedGraph ga, guigraph.panel pa) {
            super("Remove the Edge");
            this.g = ga;
            this.p = pa;
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
                if (g.removeEdge(src, dest) != null) {
                    g.removeEdge(src, dest);
                    p.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null,"edge not found");
                }

         }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "something went wrong");
            }
            this.dispose();
        }
    }


