package gui;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShortestPathDistance extends JFrame implements ActionListener {
    private JTextField inputSrc;
    private JTextField inputDest;
    private JButton button;
    private JLabel textSrc;
    private JLabel textDest;

    private DirectedWeightedGraphAlgorithms graphAlgo;

    public ShortestPathDistance(DirectedWeightedGraphAlgorithms graphAlgo) {
        super("Shortest Path Dist");
        this.graphAlgo = graphAlgo;
        textSrc = new JLabel("write the Src node:");
        textDest = new JLabel("write the Dest node:");
        button = new JButton("click");
        button.addActionListener(this);
        inputSrc = new JTextField(8);

        inputDest = new JTextField(8);

        JPanel p = new JPanel();
        p.add(textSrc);
        p.add(inputSrc);
        p.add(textDest);
        p.add(inputDest);
        p.add(button);

        p.setPreferredSize(new Dimension(140, 100));
        add(p);

        // set the size of frame
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
            shortestpathdist();
        }
    }

    private void shortestpathdist() {
        // set the text of the label to the text of the field
        setVisible(false);
        try {
            int src = Integer.parseInt(inputSrc.getText());
            int dest = Integer.parseInt(inputDest.getText());
            double dis = graphAlgo.shortestPathDist(src, dest);
            String h;
            if (dis != -1) {
                h = "The Distance Between " + src + " And " + dest + " Is: " + dis;
                h = "There Is No Path Between " + src + " And " + dest;
            }
            else {
                h = "There Is No Path Between " + src + " And " + dest;
            }
            JOptionPane.showMessageDialog(new JFrame(), h, "Shortest Path", JOptionPane.DEFAULT_OPTION);
        }
        catch (Exception e) {
            e.printStackTrace();
            String message = "Something is Wrong ";
            JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }
}
