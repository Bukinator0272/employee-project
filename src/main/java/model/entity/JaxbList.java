package model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "container")
public class JaxbList<T> {

    @XmlElement(name = "item")
    public List<T> list;

    public JaxbList() {
    }

    public JaxbList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }
}