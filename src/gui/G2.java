package gui;

import api.DirectedWeightedGraphAlgorithms;
import gui.pop.AddNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class G2 extends JFrame implements ActionListener , MouseListener {
    private DirectedWeightedGraphAlgorithms graphAlgo;
    private gui.panel panel;
    private JMenuBar mb;

    private JMenu graphOp;
    private JMenuItem addNode;
    private JMenuItem removeNode;
    private JMenuItem removeEdge;
    private JMenuItem connect;




    private boolean needToAddNode = false;

    public G2(DirectedWeightedGraphAlgorithms ans){
        super();
        graphAlgo = ans;
        panel = new panel(ans.getGraph());
        buildBar();
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
    }
    private void buildBar() {
        graphOp = new JMenu("Graph");

        addNode = new JMenuItem("Add Node");
        addNode.addActionListener(this);

        removeNode = new JMenuItem("Remove Node");
        removeNode.addActionListener(this);
        removeEdge = new JMenuItem("Remove Edge");
        removeEdge.addActionListener(this);
        connect = new JMenuItem("connect");
        connect.addActionListener(this);




        graphOp.add(addNode);
        graphOp.add(removeNode);
        graphOp.add(removeEdge);
        graphOp.add(connect);



        mb = new JMenuBar();
        mb.add(graphOp);
        setJMenuBar(mb);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == removeNode) {
            new removethenode(graphAlgo.getGraph(), panel);
        }
        else if(e.getSource() == removeEdge) {
            new removetheedge(graphAlgo.getGraph(), panel);
        }
        else if(e.getSource() == connect){
            new connecting(graphAlgo.getGraph(),panel);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == addNode) {
            if (needToAddNode) {
                new AddNode(graphAlgo.getGraph(), panel, e.getX(), e.getY());
                needToAddNode = false;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
