package model.dao;

import model.dao.constant.EmployeesNC;
import model.entity.Department;
import model.executor.Executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl {

    private Executor executor;

    public DepartmentDAOImpl() throws SQLException {
        this.executor = new Executor();
    }

    private boolean isExist(Department department) throws SQLException {
        if (department == null || department.getId() == null)
            return false;
        return existById(department.getId());
    }

    private boolean existById(Long id) throws SQLException {
        return executor.execQuery(result -> result.next(), "select * from department where id = ? ", id.toString());
    }

    public Department get(Long id) throws SQLException {
        return executor.execQuery(result -> {
            if (!result.next())
                return null;
            return new Department(result.getLong(EmployeesNC.DepartmentTable.ID), result.getString(EmployeesNC.DepartmentTable.NAME));
        }, "select * from department where id=?", id.toString());
    }

    public List<Department> getAll() throws SQLException {
        return executor.execQuery("select * from department order by NAME", result -> {
            List<Department> departments = new ArrayList<>();
            while (result.next()) {
                departments.add(new Department(result.getLong(EmployeesNC.DepartmentTable.ID), result.getString(EmployeesNC.DepartmentTable.NAME)));
            }
            return departments;
        });
    }

    public void delete(Long id) throws SQLException {
        executor.execUpdate("delete from department where id=?", id.toString());
    }

    public void delete(Department department) throws SQLException {
        executor.execUpdate("delete from department where id=?", department.getId().toString());
    }

    public void update(Department department) throws SQLException {
        executor.execUpdate("update department set name=? where id =?", department.getName(), department.getId().toString());
    }

    public void insert(Department department) throws SQLException {
        executor.execUpdate("insert into department (id, name) values (DEPARTMENT_SEQUENCE.nextval,?)", department.getName());
    }
}





