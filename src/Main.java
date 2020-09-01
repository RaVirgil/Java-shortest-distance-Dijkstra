


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.sound.midi.Soundbank;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import java.util.*;

public class Main  {
    public static void main(String[] args) {


        ArrayList<Nod>graphNodes=new ArrayList<>();
        ArrayList<Edge>graphEdges=new ArrayList<>();

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder build= null;
        try {
            build = factory.newDocumentBuilder();
            Document file=build.parse("map2.xml");
            NodeList nodes=file.getElementsByTagName("node");
            NodeList edges=file.getElementsByTagName("arc");
            Element temp;
            for(int i=0;i<nodes.getLength();i++){
                graphNodes.add(new Nod(Integer.parseInt((nodes.item(i)).getAttributes().getNamedItem("id").getNodeValue())
                                      ,Integer.parseInt((nodes.item(i)).getAttributes().getNamedItem("longitude").getNodeValue())
                                      ,Integer.parseInt((nodes.item(i)).getAttributes().getNamedItem("latitude").getNodeValue())));

            }
            for(int i=0;i<edges.getLength();i++){
                graphEdges.add(new Edge(graphNodes.get(Integer.parseInt(edges.item(i).getAttributes().getNamedItem("from").getNodeValue())),
                                        graphNodes.get(Integer.parseInt(edges.item(i).getAttributes().getNamedItem("to").getNodeValue())),
                                        Integer.parseInt((edges.item(i).getAttributes().getNamedItem("length").getNodeValue()))));

            }

            Dijkstra dijkstra = new Dijkstra(graphNodes,graphEdges);
            dijkstra.Run(graphNodes.get(1));
            LinkedList<Nod> path = dijkstra.getPath(graphNodes.get(10));
            System.out.println(path);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






}
