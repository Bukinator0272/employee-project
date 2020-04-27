package controller;

import model.dao.DepartmentDAOImpl;
import model.entity.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentController {

    public DepartmentController() {
    }

    public Department getDepartmentById(Long id) throws SQLException {
        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
        Department department = departmentDAO.get(id);
        return department;
    }

    public List<Department> getDepartment() throws SQLException {
        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
        return departmentDAO.getAll();
    }

    public void saveDepartment(String name) throws SQLException {
        Department department = new Department(name);
        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
        departmentDAO.insert(department);
    }

    public void saveDepartment(Department department) throws SQLException {
        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
        departmentDAO.update(department);
    }

    public void removeDepartment(Long id) throws SQLException {
        if (id != null) {
            DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
            departmentDAO.delete(id);
        }
    }
}

