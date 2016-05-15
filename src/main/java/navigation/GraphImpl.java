package navigation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement your graph representation here. This class will be instantiated
 * during the unit tests.
 */
@XmlRootElement(name = "graph")
public class GraphImpl implements Graph {
    private List<Node> nodes;
    private List<Edge> edges;

    @Override
    public void initializeFromFile(File inputXmlFile) {
        File inputGraph = new File("graph.xml");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GraphImpl.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            GraphImpl graph = (GraphImpl) jaxbUnmarshaller.unmarshal(inputGraph);

            Map<String, Integer> mappedEdgeProperties = new HashMap<>();
            List<Node> nodeList = new ArrayList<>();
            List<Edge> edgeList = new ArrayList<>();

            for (Node node : graph.getNodes()) {
                Node newNode = new Node();

                newNode.setId(node.getId());
                newNode.setyCoord(node.getProperties().get(0).getValue());
                newNode.setxCoord(node.getProperties().get(1).getValue());

                nodeList.add(newNode);
            }

            for (Edge edge : graph.getEdges()) {
                Edge newEdge = new Edge();

                for (EdgeProperty edgeProperty : edge.getProperties()) {
                    mappedEdgeProperties.put(edgeProperty.getAttribute(), edgeProperty.getValue());
                }

                Node startNode = nodeList.get(mappedEdgeProperties.get("startNode") - 1);
                Node endNode = nodeList.get(mappedEdgeProperties.get("endNode") - 1);

                newEdge.setId(edge.getId());
                newEdge.setStartNode(startNode);
                newEdge.setEndNode(endNode);
                newEdge.setAverageSpeed(mappedEdgeProperties.get("averageSpeed"));

                newEdge.setEdgeLength(Math.sqrt(Math.pow(Math.abs(startNode.getxCoord() - endNode.getxCoord()), 2)
                        + Math.pow(Math.abs(startNode.getyCoord() - endNode.getyCoord()), 2)));

                newEdge.setTimeToTravel(newEdge.getEdgeLength() / newEdge.getAverageSpeed());

                edgeList.add(newEdge);
                startNode.getEdges().add(newEdge);
            }

            for (Node node : nodeList) {
                for (int i = 0; i != node.getEdges().size(); i++) {
                    node.getChildren().add(node.getEdges().get(i).getEndNode());
                }
            }

            setEdges(edgeList);
            setNodes(nodeList);
        } catch (JAXBException exception) {
            exception.printStackTrace();
        }
    }

    @XmlElement(name = "node")
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @XmlElement(name = "edge")
    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
