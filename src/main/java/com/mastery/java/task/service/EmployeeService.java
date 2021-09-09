package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.exception.EmployeeServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeDao employeeDao;

    private final JmsTemplate jmsTemplate;

    @Value("${activemq.destinationName}")
    private String destinationName;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao, JmsTemplate jmsTemplate) {
        this.employeeDao = employeeDao;
        this.jmsTemplate = jmsTemplate;
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

    public void sendMessage(Employee employee) {
        try {
            LOGGER.info("Send message to : " + destinationName);
            jmsTemplate.convertAndSend(destinationName, employee);
        } catch (Exception ex) {
            LOGGER.error("Exception during send Message: ", ex);
        }
    }

    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Employee employee = (Employee) objectMessage.getObject();
            createEmployee(employee);
            LOGGER.info("Received Message: " + employee.toString());
        } catch (Exception ex) {
            LOGGER.error("Received Exception : " + ex);
        }
    }
}
