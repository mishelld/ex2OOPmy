package gui;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Savethegraph extends JFrame implements ActionListener {
    private JTextField name;
    private JButton button;
    private JLabel text;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    public Savethegraph(DirectedWeightedGraphAlgorithms graphAlgo) {
        super("save graph");
        this.graphAlgo = graphAlgo;
        text = new JLabel("Name Of The New File:");
        button = new JButton("Enter");
        button.addActionListener(this);
        name = new JTextField(8);
        JPanel p = new JPanel();
        p.add(text);
        p.add(name);
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
        if (s.equals("Enter")) {
            save();
        }
    }

    private void save() {
        setVisible(false);
        try {
            if (graphAlgo.save(name.getText())) {
                String m = "the graph was saved";
                JOptionPane.showMessageDialog(new JFrame(), m, "save", JOptionPane.DEFAULT_OPTION);

            }
            else {
            String f = "the graph was not saved";
            JOptionPane.showMessageDialog(new JFrame(), f, "not saved", JOptionPane.ERROR_MESSAGE);
        }

        }
        catch (Exception e) {
            String d = "something went wrong ";
            JOptionPane.showMessageDialog(new JFrame(), d, "error", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
