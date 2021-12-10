package gui;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loadthegraph extends JFrame implements ActionListener {
    private JTextField name;
    private JButton button;
    private JLabel test;

    private DirectedWeightedGraphAlgorithms graphAlgo;
    private gui.panel panel;

    // default constructor
    public Loadthegraph(DirectedWeightedGraphAlgorithms graphAlgo, gui.panel panel) {
        super("Load graph");
        this.graphAlgo = graphAlgo;
        this.panel = panel;
        test = new JLabel("write path of the new graph");
        button = new JButton("click");
        button.addActionListener(this);

        name = new JTextField(8);
        JPanel p = new JPanel();
        p.add(test);
        p.add(name);
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
            load();
        }

    }

    private void load() {
        setVisible(false);
        try {
            if (graphAlgo.load(name.getText())) {
                panel.init(graphAlgo.getGraph());
                panel.repaint();
            }
            else{
                String message = "path not found";
                JOptionPane.showMessageDialog(new JFrame(), message, "eroor", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e) {
            String message = "something went wrong";
            JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
