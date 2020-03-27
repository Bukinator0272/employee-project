package controller;

import model.entity.Employee;
import model.dao.EmployeeDAO;
import model.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public EmployeeController() {
        this.employeeDAO = new EmployeeService();
    }

    public List<Employee> getAll() {
        return employeeDAO.readAll();
    }

    public void add(String name, String surname, String department, String position, int managerId) {
        if (managerId == 0) {
            employeeDAO.add(new Employee(name, surname, department, position));
        } else {
            employeeDAO.add(new Employee(name, surname, department, position, managerId));
        }
    }

}
