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
import java.time.LocalDate;
import java.util.List;

@WebServlet("/edit-employee")
public class EditEmployee extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/edit_employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String newSurname = req.getParameter("surname");
        String positionId = req.getParameter("positionId");
        String departmentId = req.getParameter("departmentId");
        String managerId = req.getParameter("managerId");
        String date = req.getParameter("employmentDate");

        employee.setName(newName);
        employee.setSurname(newSurname);
        employee.setPosition(null);
        employee.setManager(null);
        employee.setDepartment(null);

        for (int i = 0; i < positions.size(); i++) {
            if (positionId.equals(positions.get(i).getStringID())) {
                Position position = positions.get(i);
                employee.setPosition(position);
            }
        }

        for (int i = 0; i < departments.size(); i++) {
            if (departmentId.equals(departments.get(i).getStringID())) {
                Department department = departments.get(i);
                employee.setDepartment(department);
            }
        }

        for (int i = 0; i < managers.size(); i++) {
            if (managerId.equals(managers.get(i).getStringID())) {
                Employee manager = managers.get(i);
                employee.setManager(manager);
            }
        }
        employee.setEmploymentDate(LocalDate.now());
        EmployeeController employeeController = new EmployeeController();
        try {
            employeeController.saveEmployee(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view-employees");
    }
}
