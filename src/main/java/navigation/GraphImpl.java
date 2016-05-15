package navigation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

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
