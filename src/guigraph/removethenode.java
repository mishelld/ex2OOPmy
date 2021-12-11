package guigraph;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class removethenode extends JFrame implements ActionListener {
    private JTextField input;
    private JButton button;
    private JLabel text;

    private DirectedWeightedGraph g;
    private guigraph.panel p;

    public removethenode(DirectedWeightedGraph ga, guigraph.panel pa) {
        super("Remove the Node");
        this.g = ga;
        this.p = pa;
        text = new JLabel("the node you want to remove:");
        button = new JButton("click");
        button.addActionListener(this);
        input = new JTextField(5);
        JPanel p = new JPanel();
        p.add(text);
        p.add(input);
        p.add(button);
        add(p);
        pack();
        setResizable(true);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("click")) {
            remove();
        }
    }
    private void remove() {
        setVisible(false);
        try {
            int num = Integer.parseInt(input.getText());
            if (g.removeNode(num) != null) {
                g.removeNode(num);
                p.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "node not found");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "something is wrong");
        }
        this.dispose();

    }
}