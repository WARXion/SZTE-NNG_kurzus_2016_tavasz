package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import java.util.Map;

public class Node {
    private int id;
    private List<NodeProperty> properties;
    private Map<String, Double> mappedProperties;

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElements(@XmlElement(name = "property"))
    public List<NodeProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<NodeProperty> properties) {
        this.properties = properties;
    }

    public Map<String, Double> getMappedProperties() {
        return mappedProperties;
    }

    public void setMappedProperties(Map<String, Double> mappedProperties) {
        this.mappedProperties = mappedProperties;
    }
}
