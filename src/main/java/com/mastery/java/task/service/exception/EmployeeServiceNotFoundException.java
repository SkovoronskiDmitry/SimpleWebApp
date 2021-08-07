package com.mastery.java.task.service.exception;

public class EmployeeServiceNotFoundException extends Exception{

    public EmployeeServiceNotFoundException(final String data) {
        super("Employee not found with data: " + data);
    }
}
