package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import java.util.Map;

public class Edge {
    private int id;
    private List<EdgeProperty> properties;
    private Map<String, Integer> mappedProperties;

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

    public Map<String, Integer> getMappedProperties() {
        return mappedProperties;
    }

    public void setMappedProperties(Map<String, Integer> mappedProperties) {
        this.mappedProperties = mappedProperties;
    }
}
