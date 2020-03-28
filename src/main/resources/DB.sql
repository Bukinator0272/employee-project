DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS position;

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

INSERT INTO department (name) VALUES ('NetСracker');
INSERT INTO position (name) VALUES ('Director');
INSERT INTO employee (employment_date, name, surname, department_id, position_id, manager_id) VALUES ('2010-01-01', 'Grigoriy', 'Fligil', 1, 1, null);

INSERT INTO department (name) VALUES ('NetСracker');
INSERT INTO position (name) VALUES ('Manager');
INSERT INTO employee (employment_date, name, surname, department_id, position_id, manager_id) VALUES ('2018-04-3', 'Lena', 'Chernova', 2, 2, 1);

INSERT INTO department (name) VALUES ('NetСracker');
INSERT INTO position (name) VALUES ('TeamLead');
INSERT INTO employee (employment_date, name, surname, department_id, position_id, manager_id) VALUES ('2020-03-27', 'Nikita', 'Burenkov', 3, 3, 2);

INSERT INTO department (name) VALUES ('NetСracker');
INSERT INTO position (name) VALUES ('Developer');
INSERT INTO employee (employment_date, name, surname, department_id, position_id, manager_id) VALUES ('2020-03-27', 'Liza', 'Ganina', 4, 4, 3);

INSERT INTO department (name) VALUES ('NetСracker');
INSERT INTO position (name) VALUES ('Junior');
INSERT INTO employee (employment_date, name, surname, department_id, position_id, manager_id) VALUES ('2020-03-27', 'Dima', 'Bukin', 5, 5, 4);
