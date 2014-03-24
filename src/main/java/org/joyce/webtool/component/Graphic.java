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
    private int graphicSize = 6;
    private int maxWeight = 100;
    private int maxEdgeCount = 6;

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
        private Map<Integer, Edge> outEdge = new HashMap<>();
        private boolean access = false;

        private Vertex(int id) {
            this.id = id;
        }

        private Edge addOutEdge (Edge edge) {
            if (edge != null)
                outEdge.put(edge.getDestinationId(), edge);

            return edge;
        }

        public Map<Integer, Edge> getOutEdge() {
            return outEdge;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
       //List<Vertex> graphic = createGraphicManual();
    }

    private List<Vertex> createGraphicRandom () {
        // initialize
        Vertex[] vertexArray = new Vertex[graphicSize];
        for (int i = 0; i < graphicSize; i++)
            vertexArray[i] = new Vertex(i);

        for (int vertexId = 0; vertexId < graphicSize; vertexId++) {
            int edgeCount = random(0, maxEdgeCount);
            vertexArray[vertexId] = new Vertex(vertexId);
            for (int j = 0; j < edgeCount; j++) {
                int weight = random(0, maxWeight);
                // for detecting the dead lock situation
                Set<Integer> detectionHistory = new HashSet<>();
                while (true) {
                    int destinationId;
                    while(true) {
                        destinationId = random(0, graphicSize);
                        if (destinationId != vertexId && !vertexArray[vertexId].getOutEdge().containsKey(destinationId) && !detectionHistory.contains(destinationId))
                            break;
                    }
                    vertexArray[vertexId].addOutEdge(new Edge(weight, destinationId));
                    if (!checkCircle(vertexArray)) {
                        vertexArray[vertexId].getOutEdge().remove(destinationId);
                        detectionHistory.add(destinationId);
                        if (detectionHistory.size() >= edgeCount - 1) {
                            System.out.println("Can't find proper edge, perform next vertex. vertex id: " + vertexId);
                            // abandon
                            break;
                        }
                        // next try
                        continue;
                    } else {
                        // success
                        graphicOutput(vertexArray);
                        detectionHistoryOutput(detectionHistory);
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
        List<Vertex> manualVertex = new ArrayList<>(5);
        Vertex v0 = new Vertex(0);
        v0.addOutEdge(new Edge(3, 1));

        Vertex v1 = new Vertex(1);
        v1.addOutEdge(new Edge(3, 2));

        Vertex v2 = new Vertex(2);
        v2.addOutEdge(new Edge(3, 3));
        v2.addOutEdge(new Edge(3, 4));

        Vertex v3 = new Vertex(3);
        //v3.addOutEdge(new Edge(3, 4));
        //v3.addOutEdge(new Edge(3, 1));

        Vertex v4 = new Vertex(4);
        //v4.addOutEdge(new Edge(3, 5));

        Vertex v5 = new Vertex(5);
        //v5.addOutEdge(new Edge(3, 2));

        manualVertex.add(v0);
        manualVertex.add(v1);
        manualVertex.add(v2);
        manualVertex.add(v3);
        manualVertex.add(v4);
        manualVertex.add(v5);

        Vertex[] graphic = new Vertex[6];
        for (int i = 0; i < 6; i++)
            graphic[i] = manualVertex.get(i);

        boolean result = checkCircle(graphic);
        return manualVertex;
    }

    private boolean checkCircle (Vertex[] graphic) {
        for (Vertex startVertex : graphic) {
            if (startVertex.getOutEdge().size() == 0) {
                startVertex.setAccess(true);
            } else {
                Stack<Vertex> vertexStack = new Stack<>();
                vertexStack.push(startVertex);
                while (!vertexStack.empty()) {
                    Vertex vertex = vertexStack.pop();
                    if (vertex.getId() == startVertex.getId() && vertex.isAccess())
                        return false;

                    if (!vertex.isAccess()) {
                        vertex.setAccess(true);
                        Iterator iterator = vertex.getOutEdge().entrySet().iterator();
                        while (iterator.hasNext()) {
                            int edgeDestination = (Integer) ((Map.Entry) iterator.next()).getKey();
                            vertexStack.add(graphic[edgeDestination]);
                        }
                    }
                }
                resetAccessFlag(graphic);
            }
        }
        return true;
    }

    private void resetAccessFlag (Vertex[] graphic) {
        for (Vertex vertex : graphic)
            if (vertex != null)
                vertex.setAccess(false);
    }

    private void graphicOutput (Vertex[] graphic) {
        System.out.println("--------Graphic--------");
        for (Vertex vertex : graphic) {
            System.out.print(vertex.getId() + "(" + vertex.isAccess() + ")" +": " );
            Iterator iterator = vertex.getOutEdge().entrySet().iterator();
            while (iterator.hasNext()) {
                int edgeDestination = (Integer) ((Map.Entry) iterator.next()).getKey();
                System.out.print(edgeDestination + " ");
            }
            System.out.println("|");
        }
        System.out.println("--------Graphic--------");
    }

    private void detectionHistoryOutput (Set<Integer> detectionHistory) {
        Iterator<Integer> iterator = detectionHistory.iterator();
        System.out.println("--------Detection History--------");
        while (iterator.hasNext()) {
            int detection = iterator.next();
            System.out.print(detection + " ");
        }
        System.out.println("|");
        System.out.println("--------Detection History--------");
    }

}