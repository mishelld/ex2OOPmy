package guigraph;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Savethegraph extends JFrame implements ActionListener {
    private JTextField name;
    private JButton button;
    private JLabel text;

    private DirectedWeightedGraphAlgorithms Galgo;

    public Savethegraph(DirectedWeightedGraphAlgorithms g) {
        super("save graph");
        this.Galgo = g;
        text = new JLabel("Name Of The New File:");
        button = new JButton("click");
        button.addActionListener(this);
        name = new JTextField(10);
        JPanel p = new JPanel();
        p.add(text);
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
            save();
        }
    }
    private void save() {
        setVisible(false);
        try {
            if (Galgo.save(name.getText())) {
                Galgo.save(name.getText());
                JOptionPane.showMessageDialog(null, "the graph was saved");
            }
            else {
            JOptionPane.showMessageDialog(null, "the graph was not saved");
        }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "something went wrong ");
        }
        this.dispose();
    }
}
