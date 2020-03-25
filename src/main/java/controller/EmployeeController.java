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

    public void add(String name, String surname, String department, String position, String projectName, int cabinetNumber) {
        employeeDAO.add(new Employee(name, surname, department, position, projectName, cabinetNumber));
    }

}
