package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Jms")
@RestController
public class JmsController {

    private final EmployeeService employeeService;

    public JmsController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Value("${activemq.destinationName}")
    private String destinationName;

    @ApiOperation(value = "Send message with employee",
            authorizations = {
                    @Authorization(
                            value = "Employee",
                            scopes = {@AuthorizationScope(scope = "send:massage", description = "send employee as a message")}
                    )})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Everything is working"),
            @ApiResponse(code = 500, message = "Internal Server Error — API developers should avoid this error. If an error occurs in the global catch blog, the stacktrace should be logged and not returned as response")
    })
    @PostMapping(value = "/jms", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@RequestBody Employee employee) {
        employeeService.sendMessage(destinationName, employee);
    }
}
