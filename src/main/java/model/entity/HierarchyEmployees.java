package model.entity;

public class HierarchyEmployees {

    private Long level;
    private Employee employee;

    public HierarchyEmployees(Long level, Employee employee) {
        this.level = level;
        this.employee = employee;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
