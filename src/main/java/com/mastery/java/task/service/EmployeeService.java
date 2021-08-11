package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.exception.EmployeeServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> findAll() throws EmployeeServiceException {
        return employeeDao.findAll();
    }

    public Optional<Employee> findById(final Long employeeId) throws EmployeeServiceException {
        return employeeDao.findById(employeeId);
    }

    public List<Employee> findByFirstNameAndLastName(final String firstName, final String lastName) throws EmployeeServiceException {
        return employeeDao.findByFirstNameAndLastName(firstName, lastName);
    }

    public Employee createEmployee(final Employee employee) throws EmployeeServiceException {
        return employeeDao.save(employee);
    }

    public Optional<Employee> updateEmployee(final Long employeeId, final Employee employee) throws EmployeeServiceException {
        return employeeDao.findById(employeeId).map(newEmployee -> {
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setJobTitle(employee.getJobTitle());
            newEmployee.setDepartmentId(employee.getDepartmentId());
            return employeeDao.save(newEmployee);
        });
    }

    public void deleteEmployee(final Long employeeId) throws EmployeeServiceException {
        employeeDao.deleteById(employeeId);
    }
}
