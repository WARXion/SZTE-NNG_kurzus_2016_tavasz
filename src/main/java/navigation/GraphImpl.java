package navigation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement your graph representation here. This class will be instantiated
 * during the unit tests.
 */
@XmlRootElement(name="graph")
public class GraphImpl implements Graph {
    private List<Node> nodes;
    private List<Edge> edges;

	@Override
	public void initializeFromFile(File inputXmlFile) {
        File inputGraph = new File("test_graph.xml");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GraphImpl.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            GraphImpl graph = (GraphImpl) jaxbUnmarshaller.unmarshal(inputGraph);

            Map<String, Integer> mappedEdgeProperties = new HashMap<>();
            Map<String, Double> mappedNodeProperties = new HashMap<>();

            for (Edge edge : graph.getEdges()) {
                for (EdgeProperty edgeProperty : edge.getProperties()) {
                    mappedEdgeProperties.put(edgeProperty.getAttribute(), edgeProperty.getValue());
                }

                edge.setMappedProperties(mappedEdgeProperties);
            }

            for (Node node : graph.getNodes()) {
                for (NodeProperty nodeProperty : node.getProperties()) {
                    mappedNodeProperties.put(nodeProperty.getAttribute(), nodeProperty.getValue());
                }

                node.setMappedProperties(mappedNodeProperties);
            }

            setEdges(graph.getEdges());
            setNodes(graph.getNodes());
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
