package gui;

import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShortestPathgraph extends JFrame implements ActionListener {
    private JTextField src;
    private JTextField dest;
    private JButton button;
    private JLabel text;
    private JLabel textD;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    public ShortestPathgraph(DirectedWeightedGraphAlgorithms graphAlgo) {
        super("Shortest Path Dist");
        this.graphAlgo = graphAlgo;
        text = new JLabel("write the Src node:");
        textD = new JLabel("write the Dest node:");
        button = new JButton("click");
        button.addActionListener(this);
        src = new JTextField(8);
        dest = new JTextField(8);
        JPanel p = new JPanel();
        p.add(text);
        p.add(src);
        p.add(textD);
        p.add(dest);
        p.add(button);

        p.setPreferredSize(new Dimension(140, 100));
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
            closeWindow();
        }
    }

    private void closeWindow() {
        setVisible(false);
        try {
            int src = Integer.parseInt(this.src.getText());
            int dest = Integer.parseInt(this.dest.getText());
            List<NodeData> path = graphAlgo.shortestPath(src, dest);
            String j;
            if (!path.isEmpty()) {
                j = "the dist Between " + src + " and " + dest + " Is:\n";
                for (NodeData nodeData : path) {
                    j += nodeData.getKey() + "--->";
                }
            }
            else {
                j = "There Is No Path Between " + src + " and " + dest;

            }
            JOptionPane.showMessageDialog(new JFrame(), j, "path", JOptionPane.DEFAULT_OPTION);
        }
        catch (Exception e) {
            e.printStackTrace();
            String message = "Something is wrong";
            JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
