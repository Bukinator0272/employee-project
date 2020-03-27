DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS position;

CREATE TABLE employee
(
    employee_id serial PRIMARY KEY,
    employment_date DATE NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    department_id INTEGER NOT NULL,
    position_id INTEGER NOT NULL,
    manager_id INTEGER,
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (position_id) REFERENCES position(position_id),
    FOREIGN KEY (manager_id) REFERENCES employee(employee_id)
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
