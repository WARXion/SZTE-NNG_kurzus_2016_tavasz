package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class NodeProperty {
    @XmlAttribute(name="name")
    private String attribute;

    @XmlValue
    private double value;
}
