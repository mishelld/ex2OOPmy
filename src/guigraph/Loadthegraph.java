package guigraph;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loadthegraph extends JFrame implements ActionListener {
    private JTextField name;
    private JButton button;
    private JLabel test;

    private DirectedWeightedGraphAlgorithms Galgo;
    private guigraph.panel panel;

    public Loadthegraph(DirectedWeightedGraphAlgorithms graphAlgo, guigraph.panel panel) {
        super("Load graph");
        this.Galgo = graphAlgo;
        this.panel = panel;
        test = new JLabel("write path of the new graph");
        button = new JButton("click");
        button.addActionListener(this);
        name = new JTextField(10);
        JPanel p = new JPanel();
        p.add(test);
        p.add(name);
        p.add(button);
        add(p);
        pack();
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
            if (Galgo.load(name.getText())) {
                panel.main(Galgo.getGraph());
                panel.repaint();
            }
            else{
                JOptionPane.showMessageDialog(null, "path not found");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),"something went wrong");
        }
        this.dispose();
    }
}
