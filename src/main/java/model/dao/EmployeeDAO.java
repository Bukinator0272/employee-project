package model.dao;

import model.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void add(Employee employee);

    Employee read(int id);

    List<Employee> readAll();

    void update(Employee employee);

    void remove(int id);

}
