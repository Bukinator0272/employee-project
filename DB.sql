DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS position;
DROP TABLE IF EXISTS project_name;
DROP TABLE IF EXISTS cabinet_number;

CREATE TABLE employee
(
    id serial PRIMARY KEY,
    employment_date DATE,
    name VARCHAR(255),
    surname VARCHAR(255),
    department_id INTEGER REFERENCES department(department_id),
    position_id INTEGER REFERENCES position(position_id),
    project_name_id INTEGER REFERENCES project_name(project_name_id),
    cabinet_number_id INTEGER REFERENCES cabinet_number(cabinet_number_id)
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

CREATE TABLE project_name
(
    project_name_id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE cabinet_number
(
    cabinet_number_id serial PRIMARY KEY,
    number INTEGER
);
