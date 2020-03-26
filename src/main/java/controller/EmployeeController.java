package controller;

import model.Employee;
import model.service.DAO.EmployeeDAO;

import java.util.List;

public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public EmployeeController() {
        this.employeeDAO = new EmployeeDAO();
    }

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public void add(String name, String surname, String department, String position, int Manager) {
        if (Manager == 0) {
            employeeDAO.add(new Employee(name, surname, department, position));
        } else {
            employeeDAO.add(new Employee(name, surname, department, position, Manager));
        }
    }

}
