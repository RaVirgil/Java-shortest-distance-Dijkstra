import java.util.*;

public class Dijkstra {
    private final List<Nod> nodes;
    private final List<Edge> edges;
    private Set<Nod> settledNodes=new HashSet<>();
    private Set<Nod> unSettledNodes=new HashSet<>();
    private Map<Nod, Nod> predecessors;
    private Map<Nod, Integer> distance;

    public Dijkstra(ArrayList<Nod> nodes,ArrayList<Edge>edges) {

        this.nodes = new ArrayList<>(nodes);
        this.edges = new ArrayList<>(edges);
    }
 

    public void Run(Nod source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Nod node = Min(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            MinDistance(node);
        }
    }

    private void MinDistance(Nod node) {
        List<Nod> adjacentNodes = Neighbors(node);
        for (Nod target : adjacentNodes) {
            if (ShortestDistance(target) > ShortestDistance(node)
                    + Distance(node, target)) {
                distance.put(target, ShortestDistance(node)
                        + Distance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int Distance(Nod node, Nod target) {
        for (Edge i : edges) {
            if (i.start.equals(node)
                    && i.finish.equals(target)) {
                return i.cost;
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Nod> Neighbors(Nod node) {
        List<Nod> neighbors = new ArrayList<>();
        for (Edge i : edges) {
            if (i.start.equals(node)
                    && !settledNodes.contains(i.finish)) {
                neighbors.add(i.finish);
            }
        }
        return neighbors;
    }

    private Nod Min(Set<Nod> Nodes) {
        Nod minimum = null;
        for (Nod i : Nodes) {
            if (minimum == null) {
                minimum = i;
            } else {
                if (ShortestDistance(i) < ShortestDistance(minimum)) {
                    minimum = i;
                }
            }
        }
        return minimum;
    }


    private int ShortestDistance(Nod destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }


    public LinkedList<Nod> getPath(Nod target) {
        LinkedList<Nod> path = new LinkedList<>();
        Nod step = target;

        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }
}
