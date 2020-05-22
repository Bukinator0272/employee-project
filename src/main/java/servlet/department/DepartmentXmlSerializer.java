package servlet.department;

import model.dao.DepartmentDAOImpl;
import model.ejb.XmlConverter;
import model.entity.Department;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/departments")
@MultipartConfig(location = "C:\\uploaded", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class DepartmentXmlSerializer extends HttpServlet {

    @EJB
    XmlConverter converter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream out = resp.getOutputStream();
        resp.setContentType("application/xml");
        try {
            try {
                converter.getXmlDocument(Department.class, out);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        try {
            List<Department> departments = converter.uploadObjects(Department.class, fileContent);
            DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
            for (Department department : departments) {
                departmentDAO.save(department);
            }

            System.out.println(departments.size());
        } catch (JAXBException | SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view-departments");
    }
}