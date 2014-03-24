package org.joyce.webtool.component;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;
import java.util.*;

/**
 * Created by junyan Zhang on 14-3-23.
 */

@Controller
@RequestMapping(value = "/graphic")
public class Graphic {
    private int graphicSize = 20;
    private int maxWeight = 100;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String graphicAssignment() {
        List<List<Integer>> graphic = new ArrayList<>(graphicSize);
        createGraphicManual();
        //print out the sorted topo list
//        for(int i=0;i<topo().length;i++){
//        System.out.print(topo(i));
//        }
        return topo().toString();
    }

    private List<Vertex> createGraphicRandom() {
        List<Vertex> graphic = new ArrayList<>(graphicSize);
        Random random = new Random();
        for (Vertex vertex : graphic) {
            int vertexCount = random.nextInt(graphicSize - 1) % (graphicSize);
            for (int i = 0; i < vertexCount; i++) {
                vertex = new Vertex(i);
                int destinationId = random.nextInt(graphicSize) % (graphicSize + 1);

            }
        }

    }

    private List<Vertex> createGraphicManual() {
        List<Vertex> manualGraph = new ArrayList<>(5);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        Edge e14 = new Edge(1, 4);
        Edge e12 = new Edge(1, 2);

        Edge e23 = new Edge(1, 3);
        Edge e24 = new Edge(1, 4);
        Edge e25 = new Edge(1, 5);

        v1.outEdge.add(e14);
        v1.outEdge.add(e12);

        v2.outEdge.add(e24);
        v2.outEdge.add(e23);
        v2.outEdge.add(e25);


        manualGraph.add(v1);
        manualGraph.add(v2);
        manualGraph.add(v3);
        manualGraph.add(v4);
        manualGraph.add(v5);

        return manualGraph;

    }

    private Vertex[] topo() {

        int size = createGraphicManual().size();
        Vertex[] sorted = new Vertex[size - 1];
        for (Vertex v : createGraphicManual()) {
            if (v.outEdge.isEmpty()) {
                sorted[size - 1] = v;
                createGraphicManual().remove(v);
            }
        }return sorted;
    }

    private boolean traverse(Vertex startVertex) {
        List<Vertex> vectorQueue = new LinkedList<>();
        vectorQueue.add(startVertex);
        Iterator iterator = vectorQueue.iterator();
        while (iterator.hasNext()) {
            Vertex vertex = (Vertex) iterator.next();
            if (vertex.access == true) {
                return false;
            } else {
                for (Edge edge : vertex.getOutEdge())
                  //  vectorQueue.add(edge.getDestinationId());
            }
        }
        return true;
    }

    private void resetAccessFlag(List<Vertex> graphic) {
        for (Vertex vertex : graphic)
            vertex.setAccess(false);
    }

    private class Edge {
        private int weight;
        private int destinationId;

        private Edge(int weight, int destinationId) {
            this.weight = weight;
            this.destinationId = destinationId;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getDestinationId() {
            return destinationId;
        }

        public void setDestinationId(int destinationId) {
            this.destinationId = destinationId;
        }
    }

    private class Vertex {
        private int id;
        private List<Edge> outEdge;
        private boolean access = false;

        private Vertex(int id) {
            this.id = id;
        }

        private Edge addOutEdge(Edge edge) {
            if (edge != null)
                outEdge.add(edge);

            return edge;
        }

        public List<Edge> getOutEdge() {
            return outEdge;
        }

        public void setOutEdge(List<Edge> outEdge) {
            this.outEdge = outEdge;
        }

        public boolean isAccess() {
            return access;
        }

        public void setAccess(boolean access) {
            this.access = access;
        }
    }

}
