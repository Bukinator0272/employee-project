package servlet.servletDepartment;

import controller.DepartmentController;
import model.entity.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/view-departments")
public class ViewDepartments extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentController departmentController = new DepartmentController();
        List<Department> departments = null;
        try {
            departments = departmentController.getDepartment();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("departments", departments);

        getServletContext().getRequestDispatcher("/viewDepartmentsPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
