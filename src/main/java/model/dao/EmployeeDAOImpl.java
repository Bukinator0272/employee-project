package model.dao;

import model.dao.constant.EmployeesNC;
import model.entity.Department;
import model.entity.Employee;
import model.entity.HierarchyEmployees;
import model.entity.Position;
import model.executor.Executor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl {

    private Executor executor;

    public EmployeeDAOImpl() throws SQLException {
        this.executor = new Executor();
    }

    private boolean isExist(Employee employee) throws SQLException {
        if (employee == null || employee.getId() == null)
            return false;
        return existById(employee.getId());
    }

    private boolean existById(Long id) throws SQLException {
        return executor.execQuery(result -> result.next(), "select * from employees where id = ? ", id.toString());
    }

    public void save(Employee employee) throws SQLException {
        if (isExist(employee)) {
            executor = new Executor();
            update(employee);
        } else {
            executor = new Executor();
            insert(employee);
        }
    }

    public Employee get(Long id, boolean isSelectManager) throws SQLException {
        if (id == null || id.equals(0L))
            return null;
        else return executor.execQuery(result -> {
            result.next();
            String name = result.getString(EmployeesNC.EmployeeTable.NAME);
            String surname = result.getString(EmployeesNC.EmployeeTable.SURNAME);
            Long positionId = result.getLong(EmployeesNC.EmployeeTable.POSITION);
            Long departmentId = result.getLong(EmployeesNC.EmployeeTable.DEPARTMENT);
            Long managerId = result.getLong(EmployeesNC.EmployeeTable.MANAGER);
            LocalDate employmentDate = result.getDate(EmployeesNC.EmployeeTable.EMPLOYMENT_DATE).toLocalDate();

            PositionDAOImpl positionDAO = new PositionDAOImpl();
            Position position = positionDAO.get(positionId);

            DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
            Department department = departmentDAO.get(departmentId);
            Employee manager = null;
            if (isSelectManager) {
                EmployeeDAOImpl managerDAO = new EmployeeDAOImpl();
                manager = managerDAO.get(managerId, false);
            }

            Employee employee = new Employee(id, name, surname, position, department, manager, employmentDate);
            return employee;
        }, "select * from employees where id=?", id.toString());
    }

    public List<Employee> getAll() throws SQLException {
        return executor.execQuery("select * from employees", result -> {
            List<Employee> employees = new ArrayList<>();
            while (result.next()) {
                Long id = result.getLong(EmployeesNC.EmployeeTable.ID);
                String name = result.getString(EmployeesNC.EmployeeTable.NAME);
                String surname = result.getString(EmployeesNC.EmployeeTable.SURNAME);
                Long positionId = result.getLong(EmployeesNC.EmployeeTable.POSITION);
                Long departmentId = result.getLong(EmployeesNC.EmployeeTable.DEPARTMENT);
                Long managerId = result.getLong(EmployeesNC.EmployeeTable.MANAGER);
                LocalDate employmentDate = result.getDate(7).toLocalDate();
                PositionDAOImpl positionDAO = new PositionDAOImpl();
                Position position = positionDAO.get(positionId);
                DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
                Department department = departmentDAO.get(departmentId);

                Employee manager = new EmployeeDAOImpl().get(managerId, false);
                Employee employee = new Employee(id, name, surname, position, department, manager, employmentDate);
                employees.add(employee);
            }
            return employees;
        });
    }

    public List<HierarchyEmployees> getHierarchyEmployees() throws SQLException{
        return executor.execQuery("SELECT level,id, name, surname, manager_id FROM EMPLOYEES START WITH manager_id is null CONNECT BY PRIOR ID = MANAGER_ID", result -> {
            List<HierarchyEmployees> hierarchy = new ArrayList<>();
            while (result.next()) {
                Long level = result.getLong("level");
                Long id = result.getLong(EmployeesNC.EmployeeTable.ID);
                String name = result.getString(EmployeesNC.EmployeeTable.NAME);
                String surname = result.getString(EmployeesNC.EmployeeTable.SURNAME);

                Employee employee = new Employee(id, name, surname);
                HierarchyEmployees hierarchyEmployees = new HierarchyEmployees(level, employee);

                hierarchy.add(hierarchyEmployees);
            }
            return hierarchy;
        });
    }



    public void delete(Long id) throws SQLException {
        executor.execUpdate("delete from employees where id=?", id.toString());
    }

    public void delete(Employee employee) throws SQLException {
        delete(employee.getId());
    }

    public void update(Employee employee) throws SQLException {
        Long id = employee.getId();
        String name = employee.getName();
        String surname = employee.getSurname();
        String positionId = employee.getPosition() != null ? employee.getPosition().getId().toString() : null;
        String departmentId = employee.getDepartment() != null ? employee.getDepartment().getId().toString() : null;
        String managerId = employee.getManager() != null ? employee.getManager().getId().toString() : "";
        LocalDate employmentDate = employee.getEmploymentDate();

        executor.execUpdate("update employees set name=?, surname=?, position_id=?, department_id=?, manager_id=?  where id =?",
                name, surname, positionId, departmentId, managerId, id.toString());
    }

    public void insert(Employee employee) throws SQLException {
        String name = employee.getName();
        String surname = employee.getSurname();
        String positionId = employee.getPosition() != null ? employee.getPosition().getId().toString() : null;
        String departmentId = employee.getDepartment() != null ? employee.getDepartment().getId().toString() : null;
        Long managerId = employee.getManager().getId();
        LocalDate employmentDate = employee.getEmploymentDate();
        String date = employmentDate.toString();
        executor.execUpdate("insert into employees (id, name, surname, position_id, department_id, manager_id, employment_date) values (EMPLOYEE_SEQUENCE.nextval,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'))", name, surname, positionId, departmentId, managerId.toString(), date);
    }
}
