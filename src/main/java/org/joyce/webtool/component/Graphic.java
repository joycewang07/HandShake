package org.joyce.webtool.component;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by junyan Zhang on 14-3-23.
 */

@Controller
@RequestMapping(value="/graphic")
public class Graphic {
    private int graphicSize = 20;

    private class Edge {
        private int weight;
        private Vertex destination;

        private Edge(int weight, Vertex destination) {
            this.weight = weight;
            this.destination = destination;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Vertex getDestination() {
            return destination;
        }

        public void setDestination(Vertex destination) {
            this.destination = destination;
        }
    }

    private class Vertex {
        private int id;
        private List<Edge> outEdge;
        private boolean access = false;

        private Vertex(int id) {
            this.id = id;
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
                    vectorQueue.add(edge.getDestination());
            }
        }
        return true;
    }

    private void resetAccessFlag (List<Vertex> graphic) {
        for (Vertex vertex : graphic)
            vertex.setAccess(false);

        
    }

}
