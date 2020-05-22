package servlet.employee;

import controller.DepartmentController;
import controller.EmployeeController;
import controller.PositionController;
import model.entity.Department;
import model.entity.Employee;
import model.entity.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add-employee")
public class AddEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionController positionController = new PositionController();
        List<Position> positions = null;
        try {
            positions = positionController.getPosition();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DepartmentController departmentController = new DepartmentController();
        List<Department> departments = null;
        try {
            departments = departmentController.getDepartment();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EmployeeController employeeController = new EmployeeController();
        List<Employee> managers = null;
        try {
            managers = employeeController.getEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("positions", positions);
        req.setAttribute("departments", departments);
        req.setAttribute("managers", managers);
        getServletContext().getRequestDispatcher("/add_employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeController employeeController = new EmployeeController();
        try {
            employeeController.addEmployee(
                    req.getParameter("name"),
                    req.getParameter("surname"),
                    req.getParameter("positionId"),
                    req.getParameter("departmentId"),
                    req.getParameter("managerId"),
                    req.getParameter("employmentDate")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view-employees");
    }

}
