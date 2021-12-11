package guigraph;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShortestPathDistance extends JFrame implements ActionListener {
    private JTextField inputs;
    private JTextField inputd;
    private JButton button;
    private JLabel labels;
    private JLabel labeld;

    private DirectedWeightedGraphAlgorithms Galgo;

    public ShortestPathDistance(DirectedWeightedGraphAlgorithms g) {
        super("Shortest path Dist");
        this.Galgo = g;
        labels = new JLabel("write the Src node:");
        labeld = new JLabel("write the Dest node:");
        button = new JButton("click");
        button.addActionListener(this);
        inputs = new JTextField(10);
        inputd = new JTextField(10);
        JPanel p = new JPanel();
        p.add(labels);
        p.add(inputs);
        p.add(labeld);
        p.add(inputd);
        p.add(button);
        add(p);
        pack();
        setResizable(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("click")) {
            shortestpathdist();
        }
    }
    private void shortestpathdist() {
        setVisible(false);
        try {
            int src = Integer.parseInt(inputs.getText());
            int dest = Integer.parseInt(inputd.getText());
            double dis = Galgo.shortestPathDist(src, dest);
            String h;
            if (dis != -1) {
                JOptionPane.showMessageDialog(null,"the distance Between " + src + " and " + dest + " Is: " + dis);

            }
            else {
                JOptionPane.showMessageDialog(null,"There Is No path Between this src and dest");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something is Wrong ");
        }
        this.dispose();
    }
}
