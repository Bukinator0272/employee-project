DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS position;

CREATE TABLE employee
(
    employee_id serial PRIMARY KEY,
    employment_date DATE,
    name VARCHAR(255),
    surname VARCHAR(255),
    department_id INTEGER REFERENCES department(department_id),
    position_id INTEGER REFERENCES position(position_id),
    manager_id INTEGER REFERENCES employee(employee_id)
);

CREATE TABLE department
(
    department_id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE position
(
    position_id serial PRIMARY KEY,
    name VARCHAR(255)
);
