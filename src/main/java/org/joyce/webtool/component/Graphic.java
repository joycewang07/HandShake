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
@RequestMapping(value = "/graphic")
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

        private Edge addOutEdge(Edge edge) {
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
    public void graphicAssignment() {
        topologicalSort(createGraphicManual());
        //topologicalSort(createGraphicRandom());
        //List<Vertex> graphic = createGraphicManual();
    }

    private Vertex[] createGraphicRandom() {
        // initialize
        Vertex[] vertexArray = new Vertex[graphicSize];
        for (int i = 0; i < graphicSize; i++)
            vertexArray[i] = new Vertex(i);

        for (int vertexId = 0; vertexId < graphicSize; vertexId++) {
            int edgeCount = random(0, maxEdgeCount);
            System.out.println("<< Vertex " + vertexId + " should have " + edgeCount + " edges >>");
            vertex: for (int j = 0; j < edgeCount; j++) {
                int weight = random(0, maxWeight);
                // for detecting the no-way situation(can not draw anything)
                Set<Integer> detectionHistory = new HashSet<>();
                while (true) {
                    int destinationId = 0;
                    while (true) {
                        destinationId = random(0, graphicSize);
                        if (destinationId != vertexId && !vertexArray[vertexId].getOutEdge().containsKey(destinationId)
                                && !detectionHistory.contains(destinationId))
                            break;
                    }
                    vertexArray[vertexId].addOutEdge(new Edge(weight, destinationId));
                    if (!checkCircle(vertexArray)) {
                        vertexArray[vertexId].getOutEdge().remove(destinationId);
                        detectionHistory.add(destinationId);
                        if (detectionHistory.size() + vertexArray[vertexId].getOutEdge().size() >= maxEdgeCount - 1) {
                            //System.out.println("Can't add more edge to vertex: " + vertexId + ", abandon!");
                            break vertex;
                        }
                        // next try
                        //System.out.println("Try to add " + "destination: " + destinationId + " to vertex: " + vertexId
                        //        + " ,failure! Try next.");
                        //detectionHistoryOutput(detectionHistory);
                        continue;
                    } else {
                        // success
                        //System.out.println("Success add destination: " + destinationId + " to vertex: " + vertexId);
                        //graphicOutput(vertexArray);
                        break;
                    }
                }
            }
            System.out.println("<< Vertex " + vertexId + " actually have " + vertexArray[vertexId].getOutEdge().size() + " edges >>");
            //System.out.println("===================================");
        }
        graphicOutput(vertexArray);

        return vertexArray;
    }

    private int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    private void topologicalSort (Vertex[] graphic) {
        for (Vertex startVertex : graphic) {
            if (startVertex.getOutEdge().size() == 0) {
                Map<Integer, Vertex> result = new LinkedHashMap<>();
                Queue<Vertex> workingQueue = new LinkedList<>();
                workingQueue.add(startVertex);
                System.out.println("Working from vertex: " + startVertex.getId());

                while (workingQueue.size() > 0) {
                    Vertex vertex = workingQueue.poll();
                    result.put(vertex.getId(), vertex);
                    List<Vertex> inVertexList = checkInDegree(vertex, graphic);
                    for (Vertex inVertex : inVertexList) {
                        if (!result.containsKey(inVertex.getId()))
                            workingQueue.add(inVertex);
                    }
                }
                //result map
                Iterator iterator = result.entrySet().iterator();
                System.out.print("Reverse order of topological sort: < ");
                while (iterator.hasNext())
                    System.out.print(((Map.Entry) iterator.next()).getKey() + " ");

                System.out.println(">");
            }
        }
    }

    private List<Vertex> checkInDegree (Vertex vertexInput, Vertex[] graphic) {
        List<Vertex> inDegreeVertex = new LinkedList<>();
        for (Vertex vertex : graphic) {
            if (vertex.getOutEdge().containsKey(vertexInput.getId()))
                inDegreeVertex.add(vertex);
        }
        return inDegreeVertex;
    }


    private Vertex[] createGraphicManual() {
        List<Vertex> manualVertex = new ArrayList<>(5);
        Vertex v0 = new Vertex(0);
        v0.addOutEdge(new Edge(3, 1));
        v0.addOutEdge(new Edge(3, 3));
        v0.addOutEdge(new Edge(3, 4));
        v0.addOutEdge(new Edge(3, 5));

        Vertex v1 = new Vertex(1);
        //v1.addOutEdge(new Edge(3, 2));

        Vertex v2 = new Vertex(2);
        v2.addOutEdge(new Edge(3, 1));
        v2.addOutEdge(new Edge(3, 5));
        v2.addOutEdge(new Edge(3, 4));
        v2.addOutEdge(new Edge(3, 3));

        Vertex v3 = new Vertex(3);
        v3.addOutEdge(new Edge(3, 1));
        v3.addOutEdge(new Edge(3, 4));

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
        return graphic;
    }

    private boolean checkCircle(Vertex[] graphic) {
        for (Vertex startVertex : graphic) {
            Stack<Vertex> vertexStack = new Stack<>();
            vertexStack.push(startVertex);
            // System.out.print("Start from " + startVertex.getId() + " , stack: < ");
            while (!vertexStack.empty()) {
                Vertex vertex = vertexStack.pop();
                //System.out.print(vertex.getId() + " ");
                if (vertex.getId() == startVertex.getId() && vertex.isAccess()) {
                    //System.out.println(">");
                    //System.out.println("Check circle failure, start id: " + startVertex.getId());
                    resetAccessFlag(graphic);
                    return false;
                }
                if (!vertex.isAccess()) {
                    vertex.setAccess(true);
                    Iterator iterator = vertex.getOutEdge().entrySet().iterator();
                    while (iterator.hasNext()) {
                        int edgeDestination = (Integer) ((Map.Entry) iterator.next()).getKey();
                        vertexStack.add(graphic[edgeDestination]);
                    }
                }
            }
            //System.out.println(">");
            resetAccessFlag(graphic);
        }
        return true;
    }

    private void resetAccessFlag(Vertex[] graphic) {
        for (Vertex vertex : graphic)
            if (vertex != null)
                vertex.setAccess(false);
    }

    private void graphicOutput(Vertex[] graphic) {
        System.out.println("--------Graphic--------");
        for (Vertex vertex : graphic) {
            System.out.print(vertex.getId() + ": ");
            System.out.print("< ");
            Iterator iterator = vertex.getOutEdge().entrySet().iterator();
            while (iterator.hasNext()) {
                int edgeDestination = (Integer) ((Map.Entry) iterator.next()).getKey();
                System.out.print(edgeDestination + " ");
            }
            System.out.println(">");
        }
        System.out.println("-----------------------");
    }

    private void detectionHistoryOutput(Set<Integer> detectionHistory) {
        Iterator<Integer> iterator = detectionHistory.iterator();
        System.out.print("Detection History: < ");
        while (iterator.hasNext()) {
            int detection = iterator.next();
            System.out.print(detection + " ");
        }
        System.out.println(">");
    }

}