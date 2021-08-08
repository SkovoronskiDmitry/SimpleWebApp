package com.mastery.java.task.service.datetime;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateTimeService {

    public Date getCurrentDate() {
        return new Date();
    }
}
