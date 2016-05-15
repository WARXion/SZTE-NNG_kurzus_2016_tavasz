package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;

public class Edge {
    private int id;
    private int averageSpeed;
    private double edgeLength;
    private double timeToTravel;
    private Node startNode;
    private Node endNode;
    private List<EdgeProperty> properties;

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElements(@XmlElement(name = "property"))
    public List<EdgeProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<EdgeProperty> properties) {
        this.properties = properties;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public double getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(double edgeLength) {
        this.edgeLength = edgeLength;
    }

    public double getTimeToTravel() {
        return timeToTravel;
    }

    public void setTimeToTravel(double timeToTravel) {
        this.timeToTravel = timeToTravel;
    }
}
