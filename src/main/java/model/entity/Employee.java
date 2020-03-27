package model.entity;

import java.time.LocalDate;

public class Employee {

    private int id;
    private LocalDate employmentDate;
    private String name;
    private String surname;
    private String department;
    private String position;
    private int manager;

    public Employee() {
    }

    public Employee(String name, String surname, String department, String position) {
        this.employmentDate = LocalDate.now();
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.position = position;
    }

    public Employee( String name, String surname, String department, String position, int manager) {
        this.employmentDate = LocalDate.now();
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.position = position;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

}

