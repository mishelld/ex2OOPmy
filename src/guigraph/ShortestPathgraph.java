package guigraph;

import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShortestPathgraph extends JFrame implements ActionListener {
    private JTextField src;
    private JTextField dest;
    private JButton button;
    private JLabel text;
    private JLabel textD;
    private DirectedWeightedGraphAlgorithms Galgo;
    public ShortestPathgraph(DirectedWeightedGraphAlgorithms g) {
        super("Shortest Path Dist");
        this.Galgo = g;
        text = new JLabel("write the Src node:");
        textD = new JLabel("write the Dest node:");
        button = new JButton("click");
        button.addActionListener(this);
        src = new JTextField(10);
        dest = new JTextField(10);
        JPanel p = new JPanel();
        p.add(text);
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
            shortdist();
        }
    }

    private void shortdist() {
        setVisible(false);
        try {
            int src = Integer.parseInt(this.src.getText());
            int dest = Integer.parseInt(this.dest.getText());
            List<NodeData> path = Galgo.shortestPath(src, dest);
            String j;
            if (!path.isEmpty()) {
                j = "the dist Between " + src + " and " + dest + " Is:\n";
                for (NodeData n : path) {
                    j += n.getKey() + "--->";
                }
                JOptionPane.showMessageDialog(null, j);

            }
            else {
                JOptionPane.showMessageDialog(null,"There Is No Path Between " + src + " and " + dest);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something is wrong");
        }
        this.dispose();
    }
}
