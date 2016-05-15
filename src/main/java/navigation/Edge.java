package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class Edge {
    private int id;
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
}
