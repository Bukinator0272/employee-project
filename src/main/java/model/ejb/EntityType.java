package model.ejb;

import model.entity.Department;
import model.entity.Employee;
import model.entity.Position;

public enum EntityType {
    DEPARTMENT(Department.class.getName()),
    POSITION(Position.class.getName()),
    EMPLOYEE(Employee.class.getName()),
    TYPE_NOT_FOUND (String.valueOf(""));

    private String className;

    EntityType(String className) {
        this.className = className;
    }

    private String getClassName() {
        return className;
    }

    public static EntityType getByClass(Class classType) {
        for (EntityType entityType : EntityType.values()) {
            String className = classType.getName();
            String entityClassName = entityType.getClassName();
            if (className.equals(entityClassName))
                return entityType;
        }
        return TYPE_NOT_FOUND;
    }
}