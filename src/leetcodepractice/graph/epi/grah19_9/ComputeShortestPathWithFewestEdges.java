package leetcodepractice.graph.epi.grah19_9;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

class ComputeShortestPathWithFewestEdges {

    private static class DistanceWithFewestEdges {
        Integer distance;

        public DistanceWithFewestEdges(Integer distance, Integer edges) {
            this.distance = distance;
            this.edges = edges;
        }

        Integer edges;
    }

    private static class VertexWithDistance {
        public VertexWithDistance(GraphVertex graphVertex, Integer distance) {
            this.graphVertex = graphVertex;
            this.distance = distance;
        }

        GraphVertex graphVertex;
       Integer distance;


    }
    private static class GraphVertex implements Comparable<GraphVertex>{
        DistanceWithFewestEdges distanceWithFewestEdges = new DistanceWithFewestEdges(Integer.MAX_VALUE, 0);
        List<VertexWithDistance> edges = new ArrayList<>();
        Integer id;
        GraphVertex pred = null;

        @Override
        public int compareTo(GraphVertex o) {
            if(distanceWithFewestEdges.distance != o.distanceWithFewestEdges.distance) {
                return Integer.compare(distanceWithFewestEdges.distance, o.distanceWithFewestEdges.distance);
            }
            if(distanceWithFewestEdges.edges != o.distanceWithFewestEdges.edges) {
                return Integer.compare(distanceWithFewestEdges.edges, o.distanceWithFewestEdges.edges);
            }
            return Integer.compare(id, o.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(distanceWithFewestEdges.distance, distanceWithFewestEdges.edges);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof GraphVertex)) {
                return false;
            }
            if(this == obj) {
                return true;
            }
            GraphVertex that = (GraphVertex) obj;
            return id.equals(that.id)
                    && distanceWithFewestEdges.distance.equals(that.distanceWithFewestEdges.distance)
                    && distanceWithFewestEdges.edges.equals(that.distanceWithFewestEdges.edges);
        }
    }
    public static void dijkstraShortestPath(GraphVertex source, GraphVertex target) {
        // initialization of source diestnace and edges
        source.distanceWithFewestEdges = new DistanceWithFewestEdges(0,0);
        SortedSet<GraphVertex> nodeSet  = new TreeSet<>();
        nodeSet.add(source);

        while (!nodeSet.isEmpty()) {
            // extract the min distance- min few edge vertex
            GraphVertex first = nodeSet.first();
            if(first.equals(target)) {
                break;
            }
            nodeSet.remove(first);
            //Relax the neighbours
            for(VertexWithDistance neighbour: first.edges) {
                int nDistance = first.distanceWithFewestEdges.distance + neighbour.distance;
                int nFewEdge = first.distanceWithFewestEdges.edges + 1;

                if(neighbour.graphVertex.distanceWithFewestEdges.distance > nDistance
                    || (neighbour.graphVertex.distanceWithFewestEdges.distance == nDistance
                && neighbour.graphVertex.distanceWithFewestEdges.edges> nFewEdge)) {
                    nodeSet.remove(neighbour.graphVertex);
                    neighbour.graphVertex.pred = first;
                    neighbour.graphVertex.distanceWithFewestEdges = new DistanceWithFewestEdges(nDistance, nFewEdge);
                    nodeSet.add(neighbour.graphVertex);
                }
            }
        }

        outputShortestPath(target);
    }

    private static void outputShortestPath(GraphVertex target) {
        if(target != null) {
            outputShortestPath(target.pred);
            System.out.print(target.id + " -> ");
        }
    }
}
