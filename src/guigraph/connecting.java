package guigraph;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class connecting extends JFrame implements ActionListener {
        private JTextField s;
        private JTextField d;
        private JTextField w;
        private JButton button;
        private JLabel textS;
        private JLabel textD;
        private JLabel textW;
        private DirectedWeightedGraph g;
        private guigraph.panel p;
        public connecting(DirectedWeightedGraph g, guigraph.panel pa) {
            super("Connect");
            this.g = g;
            this.p = pa;
            textS = new JLabel("write the Src of the node:");
            textD = new JLabel("write the Dest of the node:");
            textW = new JLabel("write the Weight of the node:");
            button = new JButton("click");
            button.addActionListener(this);
            s = new JTextField(10);
            d = new JTextField(10);
            w = new JTextField(10);
            JPanel p = new JPanel();
            p.add(textS);
            p.add(s);
            p.add(textD);
            p.add(d);
            p.add(textW);
            p.add(w);
            p.add(button);
            add(p);
            pack();
            setResizable(true);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals("click")) {
                connect();
            }
        }
        private void connect() {
            setVisible(false);
            try {
                int s = Integer.parseInt(this.s.getText());
                int d = Integer.parseInt(this.d.getText());
                double w = Double.parseDouble(this.w.getText());
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "something went wrong");
            }
            this.dispose();
        }

}
