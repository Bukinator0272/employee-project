package controller;

import model.dao.EmployeeDAO;
import model.entity.Employee;
import model.service.EmployeeService;

import java.util.List;

public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public EmployeeController() {
        this.employeeDAO = new EmployeeService();
    }

    public Employee get(int id) {
        return employeeDAO.read(id);
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

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    public void delete(int id) {
        employeeDAO.remove(id);
    }

}
