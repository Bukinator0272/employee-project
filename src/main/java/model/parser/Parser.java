package model.parser;

import model.entity.JaxbList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;

public class Parser {

    JAXBContext context;

    public Parser(Class classType) throws JAXBException {
        this.context = JAXBContext.newInstance(JaxbList.class, classType);
    }

    public void serialize(Object o, OutputStream outputStream) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(o, outputStream);
    }

    public Object deserialize(InputStream inputStream) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = null;
        object = unmarshaller.unmarshal(inputStream);
        return object;
    }
}