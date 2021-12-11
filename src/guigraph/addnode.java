package guigraph;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addnode extends JFrame implements ActionListener {
        private JTextField inputKey;
        private JButton button;
        private JLabel textKey;

        private DirectedWeightedGraph graph;
        private guigraph.panel panel;
        private int x, y;

        public addnode(DirectedWeightedGraph graph, guigraph.panel panel, int x, int y) {
            super("Add Node");
            this.graph = graph;
            this.panel = panel;
            this.x = x;
            this.y = y;
            textKey = new JLabel("write the node:");
            button = new JButton("click");
            button.addActionListener(this);
            inputKey = new JTextField(10);
            JPanel p = new JPanel();
            p.add(textKey);
            p.add(inputKey);
            p.add(button);
            add(p);
            pack();
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
                int key = Integer.parseInt(inputKey.getText());
                panel.addNode(key, x, y);
            }
            catch (Exception e) {
                String message = "Something Gets Wrong :(";
                JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();

    }

}
