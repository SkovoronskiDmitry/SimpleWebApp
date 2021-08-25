package com.mastery.java.task.rest;

import com.mastery.java.task.dto.ErrorDto;
import com.mastery.java.task.service.datetime.DateTimeService;
import com.mastery.java.task.service.exception.EmployeeServiceException;
import com.mastery.java.task.service.exception.EmployeeServiceNotFoundException;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomGlobalExceptionHandlerTest {

    private static final Date DATE = new Date();

    @Mock
    private DateTimeService dateTimeService;
    @InjectMocks
    private CustomGlobalExceptionHandler customGlobalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleEmployeeServiceNotFoundException() {
        //given
        final EmployeeServiceNotFoundException employeeServiceNotFoundException = new EmployeeServiceNotFoundException("1");
        when(dateTimeService.getCurrentDate()).thenReturn(DATE);

        //when
        final ErrorDto errorDto =
                customGlobalExceptionHandler.handleEmployeeServiceNotFoundException(employeeServiceNotFoundException);
        //then
        Mockito.verify(dateTimeService).getCurrentDate();
        Assertions.assertNotNull(errorDto);
        assertThat(errorDto.getDate(), CoreMatchers.is(DATE));
        assertThat(errorDto.getHttpStatus(), CoreMatchers.is(HttpStatus.NOT_FOUND));
        assertThat(errorDto.getError(), CoreMatchers.is(Collections.singletonList("Employee not found.")));
    }

    @Test
    public void handleEmployeeServiceException() {
        //given
        final RuntimeException runtimeException = new RuntimeException();
        final EmployeeServiceException employeeServiceException = new EmployeeServiceException("Massage exception", runtimeException);
        when(dateTimeService.getCurrentDate()).thenReturn(DATE);

        //when
        final ErrorDto errorDto =
                customGlobalExceptionHandler.handleEmployeeServiceException(employeeServiceException);
        //then
        Mockito.verify(dateTimeService).getCurrentDate();
        Assertions.assertNotNull(errorDto);
        assertThat(errorDto.getDate(), CoreMatchers.is(DATE));
        assertThat(errorDto.getHttpStatus(), CoreMatchers.is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(errorDto.getError(), CoreMatchers.is(Collections.singletonList("Service can't sorted out this issue.")));
    }

    @Test
    public void uncaughtException() {
        //given
        final Throwable throwable = new Throwable();
        when(dateTimeService.getCurrentDate()).thenReturn(DATE);

        //when
        final ErrorDto errorDto =
                customGlobalExceptionHandler.uncaughtException(throwable);

        //then
        Mockito.verify(dateTimeService).getCurrentDate();
        Assertions.assertNotNull(errorDto);
        assertThat(errorDto.getDate(), CoreMatchers.is(DATE));
        assertThat(errorDto.getHttpStatus(), CoreMatchers.is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(errorDto.getError(), CoreMatchers.is(Collections.singletonList("API developers should avoid this error. If an error occurs in the global catch blog, the stacktrace should be logged and not returned as response.")));
    }
}