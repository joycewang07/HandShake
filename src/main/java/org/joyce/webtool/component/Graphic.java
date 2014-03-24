package org.joyce.webtool.component;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by junyan Zhang on 14-3-23.
 */

@Controller
@RequestMapping(value="/graphic")
public class Graphic {
    private int graphicSize = 20;
    private int maxWeight = 100;

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

        private Edge addOutEdge (Edge edge) {
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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String graphicAssignment () {
        List<List<Integer>> graphic = new ArrayList<>(graphicSize);

    }

    private List<Vertex> createGraphicRandom () {
        List<Vertex> graphic = new ArrayList<>(graphicSize);
        Random random = new Random();
        for (Vertex vertex : graphic) {
            int vertexCount = random.nextInt(graphicSize - 1) % (graphicSize);
            for (int i = 0; i < vertexCount; i++) {
                vertex = new Vertex(i);
                int destinationId = random.nextInt(graphicSize) % (graphicSize + 1);
                if ()
                vertex.addOutEdge()
            }
        }

    }

    private List<Vertex> createGraphicManual () {

    }

    private boolean traverse (Vertex startVertex) {
        List<Vertex> vectorQueue = new LinkedList<>();
        vectorQueue.add(startVertex);
        Iterator iterator = vectorQueue.iterator();
        while (iterator.hasNext()) {
            Vertex vertex = (Vertex)iterator.next();
            if (vertex.access == true) {
                return false;
            } else {
                for (Edge edge : vertex.getOutEdge())
                    vectorQueue.add(edge.getDestinationId());
            }
        }
        return true;
    }

    private void resetAccessFlag (List<Vertex> graphic) {
        for (Vertex vertex : graphic)
            vertex.setAccess(false);
    }

}
