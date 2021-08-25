package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.exception.EmployeeServiceException;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.mastery.java.task.dto.Gender.MALE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private static Employee employeeForTest = new Employee(
            "Alex",
            "Poll",
            MALE,
            8L,
            "assistance",
            LocalDate.now());

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllEmployee() throws EmployeeServiceException {
        // given
        final List<Employee> employeeList = new LinkedList<>();
        employeeList.add(employeeForTest);
        when(employeeRepository.findAll()).thenReturn(employeeList);

        // when
        final List<Employee> resultListEmployee = employeeService.findAll();

        // then
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
        Assertions.assertNotNull(resultListEmployee);
    }

    @Test
    public void shouldFindEmployeeById() throws EmployeeServiceException {
        // given
        Mockito.when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(employeeForTest));

        // when
        final Optional<Employee> optionalEmployee = employeeService.findById(any(Long.class));

        // then
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(0L);
        assertThat(optionalEmployee, CoreMatchers.notNullValue());
        assertThat(employeeForTest.getFirstName(), CoreMatchers.is(optionalEmployee.get().getFirstName()));
        assertThat(employeeForTest.getLastName(), CoreMatchers.is(optionalEmployee.get().getLastName()));
        assertThat(employeeForTest.getJobTitle(), CoreMatchers.is(optionalEmployee.get().getJobTitle()));
        assertThat(employeeForTest.getGender(), CoreMatchers.is(optionalEmployee.get().getGender()));
        assertThat(employeeForTest.getDepartmentId(), CoreMatchers.is(optionalEmployee.get().getDepartmentId()));
        assertThat(employeeForTest.getDateOfBirth(), CoreMatchers.is(optionalEmployee.get().getDateOfBirth()));
    }

    @Test
    public void shouldFindEmployeeByFirstNameAndLastName() throws EmployeeServiceException {
        //given
        Mockito.when(employeeRepository.findByFirstNameAndLastName(any(String.class), any(String.class))).
                thenReturn(Collections.singletonList(employeeForTest));

        // when
        final List<Employee> listEmployee = employeeService.findByFirstNameAndLastName("", "");

        // then
        Mockito.verify(employeeRepository, Mockito.times(1)).findByFirstNameAndLastName(any(String.class), any(String.class));
        assertThat(listEmployee, CoreMatchers.notNullValue());
        assertThat(employeeForTest.getFirstName(), CoreMatchers.is(listEmployee.get(0).getFirstName()));
        assertThat(employeeForTest.getLastName(), CoreMatchers.is(listEmployee.get(0).getLastName()));
        assertThat(employeeForTest.getJobTitle(), CoreMatchers.is(listEmployee.get(0).getJobTitle()));
        assertThat(employeeForTest.getGender(), CoreMatchers.is(listEmployee.get(0).getGender()));
        assertThat(employeeForTest.getDepartmentId(), CoreMatchers.is(listEmployee.get(0).getDepartmentId()));
        assertThat(employeeForTest.getDateOfBirth(), CoreMatchers.is(listEmployee.get(0).getDateOfBirth()));

    }

    @Test
    public void shouldCreateEmployee() throws EmployeeServiceException {
        // given
        when(employeeRepository.save(employeeForTest)).thenReturn(employeeForTest);

        // when
        final Employee employeeAfterService = employeeService.createEmployee(employeeForTest);

        // then
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employeeForTest);
        assertThat(employeeAfterService, CoreMatchers.notNullValue());
    }

    @Test
    public void shouldUpdateEmployee() throws EmployeeServiceException {
        // given
        Employee employeeForUpdate = new Employee("Alex",
                "Poll",
                MALE,
                69L,
                "boss",
                LocalDate.now());
        employeeForUpdate.setEmployeeId(1L);
        when(employeeRepository.findById(any(Long.class))
                .map(employeeForTest -> {
                    employeeForTest.setJobTitle(employeeForUpdate.getJobTitle());
                    employeeForTest.setDepartmentId(employeeForUpdate.getDepartmentId());
                    return employeeRepository.save(employeeForTest);
                })).thenReturn(Optional.ofNullable(employeeForTest));

        // when
        employeeService.updateEmployee(1L, employeeForUpdate);

        // then
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(any(Employee.class));
        assertThat(employeeForTest.getFirstName(), CoreMatchers.is(employeeForUpdate.getFirstName()));
        assertThat(employeeForTest.getLastName(), CoreMatchers.is(employeeForUpdate.getLastName()));
        assertThat(employeeForTest.getDepartmentId(), CoreMatchers.is(employeeForUpdate.getDepartmentId()));
        assertThat(employeeForTest.getJobTitle(), CoreMatchers.is(employeeForUpdate.getJobTitle()));
        assertThat(employeeForTest.getGender(), CoreMatchers.is(employeeForUpdate.getGender()));
        assertThat(employeeForTest.getDateOfBirth(), CoreMatchers.is(employeeForUpdate.getDateOfBirth()));
    }

    @Test
    public void shouldDeleteEmployee() throws EmployeeServiceException {
        // given
        doNothing().when(employeeRepository).deleteById(1L);

        // when
        employeeService.deleteEmployee(1L);

        // then
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(any(Long.class));
    }

}
