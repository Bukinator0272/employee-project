package model.ejb;

import model.dao.DepartmentDAOImpl;
import model.dao.EmployeeDAOImpl;
import model.dao.PositionDAOImpl;
import model.entity.JaxbList;
import model.parser.Parser;

import javax.ejb.Stateful;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

@Stateful
public class XmlConverter {

    public void getXmlDocument(Class classType, OutputStream outputStream) throws SQLException, JAXBException {
        EntityType entityType = EntityType.getByClass(classType);
        Object object = null;
        switch (entityType) {
            case DEPARTMENT: {
                object = new JaxbList<>(new DepartmentDAOImpl().getAll());
                break;
            }
            case POSITION: {
                object = new JaxbList<>(new PositionDAOImpl().getAll());
                break;
            }
            case EMPLOYEE: {
                object = new JaxbList<>(new EmployeeDAOImpl().getAll());
                break;
            }
            default:
                break;
        }
        if (object != null) {
            Parser parser = new Parser(classType);
            parser.serialize(object, outputStream);
        }
    }

    public <T> List<T> uploadObjects(Class classType, InputStream inputStream) throws JAXBException {
        Parser parser = new Parser(classType);
        JaxbList<T> container = (JaxbList<T>) parser.deserialize(inputStream);
        return container.getList();
    }
}