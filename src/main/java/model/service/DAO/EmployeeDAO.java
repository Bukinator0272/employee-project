package model.service.DAO;

import model.Employee;
import model.service.DataBaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection connection;
    private static final String INSERT_INTO_EMPLOYEE = "INSERT INTO employee " +
            "(employment_date, name, surname, department_id, position_id, project_name_id, cabinet_number_id)" +
            "VALUES ((?), (?), (?), (?), (?), (?), (?))";
    private static final String[] INSERT_QUERIES = {
            "INSERT INTO department (name) VALUES ((?))",
            "INSERT INTO position (name) VALUES ((?))",
            "INSERT INTO project_name (name) VALUES ((?))",
            "INSERT INTO cabinet_number (number) VALUES ((?))"
    };
    private static final String[] SELECT_QUERIES = {
            "SELECT COUNT(*) FROM department",
            "SELECT COUNT(*) FROM position",
            "SELECT COUNT(*) FROM project_name",
            "SELECT COUNT(*) FROM cabinet_number",
            "SELECT name FROM department WHERE department_id = (?)",
            "SELECT name FROM position WHERE position_id = (?)",
            "SELECT name FROM project_name WHERE project_name_id = (?)",
            "SELECT number FROM cabinet_number WHERE cabinet_number_id = (?)",
            "SELECT * FROM employee"

    };

    public EmployeeDAO() {
        this.connection = DataBaseService.getInstance().getConnection();
    }

    public void add(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_EMPLOYEE)) {
            int[] infoId = addInfo(employee);
            preparedStatement.setObject(1, Date.valueOf(employee.getEmploymentDate()));
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setInt(4, infoId[0]);
            preparedStatement.setInt(5, infoId[1]);
            preparedStatement.setInt(6, infoId[2]);
            preparedStatement.setInt(7, infoId[3]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int[] addInfo(Employee employee) {
        int[] infoId = new int[4];
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERIES[0])) {
            preparedStatement.setString(1, employee.getDepartment());
            preparedStatement.executeUpdate();
            infoId[0] = getIdInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERIES[1])) {
            preparedStatement.setString(1, employee.getPosition());
            preparedStatement.executeUpdate();
            infoId[1] = getIdInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERIES[2])) {
            preparedStatement.setString(1, employee.getProjectName());
            preparedStatement.executeUpdate();
            infoId[2] = getIdInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERIES[3])) {
            preparedStatement.setInt(1, employee.getCabinetNumber());
            preparedStatement.executeUpdate();
            infoId[3] = getIdInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoId;
    }

    private int getIdInfo() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[0])) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[8])) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getString(3),
                        resultSet.getString(4),
                        getDepartmentName(resultSet.getInt(5)),
                        getPositionName(resultSet.getInt(6)),
                        getProjectName(resultSet.getInt(7)),
                        getCabinetNumber(resultSet.getInt(8))
                ));
                employees.get(employees.size() - 1).setId(resultSet.getInt(1));
                employees.get(employees.size() - 1).setEmploymentDate(resultSet.getDate(2).toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private String getDepartmentName(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[4])) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[5])) {
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

    private String getProjectName(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[6])) {
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

    private int getCabinetNumber(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERIES[7])) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
