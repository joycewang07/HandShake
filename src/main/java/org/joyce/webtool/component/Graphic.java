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
    private int graphicSize = 5;
    private int maxWeight = 100;
    private int maxEdgeCount = 3;

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
        private List<Edge> outEdge = new LinkedList<>();
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    public void graphicAssignment () {
        List<Vertex> graphic = createGraphicRandom();

    }

    private List<Vertex> createGraphicRandom () {
        Vertex[] vertexArray = new Vertex[graphicSize];
        for (int i = 0; i < graphicSize; i++)
            vertexArray[i] = null;

        for (int vertexId = 0; vertexId < graphicSize; vertexId++) {
            int edgeCount = random(0, maxEdgeCount);
            vertexArray[vertexId] = new Vertex(vertexId);
            for (int j = 0; j < edgeCount; j++) {
                int weight = random(0, maxWeight);
                while (true) {
                    int destinationId;
                    while(true) {
                        destinationId = random(0, graphicSize);
                        if (destinationId != vertexId)
                            break;
                    }
                    Edge newEdge = vertexArray[vertexId].addOutEdge(new Edge(weight, destinationId));
                    if (!traverse(vertexArray)) {
                        vertexArray[vertexId].getOutEdge().remove(newEdge);
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }
        List<Vertex> graphic = new ArrayList<>(graphicSize);
        for (Vertex vertex : vertexArray)
            graphic.add(vertex);

        return graphic;
    }

    private int random (int min, int max) {
        Random random = new Random();

        return random.nextInt(max)%(max - min +1 ) + min;
    }


    private List<Vertex> createGraphicManual () {

    }

    private boolean traverse (Vertex[] graphic) {
        List<Integer> vertexQueue = new LinkedList<>();
        // traverse from first one
        vertexQueue.add(0);
        Integer queuePointer = 0;
        while (queuePointer < vertexQueue.size()) {
            Vertex vertex;
            if (graphic[queuePointer] == null) {
                ++queuePointer;
                continue;
            } else {
                vertex = graphic[queuePointer];
            }

            if (vertex.isAccess()) {
                return false;
            } else {
                vertex.setAccess(true);
                if (vertex.getOutEdge().size() == 0) {
                    ++queuePointer;
                    continue;
                } else {
                    ++queuePointer;
                    for (Edge edge : vertex.getOutEdge())
                        vertexQueue.add(edge.getDestinationId());
                }
            }
        }
        return true;
    }

    private void resetAccessFlag (List<Vertex> graphic) {
        for (Vertex vertex : graphic)
            vertex.setAccess(false);
    }

}
