DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
	employee_id SERIAL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	department_id INT,
	job_title VARCHAR(30),
	gender VARCHAR (6),
	date_of_birth DATE,
	PRIMARY KEY (employee_id));



