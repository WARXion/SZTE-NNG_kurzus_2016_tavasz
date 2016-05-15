package navigation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class EdgeProperty {
    private String attribute;
    private int value;

    @XmlAttribute(name="name")
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @XmlValue
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
