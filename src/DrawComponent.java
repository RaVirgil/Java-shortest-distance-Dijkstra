import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawComponent extends JComponent {
   // ArrayList<Nod> graphNodes=new ArrayList<>();
    ArrayList<Edge>graphEdges=new ArrayList<>();
    public DrawComponent(/*ArrayList<Nod>nod*/ArrayList<Edge>edge) {
       // graphNodes=nod;
        graphEdges=edge;
    }
    public void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        graphEdges.forEach(e->{
            g2.draw(new Line2D.Double(e.start.x,e.start.y,e.finish.x,e.finish.y));
        });
    }
}
