package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.service.exception.EmployeeServiceException;
import com.mastery.java.task.service.exception.EmployeeServiceNotFoundException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Api(value = "Employees")
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(
            value = "Get all employees",
            response = Employee.class,
            responseContainer = "List",
            authorizations = {
                    @Authorization(
                            value = "Employees",
                            scopes = {@AuthorizationScope(scope = "find:employees", description = "find all employees")}
                    )}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Everything is working"),
            @ApiResponse(code = 400, message = "Bad Request — The request was invalid or cannot be served. The exact error should be explained in the error payload. E.g. „The JSON is not valid"),
            @ApiResponse(code = 500, message = "Internal Server Error — API developers should avoid this error. If an error occurs in the global catch blog, the stacktrace should be logged and not returned as response")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Employee> findAll(@Validated @RequestParam(value = "firstName", required = false) final String firstName,
                                        @Validated @RequestParam(value = "lastName", required = false) final String lastName) throws EmployeeServiceException {

        if (firstName != null && lastName != null) {
            LOGGER.info("IN: Find Employee with firstName: {}, lastName: {}", firstName, lastName);
            return employeeService.findByFirstNameAndLastName(firstName, lastName);
        } else {
            LOGGER.info("IN: Find all employees");
            return employeeService.findAll();
        }
    }

    @ApiOperation(
            value = "Find employee by ID",
            response = Employee.class,
            authorizations = {
                    @Authorization(
                            value = "Employee",
                            scopes = {@AuthorizationScope(scope = "find:employee", description = "find employee by ID")}
                    )})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Everything is working"),
            @ApiResponse(code = 400, message = "Bad Request — The request was invalid or cannot be served. The exact error should be explained in the error payload. E.g. „The JSON is not valid"),
            @ApiResponse(code = 404, message = "Not found — There is no resource behind the URI"),
            @ApiResponse(code = 500, message = "Internal Server Error — API developers should avoid this error. If an error occurs in the global catch blog, the stacktrace should be logged and not returned as response")
    })
    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee findById(
            @Validated @ApiParam(value = "ID for search employee", required = true)
            @PathVariable final Long employeeId) throws EmployeeServiceNotFoundException, EmployeeServiceException {
        LOGGER.info("IN: Find Employee with ID: {}", employeeId);
        return employeeService.findById(employeeId).
                orElseThrow(() -> new EmployeeServiceNotFoundException(employeeId.toString()));
    }

    @ApiOperation(
            value = "Create new Employee",
            response = Long.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Everything is working"),
            @ApiResponse(code = 201, message = "Employee was created"),
            @ApiResponse(code = 400, message = "Bad Request — The request was invalid or cannot be served. The exact error should be explained in the error payload. E.g. „The JSON is not valid"),
            @ApiResponse(code = 500, message = "Internal Server Error — API developers should avoid this error. If an error occurs in the global catch blog, the stacktrace should be logged and not returned as response")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee createEmployee(
            @ApiParam(value = "New employee", required = true)
            @Valid @RequestBody final Employee employee) throws EmployeeServiceException {
        LOGGER.info("IN: Create Employee: {}", employee);
        return employeeService.createEmployee(employee);
    }

    @ApiOperation(
            value = "Update Employee",
            notes = "change the values departmentId and job title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The Employee was update "),
            @ApiResponse(code = 400, message = "Bad Request — The request was invalid or cannot be served. The exact error should be explained in the error payload. E.g. „The JSON is not valid"),
            @ApiResponse(code = 500, message = "Internal Server Error — API developers should avoid this error. If an error occurs in the global catch blog, the stacktrace should be logged and not returned as response")
    })
    @PutMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Employee> updateEmployee(
            @Validated @ApiParam(value = "Employee for update", required = true)
            @PathVariable final Long employeeId, @RequestBody final Employee employee) throws EmployeeServiceException {
        LOGGER.info("IN: Update Employee with ID: {}", employeeId);
        return employeeService.updateEmployee(employeeId, employee);
    }

    @ApiOperation(value = "Delete Employee by ID",
            authorizations = {
                    @Authorization(
                            value = "Employee",
                            scopes = {@AuthorizationScope(scope = "delete:employee", description = "delete employee by ID")}
                    )})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Everything is working"),
            @ApiResponse(code = 400, message = "Bad Request — The request was invalid or cannot be served. The exact error should be explained in the error payload. E.g. „The JSON is not valid"),
            @ApiResponse(code = 404, message = "Not found — There is no resource behind the URI"),
            @ApiResponse(code = 500, message = "Internal Server Error — API developers should avoid this error. If an error occurs in the global catch blog, the stacktrace should be logged and not returned as response")
    })
    @DeleteMapping(value = "/{employeeId}")
    public void deleteEmployee(
            @Validated @ApiParam(value = "ID for delete employee", required = true)
            @PathVariable final Long employeeId) throws EmployeeServiceException, EmployeeServiceException {
        LOGGER.info("IN: Delete Employee with ID {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        LOGGER.info("OUT: Employee was successfully delete with ID {}", employeeId);
    }

}
