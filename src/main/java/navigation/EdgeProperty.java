package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class EdgeProperty {
    @XmlAttribute(name="name")
    private String attribute;

    @XmlValue
    private int value;
}
