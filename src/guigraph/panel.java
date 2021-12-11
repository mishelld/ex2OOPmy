package guigraph;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import codes.GeoLocationImpl;
import codes.NodeDataImpl;

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
    private DirectedWeightedGraph g;
    public panel(DirectedWeightedGraph graph) {
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(Color.black);
        this.setFocusable(true);
        main(graph);
    }
    public void main(DirectedWeightedGraph graph) {
        this.g = graph;
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

    public void paint(Graphics g) {
        super.paint(g);
        sumx = this.getWidth() / Math.abs(biggestvaluex - smallestvaluex) * 0.888;
        sumy = this.getHeight() / Math.abs(biggestvaluey - smallestvalusey) * 0.888;
        Iterator<EdgeData> iter = this.g.edgeIter();
        for (Iterator<EdgeData> it3 = iter; it3.hasNext(); ) {
            EdgeData edge = it3.next();
            double firstx = this.g.getNode(edge.getSrc()).getLocation().x();
            firstx = ((firstx - smallestvaluex) * sumx) + 10;
            double firsty = this.g.getNode(edge.getSrc()).getLocation().y();
            firsty = ((firsty - smallestvalusey) * sumy) + 10;
            double endx = this.g.getNode(edge.getDest()).getLocation().x();
            endx = ((endx - smallestvaluex) * sumx) + 10;
            double endy = this.g.getNode(edge.getDest()).getLocation().y();
            endy = ((endy - smallestvalusey) * sumy) + 10;
            g.setColor(Color.orange);
            int distanceofx = (int)endx -(int) firstx;
            int  distancey = (int)endy - (int)firsty;
            double distance = Math.sqrt(distanceofx * distanceofx + distancey * distancey);
            double dist = distance - 20;
            double distn = dist;
            double disty = 20;
            double distx = -20 ;
            double p;
            double engles = distancey / distance;
            double englec = distanceofx / distance;
            p = dist * englec - disty * engles + firstx;
            disty = dist * engles + disty * englec + firsty;
            dist = p;
            p = distn * englec - distx * engles + firstx;
            distx = distn * engles + distx * englec + firsty;
            distn = p;
            int[] xpoints = {(int)endx, (int) dist, (int) distn};
            int[] ypoints = {(int)endy, (int) disty, (int) distx};
            g.drawLine((int)firstx, (int)firsty, (int)endx,(int) endy);
            g.fillPolygon(xpoints, ypoints, 3);
            int first = (int) ( endx*0.7+firstx*0.2);
            int sec = (int)(endy*0.7+firsty*0.2 );
            String s = edge.getWeight() + "";
            g.setColor(Color.blue);
            g.drawString(s,first,sec);
        }
        Iterator<NodeData> iterator = this.g.nodeIter();
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
    public void addNode(int key, int x, int y) {
        y -= 50;
        x-=5;
        double newX = (x-12)/sumx + smallestvaluex;
        double newY = (y-12)/sumy + smallestvalusey;
        GeoLocationImpl GOO = new GeoLocationImpl(newX,newY,0);
        g.addNode(new NodeDataImpl(GOO,key));
        repaint();
    }
}
