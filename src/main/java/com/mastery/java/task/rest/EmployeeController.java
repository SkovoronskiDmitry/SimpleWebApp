package com.mastery.java.task.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll() {

        return "employeeService.findAll()";
    }

}
