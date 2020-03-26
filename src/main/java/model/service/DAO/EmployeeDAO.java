package model.service.DAO;

import model.Employee;
import model.service.DataBaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection connection;
    private static final String INSERT_INTO_EMPLOYEE = "INSERT INTO employee " +
            "(employment_date, name, surname, department_id, position_id, manager_id)" +
            "VALUES ((?), (?), (?), (?), (?), (?))";
    private static final String[] INSERT_QUERIES = {
            "INSERT INTO department (name) VALUES ((?))",
            "INSERT INTO position (name) VALUES ((?))"
    };
    private static final String[] SELECT_QUERIES = {
            "SELECT COUNT(*) FROM department",
            "SELECT COUNT(*) FROM position",
            "SELECT name FROM department WHERE department_id = (?)",
            "SELECT name FROM position WHERE position_id = (?)",
            "SELECT name, surname FROM employee WHERE employee_id = (?)",
            "SELECT * FROM employee",
            "SELECT employee_id FROM employee where employee_id = (?)",
            "SELECT COUNT(*) FROM employee",
    };

    public EmployeeDAO() {
        this.connection = DataBaseService.getInstance().getConnection();
    }

    public void add(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_EMPLOYEE)) {
            if (checkManager(employee.getManager()) || crutch()) {
                int[] infoId = addInfo(employee);
                preparedStatement.setObject(1, Date.valueOf(employee.getEmploymentDate()));
                preparedStatement.setString(2, employee.getName());
                preparedStatement.setString(3, employee.getSurname());
                preparedStatement.setInt(4, infoId[0]);
                preparedStatement.setInt(5, infoId[1]);
                if (employee.getManager() == 0 || crutch()) {
                    preparedStatement.setObject(6, null);
                } else {
                    preparedStatement.setInt(6, employee.getManager());
                }
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkManager(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[6])) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean crutch() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[7])) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int[] addInfo(Employee employee) {
        int[] infoId = new int[2];
        int tableCount = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERIES[tableCount])) {
            preparedStatement.setString(1, employee.getDepartment());
            preparedStatement.executeUpdate();
            infoId[tableCount] = getIdInfoId(tableCount);
            tableCount++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERIES[tableCount])) {
            preparedStatement.setString(1, employee.getPosition());
            preparedStatement.executeUpdate();
            infoId[tableCount] = getIdInfoId(tableCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoId;
    }

    private int getIdInfoId(int tableCount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[tableCount])) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[5])) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(7) > 0 && resultSet.getInt(7) < 1000) {
                    employees.add(new Employee(
                            resultSet.getString(3),
                            resultSet.getString(4),
                            getDepartmentName(resultSet.getInt(5)),
                            getPositionName(resultSet.getInt(6)),
                            resultSet.getInt(7)
                    ));
                } else {
                    employees.add(new Employee(
                            resultSet.getString(3),
                            resultSet.getString(4),
                            getDepartmentName(resultSet.getInt(5)),
                            getPositionName(resultSet.getInt(6))
                    ));
                }
                employees.get(employees.size() - 1).setId(resultSet.getInt(1));
                employees.get(employees.size() - 1).setEmploymentDate(resultSet.getDate(2).toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private String getDepartmentName(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[2])) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPositionName(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[3])) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getManagersNameSurname(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[4])) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1) + " " + resultSet.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
