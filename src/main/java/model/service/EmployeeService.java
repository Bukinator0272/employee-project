package model.service;

import model.connection.PoolConnectionBuilder;
import model.dao.EmployeeDAO;
import model.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements EmployeeDAO {

    private Connection connection;

    public EmployeeService() {
        this.connection = new PoolConnectionBuilder().getConnection();
    }

    public void add(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee (employment_date, name, surname, department_id, position_id, manager_id) VALUES ((?), (?), (?), (?), (?), (?))")) {
            if (isManagerExist(employee.getManager()) || isEmpty()) {
                int[] infoId = addInfo(employee);
                preparedStatement.setObject(1, Date.valueOf(employee.getEmploymentDate()));
                preparedStatement.setString(2, employee.getName());
                preparedStatement.setString(3, employee.getSurname());
                preparedStatement.setInt(4, infoId[0]);
                preparedStatement.setInt(5, infoId[1]);
                if (employee.getManager() == 0 || isEmpty()) {
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

    @Override
    public Employee read(int i) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE employee_id = (?)")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.getInt(7) > 0 && resultSet.getInt(7) < 1000) {
                return new Employee(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        getDepartmentName(resultSet.getInt("department_id")),
                        getPositionName(resultSet.getInt("position_id")),
                        resultSet.getInt("manager_id")
                );
            } else {
                return new Employee(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        getDepartmentName(resultSet.getInt("department_id")),
                        getPositionName(resultSet.getInt("position_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(7) > 0 && resultSet.getInt(7) < 1000) {
                    employees.add(new Employee(
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            getDepartmentName(resultSet.getInt("department_id")),
                            getPositionName(resultSet.getInt("position_id")),
                            resultSet.getInt("manager_id")
                    ));
                } else {
                    employees.add(new Employee(
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            getDepartmentName(resultSet.getInt("department_id")),
                            getPositionName(resultSet.getInt("position_id"))
                    ));
                }
                employees.get(employees.size() - 1).setId(resultSet.getInt("employee_id"));
                employees.get(employees.size() - 1).setEmploymentDate(resultSet.getDate("employment_date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET name = (?) WHERE id = (?)")) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE employee_id = (?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isManagerExist(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT employee_id FROM employee where employee_id = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isEmpty() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee")) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO department (name) VALUES ((?))")) {
            preparedStatement.setString(1, employee.getDepartment());
            preparedStatement.executeUpdate();
            infoId[tableCount] = getIdInfoId(tableCount);
            tableCount++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO position (name) VALUES ((?))")) {
            preparedStatement.setString(1, employee.getPosition());
            preparedStatement.executeUpdate();
            infoId[tableCount] = getIdInfoId(tableCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoId;
    }

    private int getIdInfoId(int tableCount) {
        String[] SELECT_QUERIES = {
                "SELECT COUNT(*) FROM department", "SELECT COUNT(*) FROM position"
        };
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


    private String getDepartmentName(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM department WHERE department_id = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPositionName(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM position WHERE position_id = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //дернуть в сервлете
    public String getManagersNameSurname(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, surname FROM employee WHERE employee_id = (?)")) {
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