package controller;

import model.dao.DepartmentDAOImpl;
import model.dao.EmployeeDAOImpl;
import model.dao.PositionDAOImpl;
import model.entity.Department;
import model.entity.Employee;
import model.entity.HierarchyEmployees;
import model.entity.Position;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmployeeController {

    public EmployeeController() {
    }

    public Employee getEmployeeById(Long id) throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        Employee employee = employeeDAO.get(id, true);
        return employee;
    }

    public void saveEmployee(Employee employee) throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.save(employee);
    }

    public void removeEmployee(Long id) throws SQLException {
        if (id != null) {
            EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
            employeeDAO.delete(id);
        }
    }

    public void addEmployee(String name, String surname, String positionId, String departmentId, String managerId, String employmentDate) throws SQLException {
        EmployeeDAOImpl managerEmpDAO = new EmployeeDAOImpl();
        Employee manager = managerEmpDAO.get(Long.valueOf(managerId), false);

        PositionDAOImpl positionDAO = new PositionDAOImpl();
        Position position = positionDAO.get(Long.valueOf(positionId));

        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
        Department department = departmentDAO.get(Long.valueOf(departmentId));

        Employee employee = new Employee(name, surname, position, department, manager, LocalDate.now());

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.save(employee);
    }

    public List<Employee> getEmployees() throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.getAll();
    }

    public List<HierarchyEmployees> showHierarchy() throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.getHierarchyEmployees();
    }
}
