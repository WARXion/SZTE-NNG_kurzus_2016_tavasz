package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;

public class Node {
    private int id;
    private List<NodeProperty> properties;

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
}
