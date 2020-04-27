package model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private Long id;
    private String name;
    private String surname;
    private Position position;
    private Department department;
    private Employee manager;
    private LocalDate employmentDate;

    public Employee() {
    }

    public Employee(Long id, String name, String surname, Position position, Department department, Employee manager, LocalDate employmentDate) {
        this.id = id;
        this.employmentDate = LocalDate.now();
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.department = department;
        this.manager = manager;
        this.employmentDate = employmentDate;
    }

    public Employee(String name, String surname, Position position, Department department, Employee manager, LocalDate employmentDate) {
        this.employmentDate = LocalDate.now();
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.department = department;
        this.manager = manager;
        this.employmentDate = employmentDate;
    }

    public Employee(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getStringID() {
        return Long.toString(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

