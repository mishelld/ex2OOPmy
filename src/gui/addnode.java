package gui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addnode extends JFrame implements ActionListener {
        private JTextField inputKey;
        private JButton button;
        private JLabel textKey;

        private DirectedWeightedGraph graph;
        private gui.panel panel;
        private int x, y;

        // default constructor
        public addnode(DirectedWeightedGraph graph, gui.panel panel, int x, int y) {
            // create a new frame to store text field and button
            super("Add Node");
            this.graph = graph;
            this.panel = panel;
            this.x = x;
            this.y = y;
            textKey = new JLabel("write the node:");
            button = new JButton("click");
            button.addActionListener(this);
            inputKey = new JTextField(8);

            JPanel p = new JPanel();
            p.add(textKey);
            p.add(inputKey);
            p.add(button);

            add(p);

            pack();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            setResizable(true);
            setVisible(true);
        }

        // if the button is pressed
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
