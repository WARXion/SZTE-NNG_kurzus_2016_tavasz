package navigation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement your navigation algorithm here. This class will be instantiated
 * during the unit tests.
 */
public class AlgorithmImpl implements Algorithm {
    private GraphImpl graph;

    private LinkedList<Node> nodesToVisit = new LinkedList<>();
    private LinkedList<Node> visitedNodes = new LinkedList<>();

    @Override
    public void preProcess(Graph graph) {
        this.graph = (GraphImpl) graph;
    }

    @Override
    public DistanceResult findShortestPath(int startNodeId,
                                           int destinationNodeId) {
        LinkedList<Node> shortestPath = AStarPathFinding(startNodeId, destinationNodeId);

        return new DistanceResult() {
            @Override
            public List<Integer> getResultPath() {
                return shortestPath.stream().map(Node::getId).collect(Collectors.toCollection(LinkedList::new));
            }

            @Override
            public double getTravelDistanceOfResultPath() {
                double distance = 0;

                for (int i = 0; i < shortestPath.size() - 1; i++) {
                    distance += getDistanceOf(shortestPath.get(i), shortestPath.get(i + 1));
                }

                return distance;
            }
        };
    }

    @Override
    public TimeResult findFastestPath(int startNodeId, int destinationNodeId) {
        LinkedList<Node> shortestPath = AStarPathFinding(startNodeId, destinationNodeId);

        return new TimeResult() {

            @Override
            public List<Integer> getResultPath() {
                return shortestPath.stream().map(Node::getId).collect(Collectors.toCollection(LinkedList::new));
            }

            @Override
            public double getTravelTimeOfResultPath() {
                double travelTime = 0;

                for (int i = 0; i < shortestPath.size() - 1; i++) {
                    travelTime += getTravelTimeOf(shortestPath.get(i), shortestPath.get(i + 1));
                }

                return travelTime;
            }
        };
    }

    @Override
    public boolean hasPath(int startNodeId, int destinationNodeId) {
        LinkedList<Node> shortestPath = AStarPathFinding(startNodeId, destinationNodeId);

        return shortestPath != null && !shortestPath.isEmpty();
    }

    private LinkedList<Node> AStarPathFinding(int startId, int endId) {
        LinkedList<Node> path = new LinkedList<>();

        Node startNode = getNodeById(startId);
        Node endNode = getNodeById(endId);

        startNode.setfScore(0);
        path.add(startNode);
        nodesToVisit.add(startNode);

        while (!nodesToVisit.isEmpty()) {
            Node currentNode = getLeastF();

            nodesToVisit.remove(currentNode);
            List<Node> successorList = currentNode.getChildren();

            for (Node node : successorList) {
                node.setParent(currentNode);

                if (node.equals(endNode)) {
                    path.add(node);

                    return path;
                }

                node.setgScore(currentNode.getgScore() + getLengthOfEdge(currentNode, node));
                node.sethScore(getDistanceOf(node, endNode));

                if (nodesToVisit.contains(node)) {
                    if (node.getfScore() <= node.getgScore() + node.gethScore()) {
                        continue;
                    }
                }

                if (visitedNodes.contains(node)) {
                    if (node.getfScore() <= node.getgScore() + node.gethScore()) {
                        continue;
                    }
                }

                nodesToVisit.add(node);
            }

            visitedNodes.add(currentNode);
        }

        return path;
    }

    private Node getNodeById(int id) {
        for (Node node : graph.getNodes()) {
            if (node.getId() == id) {
                return node;
            }
        }

        return null;
    }

    private Node getLeastF() {
        Node min = new Node();
        min.setfScore(Double.MAX_VALUE);

        for (Node node : nodesToVisit) {
            if (node.getfScore() < min.getfScore()) {
                min = node;
            }
        }

        return min;
    }

    private double getDistanceOf(Node from, Node to) {
        return Math.sqrt(Math.pow(Math.abs(from.getxCoord() - to.getxCoord()), 2)
                + Math.pow(Math.abs(from.getyCoord() - to.getyCoord()), 2));
    }

    private double getTravelTimeOf(Node from, Node to) {
        for (Edge edge : from.getEdges()) {
            if (edge.getEndNode().equals(to)) {
                return edge.getTimeToTravel();
            }
        }

        return 0;
    }

    private double getLengthOfEdge(Node from, Node to) {
        for (Edge edge : from.getEdges()) {
            if (edge.getEndNode().equals(to)) {
                return edge.getEdgeLength();
            }
        }

        return 0;
    }
}