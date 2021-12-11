package gui;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;


public class panel extends JPanel {
    private double smallestvaluex;
    private double smallestvalusey;
    private double biggestvaluex;
    private double biggestvaluey;
    private double sumx;
    private double sumy;
    private static final Dimension di = new Dimension(1000, 1000);
    private DirectedWeightedGraph graph;
    public panel(DirectedWeightedGraph graph) {
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(Color.black);
        this.setFocusable(true);
        main(graph);
    }
    public void main(DirectedWeightedGraph graph) {
        this.graph = graph;
        Iterator<NodeData> n = graph.nodeIter();
        NodeData nextnode = n.next();
        smallestvaluex = nextnode.getLocation().x();
        smallestvalusey = nextnode.getLocation().y();
        biggestvaluex = nextnode.getLocation().x();
        biggestvaluey = nextnode.getLocation().y();
        for (Iterator<NodeData> iterator1 = n; iterator1.hasNext(); ) {
            nextnode = n.next();
            smallestvaluex = Math.min(smallestvaluex, nextnode.getLocation().x());
            smallestvalusey = Math.min(smallestvalusey, nextnode.getLocation().y());
            biggestvaluex = Math.max(biggestvaluex, nextnode.getLocation().x());
            biggestvaluey = Math.max(biggestvaluey, nextnode.getLocation().y());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sumx = this.getWidth() / Math.abs(biggestvaluex - smallestvaluex) * 0.888;
        sumy = this.getHeight() / Math.abs(biggestvaluey - smallestvalusey) * 0.888;
        Iterator<EdgeData> iter = graph.edgeIter();
        for (Iterator<EdgeData> it3 = iter; it3.hasNext(); ) {
            EdgeData edge = it3.next();
            double firstx = graph.getNode(edge.getSrc()).getLocation().x();
            firstx = ((firstx - smallestvaluex) * sumx) + 10;
            double firsty = graph.getNode(edge.getSrc()).getLocation().y();
            firsty = ((firsty - smallestvalusey) * sumy) + 10;
            double endx = graph.getNode(edge.getDest()).getLocation().x();
            endx = ((endx - smallestvaluex) * sumx) + 10;
            double endy = graph.getNode(edge.getDest()).getLocation().y();
            endy = ((endy - smallestvalusey) * sumy) + 10;

            g.setColor(Color.orange);
            drawArrowLine(g, (int) firstx, (int) firsty, (int) endx, (int) endy, 20, 20);
            int first = (int) ( endx*0.7+firstx*0.2);
            int sec = (int)(endy*0.7+firsty*0.2 );
            String s = edge.getWeight() + "";

            g.setColor(Color.blue);
            g.drawString(s,first,sec);
        }
        Iterator<NodeData> iterator = graph.nodeIter();
        for (Iterator<NodeData> it = iterator; it.hasNext(); ) {
            NodeData n = it.next();
            double x =  ((n.getLocation().x() - smallestvaluex));
            int z = (int) (x*sumx);
            double y =  (n.getLocation().y() - smallestvalusey);
            int h =  (int) (y*sumy);;

            g.setColor(Color.green);
            g.fillOval(z, h, 30, 30);
            g.setColor(Color.red);
            g.drawString( n.getKey()+"", z +2, h +15);
        }
    }

    private void drawArrowLine(Graphics g, int a, int b, int c, int d, int e, int f) {
        int distanceofx = c - a;
        int  distancey = d - b;
        double distance = Math.sqrt(distanceofx * distanceofx + distancey * distancey);
        double dist = distance - e;
        double distn = dist;
        double disty = f;
        double distx = -f ;
        double p;
        double engles = distancey / distance;
        double englec = distanceofx / distance;
        p = dist * englec - disty * engles + a;
        disty = dist * engles + disty * englec + b;
        dist = p;

        p = distn * englec - distx * engles + a;
        distx = distn * engles + distx * englec + b;
        distn = p;

        int[] xpoints = {c, (int) dist, (int) distn};
        int[] ypoints = {d, (int) disty, (int) distx};
        g.drawLine(a, b, c, d);
        g.fillPolygon(xpoints, ypoints, 3);
    }




}
