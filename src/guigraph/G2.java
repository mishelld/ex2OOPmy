package guigraph;


import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class G2 extends JFrame implements ActionListener {
    private DirectedWeightedGraphAlgorithms Galgo;
    private guigraph.panel pan;
    private JMenuBar mb;
    private JMenu t;
    private JMenuItem addNode;
    private JMenuItem removeNode;
    private JMenuItem removeEdge;
    private JMenuItem connect;
    private boolean add = false;

    public G2(DirectedWeightedGraphAlgorithms b) {
        super();
        Galgo = b;
        pan = new panel(b.getGraph());
        manu();
        this.add(pan);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
    }

    private void manu() {
        t = new JMenu("click me for more options");
        addNode = new JMenuItem("Add Node");
        addNode.addActionListener(this);
        removeNode = new JMenuItem("Remove Node");
        removeNode.addActionListener(this);
        removeEdge = new JMenuItem("Remove Edge");
        removeEdge.addActionListener(this);
        connect = new JMenuItem("connect");
        connect.addActionListener(this);
        t.add(addNode);
        t.add(removeNode);
        t.add(removeEdge);
        t.add(connect);
        mb = new JMenuBar();
        mb.add(t);
        setJMenuBar(mb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeNode) {
            new removethenode(Galgo.getGraph(), pan);
        } else if (e.getSource() == removeEdge) {
            new removetheedge(Galgo.getGraph(), pan);
        } else if (e.getSource() == connect) {
            new connecting(Galgo.getGraph(), pan);

        }

    }
}
