package servlet.servletEmployee;

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

@WebServlet("/show-employee")
public class ShowEmployee extends HttpServlet {

    private Employee employee;
    private List<Position> positions;
    private List<Department> departments;
    private List<Employee> managers;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionController positionController = new PositionController();
        try {
            positions = positionController.getPosition();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DepartmentController departmentController = new DepartmentController();
        try {
            departments = departmentController.getDepartment();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EmployeeController employeeController = new EmployeeController();
        try {
            managers = employeeController.getEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("positions", positions);
        req.setAttribute("departments", departments);
        req.setAttribute("managers", managers);

        try {
            employee = employeeController.getEmployeeById(Long.valueOf(req.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("employee", employee);
        getServletContext().getRequestDispatcher("/showEmployeePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/edit-employees" + req.getParameter("id"));
    }
}
